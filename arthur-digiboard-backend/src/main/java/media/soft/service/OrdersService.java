package media.soft.service;

import lombok.RequiredArgsConstructor;
import media.soft.model.Order;
import media.soft.repository.OrdersRepositoryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    private final OrdersRepositoryDao ordersRepositoryDao;

    public OrdersService(@Autowired OrdersRepositoryDao ordersRepositoryDao){
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
}

