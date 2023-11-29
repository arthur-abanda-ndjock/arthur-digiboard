package media.soft.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import media.soft.model.ECommerceDashboardCard;
import media.soft.model.Order;
import media.soft.model.OrderCard;
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
	public ResponseEntity<List<Order>> getAllOrders() {
		return new ResponseEntity<>(ordersService.getAllOrders(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = "/dashboard-cards", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ECommerceDashboardCard> getOrdersByLatestOrderWeek() {
		ECommerceDashboardCard dashboardCard = new ECommerceDashboardCard();

		// build order card
		OrderCard orderCard = new OrderCard();
		orderCard.setTotalOrderCount(ordersService.getTotalOrderCount());
		orderCard.setLatestWeekPercentageChange(ordersService.getOrderLatestWeekPercentageChange());

		dashboardCard.setOrderCard(orderCard);
		return new ResponseEntity<>(dashboardCard, HttpStatusCode.valueOf(200));
	}
}
