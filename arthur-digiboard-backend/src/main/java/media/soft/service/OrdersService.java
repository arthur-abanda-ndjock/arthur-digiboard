package media.soft.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import media.soft.model.Order;
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

    public Long getTotalOrderCount() {
        return ordersRepositoryDao.getTotalCount();
    }

    public List<Order> getAllOrders() {
        return ordersRepositoryDao.getAll();
    }
}
