package media.soft.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import media.soft.model.BalanceWeeklyRecap;
import media.soft.model.CategoryCost;
import media.soft.model.ECommerceDashboardCard;
import media.soft.model.MarketingCost;
import media.soft.model.MarketingCostRecap;
import media.soft.model.Order;
import media.soft.model.OrderCard;
import media.soft.model.OrderPriceByDate;
import media.soft.model.OrderSummary;
import media.soft.model.SalesWeeklyRecap;
import media.soft.model.ShippingFeesMart;
import media.soft.service.ECommerceService;
import media.soft.service.MarketingCostService;
import media.soft.service.OrdersService;

@RestController
@RequestMapping("/api/ecommerce")
public class ECommerceController {

	Logger logger = LoggerFactory.getLogger(ECommerceController.class);

	private ECommerceService eCommerceService;
	private OrdersService ordersService;
	private MarketingCostService marketingCostService;

	public ECommerceController(@Autowired ECommerceService eCommerceService, @Autowired OrdersService ordersService,
			@Autowired MarketingCostService marketingCostService) {
		this.eCommerceService = eCommerceService;
		this.ordersService = ordersService;
		this.marketingCostService = marketingCostService;
	}

	@GetMapping(value = "/shippingfees", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ShippingFeesMart> getAllShippingFees() {
		return eCommerceService.getAllShippingFees();
	}

	@GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getAllOrders() {
		return new ResponseEntity<>(ordersService.getAllOrders(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = "/orders/summary/recents", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderSummary>> getOrders() {
		return new ResponseEntity<>(ordersService.getRecentOrders(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = "/orders/by-date", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderPriceByDate>> getAllOrdersByDate() {

		List<OrderPriceByDate> orderPriceByDates = ordersService.getAllOrderGroupedByDate();
		return new ResponseEntity<>(orderPriceByDates, HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = "/orders/weekly-sales", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SalesWeeklyRecap>> getSaleRecapByWeek() {

		List<SalesWeeklyRecap> sales = ordersService.getWeeklySaleRecaps(4);
		return new ResponseEntity<>(sales, HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = "/costs/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MarketingCost>> getMarketingCosts() {

		List<MarketingCost> sales = marketingCostService.getAll();
		return new ResponseEntity<>(sales, HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = "/costs/categories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, List<CategoryCost>>> getMarketingCostsGroupedByCategories() {
		return new ResponseEntity<>(marketingCostService.getTotalsByAllCategories(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = "/costs/recents", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MarketingCostRecap>> getRecentCost() {
		return new ResponseEntity<>(marketingCostService.getRecentCost(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = "/costs/sum", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> getMarkingCostSum() {
		return new ResponseEntity<>(marketingCostService.getMarketingCostSum(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = "/balance/weekly-recap", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BalanceWeeklyRecap>> getBalanceWeeklyRecap() {
		return new ResponseEntity<>(eCommerceService.getBalanceWeeklyRecap(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> getTotalBalance() {
		return new ResponseEntity<>(eCommerceService.getBalance(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = "/dashboard-cards", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ECommerceDashboardCard> getOrdersByLatestOrderWeek() {
		ECommerceDashboardCard dashboardCard = new ECommerceDashboardCard();

		OrderCard orderCard = new OrderCard();
		orderCard.setTotalOrderCount(ordersService.getTotalOrderCount());
		orderCard.setLatestWeekPercentageChange(ordersService.getOrderLatestWeekPercentageChange());

		dashboardCard.setOrderCard(orderCard);
		return new ResponseEntity<>(dashboardCard, HttpStatusCode.valueOf(200));
	}
}
