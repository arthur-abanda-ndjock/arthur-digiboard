package media.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import media.soft.repository.OrdersRepositoryDao;
import media.soft.repository.ShippingFeesMartDao;
import media.soft.model.Order;
import media.soft.model.ShippingFeesMart;

@Service
public class ECommerceService {

	private ShippingFeesMartDao shippingFeesMartDao;
	private OrdersRepositoryDao ordersRepositoryDao;

	public ECommerceService(@Autowired ShippingFeesMartDao shippingFeesMartDao, @Autowired OrdersRepositoryDao ordersRepositoryDao) {
		this.shippingFeesMartDao = shippingFeesMartDao;
		this.ordersRepositoryDao = ordersRepositoryDao;
	}
	
	public List<ShippingFeesMart> getAllShippingFees(){
		return shippingFeesMartDao.getAllData();
	}

	public List<Order> getAllOrders(){
		return ordersRepositoryDao.readAll();
	}
}
