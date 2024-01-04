package media.soft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import media.soft.model.BalanceWeeklyRecap;
import media.soft.model.MarketingCostWeeklyRecap;
import media.soft.model.SalesWeeklyRecap;
import media.soft.model.ShippingFeesMart;
import media.soft.repository.ShippingFeesMartDao;

class ECommerceServiceTest {

    @Mock
    private ShippingFeesMartDao shippingFeesMartDao;

    @Mock
    private OrdersService ordersService;

    @Mock
    private MarketingCostService marketingCostService;

    @InjectMocks
    private ECommerceService eCommerceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllShippingFees() {
        List<ShippingFeesMart> expectedShippingFees = Arrays.asList(
                createSampleShippingFees(),
                createSampleShippingFees());
        when(shippingFeesMartDao.getAllData()).thenReturn(expectedShippingFees);

        List<ShippingFeesMart> actualShippingFees = eCommerceService.getAllShippingFees();

        assertEquals(expectedShippingFees.size(), actualShippingFees.size());
        assertTrue(actualShippingFees.containsAll(expectedShippingFees));
    }

    @Test
    void testGetBalanceWeeklyRecap() {
        List<MarketingCostWeeklyRecap> marketingCostRecaps = Arrays.asList(
                createSampleMarketingCostRecap("Week1", 30.00),
                createSampleMarketingCostRecap("Week2", 40.00));
        List<SalesWeeklyRecap> salesRecaps = Arrays.asList(
                createSampleSalesRecap("Week1", new BigDecimal("50.00")),
                createSampleSalesRecap("Week2", new BigDecimal("60.00")));
        when(marketingCostService.getMarketingCostRecapByWeek(10)).thenReturn(marketingCostRecaps);
        when(ordersService.getWeeklySaleRecaps(10)).thenReturn(salesRecaps);

        List<BalanceWeeklyRecap> balanceWeeklyRecaps = eCommerceService.getBalanceWeeklyRecap();

        assertEquals(2, balanceWeeklyRecaps.size());
        assertBalanceWeeklyRecap(balanceWeeklyRecaps.get(0), "Week1", new BigDecimal("50.00"), 30.00, 20.00);
        assertBalanceWeeklyRecap(balanceWeeklyRecaps.get(1), "Week2", new BigDecimal("60.00"), 40.00, 20.00);
    }

    @Test
    void testGetBalance() {
        BigDecimal orderAmountSum = new BigDecimal("100.00");
        BigDecimal marketingCostSum = new BigDecimal("20.00");
        when(ordersService.getOrderAmountSum()).thenReturn(orderAmountSum);
        when(marketingCostService.getMarketingCostSum()).thenReturn(marketingCostSum);

        BigDecimal balance = eCommerceService.getBalance();

        assertEquals(new BigDecimal("80.00"), balance);
    }

    private void assertBalanceWeeklyRecap(BalanceWeeklyRecap actual, String weekId, BigDecimal eowPrice,
            double eowCost, double eowBalance) {
        assertEquals(weekId, actual.getWeekId());
        assertEquals(eowPrice, actual.getEowPrice());
        assertEquals(eowCost, actual.getEowCost());
        assertEquals(eowBalance, actual.getEowBalance());
    }

    private ShippingFeesMart createSampleShippingFees() {
        return new ShippingFeesMart(1, new BigDecimal("10.00"), LocalDateTime.now());
    }

    private MarketingCostWeeklyRecap createSampleMarketingCostRecap(String weekId, double amount) {
        return new MarketingCostWeeklyRecap(weekId, amount);
    }

    private SalesWeeklyRecap createSampleSalesRecap(String weekId, BigDecimal price) {
        return new SalesWeeklyRecap(weekId, price);
    }
}
