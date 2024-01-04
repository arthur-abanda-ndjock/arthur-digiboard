package media.soft.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import media.soft.model.BalanceWeeklyRecap;
import media.soft.model.CategoryCost;
import media.soft.model.ECommerceDashboardCard;
import media.soft.model.MarketingCost;
import media.soft.model.MarketingCostRecap;
import media.soft.model.MarketingCostWeeklyRecap;
import media.soft.model.Order;
import media.soft.model.OrderCard;
import media.soft.model.OrderPriceByDate;
import media.soft.model.OrderStatus;
import media.soft.model.OrderSummary;
import media.soft.model.SalesWeeklyRecap;
import media.soft.model.ShippingFeesMart;
import media.soft.service.ECommerceService;
import media.soft.service.MarketingCostService;
import media.soft.service.OrdersService;

@ExtendWith(MockitoExtension.class)
class ECommerceControllerTest {

    @Mock
    private ECommerceService eCommerceService;

    @Mock
    private OrdersService ordersService;

    @Mock
    private MarketingCostService marketingCostService;

    @InjectMocks
    private ECommerceController eCommerceController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllShippingFees() {

        List<ShippingFeesMart> expectedShippingFees = Arrays.asList(createSampleShippingFeesMart(),
                createSampleShippingFeesMart());
        when(eCommerceService.getAllShippingFees()).thenReturn(expectedShippingFees);

        List<ShippingFeesMart> result = eCommerceController.getAllShippingFees();

        assertEquals(expectedShippingFees, result);
    }

    @Test
    void testGetAllOrders() {

        List<Order> expectedOrders = Arrays.asList(createSampleOrder(), createSampleOrder());
        when(ordersService.getAllOrders()).thenReturn(expectedOrders);

        ResponseEntity<List<Order>> result = eCommerceController.getAllOrders();

        assertEquals(expectedOrders, result.getBody());
        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void testGetOrders() {

        List<OrderSummary> expectedOrderSummary = Arrays.asList(createSampleOrderSummary(), createSampleOrderSummary());
        when(ordersService.getRecentOrders()).thenReturn(expectedOrderSummary);

        ResponseEntity<List<OrderSummary>> result = eCommerceController.getOrders();

        assertEquals(expectedOrderSummary, result.getBody());
        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void testGetAllOrdersByDate() {

        List<OrderPriceByDate> expectedOrderPriceByDates = Arrays.asList(new OrderPriceByDate(),
                new OrderPriceByDate());
        when(ordersService.getAllOrderGroupedByDate()).thenReturn(expectedOrderPriceByDates);

        ResponseEntity<List<OrderPriceByDate>> result = eCommerceController.getAllOrdersByDate();

        assertEquals(expectedOrderPriceByDates, result.getBody());
        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void testGetSaleRecapByWeek() {

        List<SalesWeeklyRecap> expectedSalesWeeklyRecap = Arrays.asList(
                createSampleSalesRecap("Week-1", BigDecimal.valueOf(100.0)),
                createSampleSalesRecap("Week-3", BigDecimal.valueOf(300.0)));
        when(ordersService.getWeeklySaleRecaps(4)).thenReturn(expectedSalesWeeklyRecap);

        ResponseEntity<List<SalesWeeklyRecap>> result = eCommerceController.getSaleRecapByWeek();

        assertEquals(expectedSalesWeeklyRecap, result.getBody());
        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void testGetMarketingCosts() {

        List<MarketingCost> expectedMarketingCosts = Arrays.asList(new MarketingCost(), new MarketingCost());
        when(marketingCostService.getAll()).thenReturn(expectedMarketingCosts);

        ResponseEntity<List<MarketingCost>> result = eCommerceController.getMarketingCosts();

        assertEquals(expectedMarketingCosts, result.getBody());
        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void testGetMarketingCostsGroupedByCategories() {

        Map<String, List<CategoryCost>> expectedCategoryCosts = new HashMap<>();
        expectedCategoryCosts.put("categories", Arrays.asList(new CategoryCost(), new CategoryCost()));
        expectedCategoryCosts.put("subcategories", Arrays.asList(new CategoryCost(), new CategoryCost()));
        when(marketingCostService.getTotalsByAllCategories()).thenReturn(expectedCategoryCosts);

        ResponseEntity<Map<String, List<CategoryCost>>> result = eCommerceController
                .getMarketingCostsGroupedByCategories();

        assertEquals(expectedCategoryCosts, result.getBody());
        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void testGetRecentCost() {

        List<MarketingCostRecap> expectedMarketingCostRecap = Arrays.asList(new MarketingCostRecap(),
                new MarketingCostRecap());
        when(marketingCostService.getRecentCost()).thenReturn(expectedMarketingCostRecap);

        ResponseEntity<List<MarketingCostRecap>> result = eCommerceController.getRecentCost();

        assertEquals(expectedMarketingCostRecap, result.getBody());
        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void testGetMarkingCostSum() {

        BigDecimal expectedSum = new BigDecimal("100.50");
        when(marketingCostService.getMarketingCostSum()).thenReturn(expectedSum);

        ResponseEntity<BigDecimal> result = eCommerceController.getMarkingCostSum();

        assertEquals(expectedSum, result.getBody());
        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void testGetBalanceWeeklyRecap() {

        List<BalanceWeeklyRecap> expectedBalanceWeeklyRecap = Arrays.asList(
                new BalanceWeeklyRecap("W-3", new BigDecimal("50.00"), 30.00, 20.00),
                new BalanceWeeklyRecap("W-5", new BigDecimal("10.00"), 40.00, 10.00));
        when(eCommerceService.getBalanceWeeklyRecap()).thenReturn(expectedBalanceWeeklyRecap);

        ResponseEntity<List<BalanceWeeklyRecap>> result = eCommerceController.getBalanceWeeklyRecap();

        assertEquals(expectedBalanceWeeklyRecap, result.getBody());
        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void testGetTotalBalance() {

        BigDecimal expectedTotalBalance = new BigDecimal("500.75");
        when(eCommerceService.getBalance()).thenReturn(expectedTotalBalance);

        ResponseEntity<BigDecimal> result = eCommerceController.getTotalBalance();

        assertEquals(expectedTotalBalance, result.getBody());
        assertEquals(200, result.getStatusCode().value());
    }

    @Test
    void testGetOrdersByLatestOrderWeek() {

        ECommerceDashboardCard expectedDashboardCard = new ECommerceDashboardCard();
        OrderCard orderCard = new OrderCard();
        orderCard.setTotalOrderCount(10L);
        orderCard.setLatestWeekPercentageChange(5.0);
        expectedDashboardCard.setOrderCard(orderCard);

        when(ordersService.getTotalOrderCount()).thenReturn(10L);
        when(ordersService.getOrderLatestWeekPercentageChange()).thenReturn(5.0);

        ResponseEntity<ECommerceDashboardCard> result = eCommerceController.getOrdersByLatestOrderWeek();

        assertEquals(expectedDashboardCard, result.getBody());
        assertEquals(200, result.getStatusCode().value());
    }

    private ShippingFeesMart createSampleShippingFeesMart() {
        return new ShippingFeesMart(1, new BigDecimal("100.00"), LocalDateTime.of(2023, 12, 11, 02, 03, 05));
    }

    private Order createSampleOrder() {
        return new Order(1, 1, "EBook", 1, LocalDate.now(), new BigDecimal("1.00"));
    }

    private OrderSummary createSampleOrderSummary() {
        return new OrderSummary(1, "John Doe", "EBook", LocalDate.now(), new BigDecimal("1.00"), OrderStatus.COMPLETED);
    }

    private SalesWeeklyRecap createSampleSalesRecap(String weekId, BigDecimal price) {
        return new SalesWeeklyRecap(weekId, price);
    }

    private ShippingFeesMart createSampleShippingFees() {
        return new ShippingFeesMart(1, new BigDecimal("10.00"), LocalDateTime.now());
    }

    private MarketingCostWeeklyRecap createSampleMarketingCostRecap(String weekId, double amount) {
        return new MarketingCostWeeklyRecap(weekId, amount);
    }

    private BalanceWeeklyRecap createSampleBalanceWeeklyRecap(String weekId, double amount) {
        return new BalanceWeeklyRecap("Week1", new BigDecimal("50.00"), 30.00, 20.00);
    }
}
