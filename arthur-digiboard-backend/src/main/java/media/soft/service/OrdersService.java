package media.soft.service;

import lombok.RequiredArgsConstructor;
import media.soft.controller.ECommerceController;
import media.soft.model.Order;
import media.soft.repository.OrdersRepositoryDao;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Order> getOrdersByLatestOrderWeek() {
        List<Order> orders = new ArrayList<>();

        LocalDate latestOrderDate = ordersRepositoryDao.getLatestOrderDate();
        LocalDate startOfWeek = latestOrderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = latestOrderDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        logger.info("Given Date: " + latestOrderDate);
        logger.info("Start of the Week: " + startOfWeek);
        System.out.println("End of the Week: " + endOfWeek);

        orders = ordersRepositoryDao.getOrdersByPeriod(startOfWeek, endOfWeek);
        System.out.println("oder count: " + orders.size());

        return orders;
    }
}
