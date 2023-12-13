package media.soft.service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import media.soft.model.Order;
import media.soft.model.OrderPriceByDate;
import media.soft.model.OrderSummary;
import media.soft.model.SalesWeeklyRecap;
import media.soft.repository.OrdersRepositoryDao;

@Service
public class OrdersService {

    Logger logger = LoggerFactory.getLogger(OrdersService.class);

    private final OrdersRepositoryDao ordersRepositoryDao;

    public OrdersService(@Autowired OrdersRepositoryDao ordersRepositoryDao) {
        this.ordersRepositoryDao = ordersRepositoryDao;
    }

    public void insertOrders(Order order) {
        ordersRepositoryDao.insert(order);
    }

    public void updateOrdersById(int orderId, Order order) {
        ordersRepositoryDao.updateById(orderId, order);
    }

    public void deleteOrdersById(int orderId) {
        ordersRepositoryDao.deleteById(orderId);
    }

    public List<OrderPriceByDate> getAllOrderGroupedByDate() {

        List<OrderPriceByDate> orderPriceByDates = new ArrayList<>();

        List<Order> orders = this.getAllOrders();
        Map<LocalDate, List<Order>> orderMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getOrderDate, TreeMap::new, Collectors.toList()));

        for (Entry<LocalDate, List<Order>> entry : orderMap.entrySet()) {

            OrderPriceByDate orderPriceByDate = new OrderPriceByDate();

            orderPriceByDate.setOrderDate(entry.getKey());

            entry.getValue().forEach(order -> {
                switch (order.getProductType()) {
                    case "EBook":
                        orderPriceByDate.setEbookPrice(orderPriceByDate.getEbookPrice().add(order.getPrice()));
                        break;
                    case "Software":
                        orderPriceByDate.setSoftwarePrice(orderPriceByDate.getSoftwarePrice().add(order.getPrice()));
                        break;
                    case "DigitalMusic":
                        orderPriceByDate
                                .setDigitalMusicPrice(orderPriceByDate.getDigitalMusicPrice().add(order.getPrice()));
                        break;
                    case "OnlineCourse":
                        orderPriceByDate
                                .setOnlineCoursePrice(orderPriceByDate.getOnlineCoursePrice().add(order.getPrice()));
                        break;
                    case "StreamingService":
                        orderPriceByDate.setStreamingServicePrice(
                                orderPriceByDate.getStreamingServicePrice().add(order.getPrice()));
                        break;
                    default:
                        break;
                }
            });
            orderPriceByDates.add(orderPriceByDate);
        }

        return orderPriceByDates;
    }

    public List<SalesWeeklyRecap> getWeeklySaleRecaps(int maxRecapWeeks) {

        List<SalesWeeklyRecap> recap = new ArrayList<>();
        int weekMax = maxRecapWeeks;

        for (int i = weekMax; i >= 0; i--) {
            LocalDate latestOrderDate = ordersRepositoryDao.getLatestOrderDate();
            LocalDate startOfWeek = latestOrderDate
                    .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                    .minusWeeks(i);
            LocalDate endOfWeek = latestOrderDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).minusWeeks(i);
            logger.info("{}. week start {} \t week end {}", i, startOfWeek, endOfWeek);

            List<Order> orders = ordersRepositoryDao.getOrdersByPeriod(startOfWeek, endOfWeek);
            BigDecimal weeklySale = orders.stream().map(Order::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            logger.info("week sale count: {}", orders.size());
            logger.info("week sale price: {}", weeklySale);

            String weekId = (i == 0) ? "this week" : "W-" + i;
            recap.add(new SalesWeeklyRecap(weekId, weeklySale));
        }
        return recap;
    }

    public Double getOrderLatestWeekPercentageChange() {

        LocalDate latestOrderDate = ordersRepositoryDao.getLatestOrderDate();
        LocalDate startOfWeek = latestOrderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = latestOrderDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        logger.info("latest order date: {}", latestOrderDate);
        logger.info("Start of the week of latest order date: {}", startOfWeek);
        logger.info("End of the week of latest order date: {}", endOfWeek);

        List<Order> orders = ordersRepositoryDao.getOrdersByPeriod(startOfWeek, endOfWeek);
        logger.info("current week oder count: {}", orders.size());
        int currentWeekOrderCount = orders.size();

        startOfWeek = latestOrderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).minusWeeks(1);
        endOfWeek = latestOrderDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).minusWeeks(1);

        logger.info("Start of the previous week of latest order date: {}", startOfWeek);
        logger.info("End of the previous week of latest order date: {}", endOfWeek);

        orders = ordersRepositoryDao.getOrdersByPeriod(startOfWeek, endOfWeek);
        logger.info("previous week oder count: {}", orders.size());
        int previousWeekOrderCount = orders.size();

        double percentageChange = (((double) currentWeekOrderCount - (double) previousWeekOrderCount)
                / previousWeekOrderCount) * 100;
        logger.info("percentage: {}", percentageChange);

        return percentageChange;
    }

    public List<OrderSummary> getRecentOrders() {
        return ordersRepositoryDao.getRecentOrders();
    }

    public Long getTotalOrderCount() {
        return ordersRepositoryDao.getTotalCount();
    }

    public List<Order> getAllOrders() {
        return ordersRepositoryDao.getAll();
    }

    public BigDecimal getOrderAmountSum() {
        return ordersRepositoryDao.getOrderAmountSum();
    }
}
