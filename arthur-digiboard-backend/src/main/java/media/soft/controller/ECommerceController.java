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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "ECommerce", description = "ECommerce APIs")
@RestController
@RequestMapping(ECommerceController.API_PREFIX)
public class ECommerceController {

	// request paths
	public static final String API_PREFIX = "/api/ecommerce";
	public static final String SHIPPING = "/shippingfees";
	public static final String ORDERS = "/orders";
	public static final String ORDERS_BY_ID = ORDERS + "/{id}";
	public static final String ORDERS_SMR_RCT = ORDERS + "/summary/recents";
	public static final String ORDERS_BY_DATE = ORDERS + "/by-date";
	public static final String ORDERS_WK_SALES = ORDERS + "/weekly-sales";
	public static final String COSTS = "/costs";
	public static final String COSTS_ALL = COSTS + "/all";
	public static final String COSTS_CAT = COSTS + "/categories";
	public static final String COSTS_RECENT = COSTS + "/recents";
	public static final String COSTS_SUM = COSTS + "/sum";
	public static final String BALANCE = "/balance";
	public static final String BALANCE_WK_RECAP = BALANCE + "/weekly-recap";
	public static final String DASHBOARD_CARDS = "/dashboard-cards";

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

	@GetMapping(value = SHIPPING, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ShippingFeesMart> getAllShippingFees() {
		return eCommerceService.getAllShippingFees();
	}

	@Operation(summary = "Retrieve all the orders", description = "Retrieve all the orders. the response is a list of the 'order' objects", tags = {
			"orders", "get", "list" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Order.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping(value = ORDERS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getAllOrders() {
		return new ResponseEntity<>(ordersService.getAllOrders(), HttpStatusCode.valueOf(200));
	}

	@Operation(summary = "Retrieve an order by id", description = "Retrieve an order by id. the response is an 'order' object, using the id", tags = {
			"orders", "get", "list" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Order.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping(value = ORDERS_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> getOrders(@PathVariable Integer id) {
		return new ResponseEntity<>(ordersService.getOrderById(id), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = ORDERS_SMR_RCT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderSummary>> getOrders() {
		return new ResponseEntity<>(ordersService.getRecentOrders(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = ORDERS_BY_DATE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderPriceByDate>> getAllOrdersByDate() {

		List<OrderPriceByDate> orderPriceByDates = ordersService.getAllOrderGroupedByDate();
		return new ResponseEntity<>(orderPriceByDates, HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = ORDERS_WK_SALES, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SalesWeeklyRecap>> getSaleRecapByWeek() {

		List<SalesWeeklyRecap> sales = ordersService.getWeeklySaleRecaps(4);
		return new ResponseEntity<>(sales, HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = COSTS_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MarketingCost>> getMarketingCosts() {

		List<MarketingCost> sales = marketingCostService.getAll();
		return new ResponseEntity<>(sales, HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = COSTS_CAT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, List<CategoryCost>>> getMarketingCostsGroupedByCategories() {
		return new ResponseEntity<>(marketingCostService.getTotalsByAllCategories(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = COSTS_RECENT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MarketingCostRecap>> getRecentCost() {
		return new ResponseEntity<>(marketingCostService.getRecentCost(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = COSTS_SUM, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> getMarkingCostSum() {
		return new ResponseEntity<>(marketingCostService.getMarketingCostSum(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = BALANCE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> getTotalBalance() {
		return new ResponseEntity<>(eCommerceService.getBalance(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = BALANCE_WK_RECAP, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BalanceWeeklyRecap>> getBalanceWeeklyRecap() {
		return new ResponseEntity<>(eCommerceService.getBalanceWeeklyRecap(), HttpStatusCode.valueOf(200));
	}

	@GetMapping(value = DASHBOARD_CARDS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ECommerceDashboardCard> getOrdersByLatestOrderWeek() {
		ECommerceDashboardCard dashboardCard = new ECommerceDashboardCard();

		OrderCard orderCard = new OrderCard();
		orderCard.setTotalOrderCount(ordersService.getTotalOrderCount());
		orderCard.setLatestWeekPercentageChange(ordersService.getOrderLatestWeekPercentageChange());

		dashboardCard.setOrderCard(orderCard);
		return new ResponseEntity<>(dashboardCard, HttpStatusCode.valueOf(200));
	}
}
