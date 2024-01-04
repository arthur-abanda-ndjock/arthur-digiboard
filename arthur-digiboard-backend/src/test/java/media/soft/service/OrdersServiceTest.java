package media.soft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import media.soft.model.Order;
import media.soft.model.OrderPriceByDate;
import media.soft.model.OrderStatus;
import media.soft.model.OrderSummary;
import media.soft.model.SalesWeeklyRecap;
import media.soft.repository.OrdersRepositoryDao;

class OrdersServiceTest {

    @Mock
    private OrdersRepositoryDao ordersRepositoryDao;

    @InjectMocks
    private OrdersService ordersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertOrders() {
        Order order = createSampleOrder();
        ordersService.insertOrders(order);
        verify(ordersRepositoryDao, times(1)).insert(order);
    }

    @Test
    void testUpdateOrdersById() {
        int orderId = 1;
        Order order = createSampleOrder();
        ordersService.updateOrdersById(orderId, order);
        verify(ordersRepositoryDao, times(1)).updateById(orderId, order);
    }

    @Test
    void testDeleteOrdersById() {
        int orderId = 1;
        ordersService.deleteOrdersById(orderId);
        verify(ordersRepositoryDao, times(1)).deleteById(orderId);
    }

    @Test
    void testGetAllOrderGroupedByDateWithThreeDates() {
        // Create sample orders with various product types on three unique dates
        Order ebookOrder1 = new Order(1, 1, "EBook", 1, LocalDate.now(), new BigDecimal("1.00"));
        Order ebookOrder2 = new Order(2, 2, "EBook", 2, LocalDate.now(), new BigDecimal("2.00"));

        Order softwareOrder1 = new Order(3, 3, "Software", 3, LocalDate.now(), new BigDecimal("3.00"));
        Order softwareOrder2 = new Order(4, 4, "Software", 4, LocalDate.now(), new BigDecimal("4.00"));

        Order musicOrder1 = new Order(5, 5, "DigitalMusic", 5, LocalDate.now(), new BigDecimal("5.00"));
        Order musicOrder2 = new Order(6, 6, "DigitalMusic", 6, LocalDate.now(), new BigDecimal("6.00"));

        Order courseOrder1 = new Order(7, 7, "OnlineCourse", 7, LocalDate.now().plusDays(1), new BigDecimal("7.00"));
        Order courseOrder2 = new Order(8, 8, "OnlineCourse", 8, LocalDate.now().plusDays(1), new BigDecimal("8.00"));

        Order streamingOrder1 = new Order(9, 9, "StreamingService", 9, LocalDate.now().plusDays(2),
                new BigDecimal("9.00"));
        Order streamingOrder2 = new Order(10, 10, "StreamingService", 10, LocalDate.now().plusDays(2),
                new BigDecimal("10.00"));

        List<Order> orders = Arrays.asList(
                ebookOrder1, ebookOrder2,
                softwareOrder1, softwareOrder2,
                musicOrder1, musicOrder2,
                courseOrder1, courseOrder2,
                streamingOrder1, streamingOrder2);

        when(ordersRepositoryDao.getAll()).thenReturn(orders);

        List<OrderPriceByDate> actualOrderPriceByDates = ordersService.getAllOrderGroupedByDate();

        // Verify the result for each product type and date
        assertEquals(3, actualOrderPriceByDates.size());

        // Assuming three unique dates
        OrderPriceByDate date1 = actualOrderPriceByDates.get(0);
        OrderPriceByDate date2 = actualOrderPriceByDates.get(1);
        OrderPriceByDate date3 = actualOrderPriceByDates.get(2);

        // Verify totals for each product type on each date
        assertEquals(new BigDecimal("3.00"), date1.getEbookPrice());
        assertEquals(new BigDecimal("7.00"), date1.getSoftwarePrice());
        assertEquals(new BigDecimal("11.00"), date1.getDigitalMusicPrice());
        assertEquals(new BigDecimal("0"), date1.getOnlineCoursePrice()); // Assuming no OnlineCourse on date1
        assertEquals(new BigDecimal("0"), date1.getStreamingServicePrice()); // Assuming no StreamingService on date1

        assertEquals(new BigDecimal("0"), date2.getEbookPrice()); // Assuming no EBook on date2
        assertEquals(new BigDecimal("0"), date2.getSoftwarePrice()); // Assuming no Software on date2
        assertEquals(new BigDecimal("0"), date2.getDigitalMusicPrice()); // Assuming no DigitalMusic on date2
        assertEquals(new BigDecimal("15.00"), date2.getOnlineCoursePrice());
        assertEquals(new BigDecimal("0"), date2.getStreamingServicePrice());

        assertEquals(new BigDecimal("0"), date3.getEbookPrice()); // Assuming no EBook on date3
        assertEquals(new BigDecimal("0"), date3.getSoftwarePrice()); // Assuming no Software on date3
        assertEquals(new BigDecimal("0"), date3.getDigitalMusicPrice()); // Assuming no DigitalMusic on date3
        assertEquals(new BigDecimal("0"), date3.getOnlineCoursePrice()); // Assuming no OnlineCourse on date3
        assertEquals(new BigDecimal("19.00"), date3.getStreamingServicePrice()); // Assuming no StreamingService on
                                                                                 // date3
    }

    @Test
    void testGetWeeklySaleRecapsWithThreeWeeks() {
        // Create sample orders for three consecutive weeks
        LocalDate today = LocalDate.now();
        Order weekMinus3Order1 = new Order(1, 1, "EBook", 1, today.minusWeeks(3), new BigDecimal("10.00"));
        Order weekMinus3Order2 = new Order(2, 2, "Software", 2, today.minusWeeks(3), new BigDecimal("10.00"));

        Order weekMinus2Order1 = new Order(1, 1, "EBook", 1, today.minusWeeks(2), new BigDecimal("10.00"));
        Order weekMinus2Order2 = new Order(2, 2, "Software", 2, today.minusWeeks(2), new BigDecimal("20.00"));

        Order weekMinus1Order1 = new Order(3, 3, "EBook", 3, today.minusWeeks(1), new BigDecimal("75.00"));
        Order weekMinus1Order2 = new Order(4, 4, "Software", 4, today.minusWeeks(1), new BigDecimal("25.00"));

        Order currentweekOrder1 = new Order(5, 5, "EBook", 5, today, new BigDecimal("25.00"));
        Order currentweekOrder2 = new Order(6, 6, "Software", 6, today, new BigDecimal("25.00"));

        List<Order> orders = Arrays.asList(
                weekMinus3Order1, weekMinus3Order2,
                weekMinus2Order1, weekMinus2Order2,
                weekMinus1Order1, weekMinus1Order2,
                currentweekOrder1, currentweekOrder2);

        when(ordersRepositoryDao.getLatestOrderDate()).thenReturn(today);

        when(ordersRepositoryDao.getOrdersByPeriod(any(LocalDate.class), any(LocalDate.class))).thenReturn(orders);

        WeekRange weekRangeMinus3 = getWeekRangeFromDate(today.minusWeeks(3));
        when(ordersRepositoryDao.getOrdersByPeriod(weekRangeMinus3.getStartOfWeek(), weekRangeMinus3.getEndOfWeek()))
                .thenReturn(Arrays.asList(
                        weekMinus3Order1, weekMinus3Order2));

        WeekRange weekRangeMinus2 = getWeekRangeFromDate(today.minusWeeks(2));
        when(ordersRepositoryDao.getOrdersByPeriod(weekRangeMinus2.getStartOfWeek(), weekRangeMinus2.getEndOfWeek()))
                .thenReturn(Arrays.asList(
                        weekMinus2Order1, weekMinus2Order2));

        WeekRange weekRangeMinus1 = getWeekRangeFromDate(today.minusWeeks(1));
        when(ordersRepositoryDao.getOrdersByPeriod(weekRangeMinus1.getStartOfWeek(), weekRangeMinus1.getEndOfWeek()))
                .thenReturn(Arrays.asList(
                        weekMinus1Order1, weekMinus1Order2));

        WeekRange weekRange = getWeekRangeFromDate(today);
        when(ordersRepositoryDao.getOrdersByPeriod(weekRange.getStartOfWeek(), weekRange.getEndOfWeek()))
                .thenReturn(Arrays.asList(
                        currentweekOrder1, currentweekOrder2));

        List<SalesWeeklyRecap> actualRecaps = ordersService.getWeeklySaleRecaps(3);

        // Assuming the latest order date is today
        assertEquals(4, actualRecaps.size());

        // Assuming three consecutive weeks
        SalesWeeklyRecap week1Recap = actualRecaps.get(0);
        SalesWeeklyRecap week2Recap = actualRecaps.get(1);
        SalesWeeklyRecap week3Recap = actualRecaps.get(2);
        SalesWeeklyRecap week4Recap = actualRecaps.get(3);

        // Verify the weekly sale recaps
        assertEquals("W-3", week1Recap.getWeekId());
        assertEquals(new BigDecimal("20.00"), week1Recap.getPrice());

        assertEquals("W-2", week2Recap.getWeekId());
        assertEquals(new BigDecimal("30.00"), week2Recap.getPrice());

        assertEquals("W-1", week3Recap.getWeekId());
        assertEquals(new BigDecimal("100.00"), week3Recap.getPrice());

        assertEquals("this week", week4Recap.getWeekId());
        assertEquals(new BigDecimal("50.00"), week4Recap.getPrice());
    }

    class WeekRange {
        private LocalDate startOfWeek;
        private LocalDate endOfWeek;

        public LocalDate getStartOfWeek() {
            return startOfWeek;
        }

        public void setStartOfWeek(LocalDate startOfWeek) {
            this.startOfWeek = startOfWeek;
        }

        public LocalDate getEndOfWeek() {
            return endOfWeek;
        }

        public void setEndOfWeek(LocalDate endOfWeek) {
            this.endOfWeek = endOfWeek;
        }
    }

    private WeekRange getWeekRangeFromDate(LocalDate currentDate) {
        LocalDate startOfWeek = currentDate
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        WeekRange range = new WeekRange();
        range.setStartOfWeek(startOfWeek);
        range.setEndOfWeek(endOfWeek);
        return range;
    }

    @Test
    void testGetOrderLatestWeekPercentageChange() {
        LocalDate latestOrderDate = LocalDate.now();
        when(ordersRepositoryDao.getLatestOrderDate()).thenReturn(latestOrderDate);

        // first test
        LocalDate startOfCurrentWeek = latestOrderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfCurrentWeek = latestOrderDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        when(ordersRepositoryDao.getOrdersByPeriod(startOfCurrentWeek, endOfCurrentWeek)).thenReturn(Arrays.asList(
                createSampleOrder(),
                createSampleOrder(),
                createSampleOrder()));

        LocalDate startOfPreviousWeek = latestOrderDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .minusWeeks(1);
        LocalDate endOfPreviousWeek = latestOrderDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
                .minusWeeks(1);

        when(ordersRepositoryDao.getOrdersByPeriod(startOfPreviousWeek, endOfPreviousWeek)).thenReturn(Arrays.asList(
                createSampleOrder(),
                createSampleOrder()));

        double percentageChange = ordersService.getOrderLatestWeekPercentageChange();
        assertEquals(50.0, percentageChange); // assert increase

        // 2nd test
        when(ordersRepositoryDao.getOrdersByPeriod(startOfCurrentWeek, endOfCurrentWeek)).thenReturn(Arrays.asList(
                createSampleOrder()));

        when(ordersRepositoryDao.getOrdersByPeriod(startOfPreviousWeek, endOfPreviousWeek)).thenReturn(Arrays.asList(
                createSampleOrder(),
                createSampleOrder(),
                createSampleOrder(),
                createSampleOrder()));

        percentageChange = ordersService.getOrderLatestWeekPercentageChange();
        assertEquals(-75.0, percentageChange); // assert drop

    }

    @Test
    void testGetRecentOrders() {
        List<OrderSummary> expectedOrderSummaries = Arrays.asList(
                createSampleOrderSummary(),
                createSampleOrderSummary());
        when(ordersRepositoryDao.getRecentOrders()).thenReturn(expectedOrderSummaries);

        List<OrderSummary> actualOrderSummaries = ordersService.getRecentOrders();

        assertEquals(expectedOrderSummaries.size(), actualOrderSummaries.size());
        assertEquals(expectedOrderSummaries, actualOrderSummaries);
    }

    @Test
    void testGetTotalOrderCount() {
        when(ordersRepositoryDao.getTotalCount()).thenReturn(42L);

        Long totalOrderCount = ordersService.getTotalOrderCount();

        assertEquals(42L, totalOrderCount);
    }

    @Test
    void testGetAllOrders() {
        List<Order> expectedOrders = Arrays.asList(
                createSampleOrder(),
                createSampleOrder());
        when(ordersRepositoryDao.getAll()).thenReturn(expectedOrders);

        List<Order> actualOrders = ordersService.getAllOrders();

        assertEquals(expectedOrders.size(), actualOrders.size());
        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    void testGetOrderAmountSum() {
        BigDecimal expectedSum = new BigDecimal("42.00");
        when(ordersRepositoryDao.getOrderAmountSum()).thenReturn(expectedSum);

        BigDecimal actualSum = ordersService.getOrderAmountSum();

        assertEquals(expectedSum, actualSum);
    }

    private Order createSampleOrder() {
        return new Order(1, 1, "EBook", 1, LocalDate.now(), new BigDecimal("1.00"));
    }

    private OrderSummary createSampleOrderSummary() {
        return new OrderSummary(1, "John Doe", "EBook", LocalDate.now(), new BigDecimal("1.00"), OrderStatus.COMPLETED);
    }
}
