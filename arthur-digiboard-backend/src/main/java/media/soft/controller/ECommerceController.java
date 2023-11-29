package media.soft.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import media.soft.model.Order;
import media.soft.model.ShippingFeesMart;
import media.soft.service.ECommerceService;
import media.soft.service.OrdersService;

@RestController
@RequestMapping("/api/ecommerce")
public class ECommerceController {

	Logger logger = LoggerFactory.getLogger(ECommerceController.class);

	private ECommerceService eCommerceService;
	private OrdersService ordersService;

	public ECommerceController(@Autowired ECommerceService eCommerceService, @Autowired OrdersService ordersService) {
		this.eCommerceService = eCommerceService;
		this.ordersService = ordersService;
	}

	@GetMapping(value = "/shippingfees", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ShippingFeesMart> getAllShippingFees() {
		return eCommerceService.getAllShippingFees();
	}

	@GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Order> getAllOrders() {
		return eCommerceService.getAllOrders();
	}

	@GetMapping(value = "/orders/latest-week", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Order> getOrdersByLatestOrderWeek() {
		return ordersService.getOrdersByLatestOrderWeek();
	}

}
