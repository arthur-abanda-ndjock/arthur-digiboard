package media.soft.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import media.soft.model.BalanceWeeklyRecap;
import media.soft.model.CategoryCost;
import media.soft.model.ECommerceDashboardCard;
import media.soft.model.MarketingCost;
import media.soft.model.MarketingCostRecap;
import media.soft.model.OrderCard;
import media.soft.model.OrderPriceByDate;
import media.soft.model.SalesWeeklyRecap;
import media.soft.service.ECommerceService;
import media.soft.service.MarketingCostService;
import media.soft.service.OrdersService;

@WebMvcTest(ECommerceController.class)
class ECommerceControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ECommerceService eCommerceService;

    @MockBean
    private OrdersService ordersService;

    @MockBean
    private MarketingCostService marketingCostService;

    @Test
    void testGetAllOrdersByDate() throws Exception {

        OrderPriceByDate priceByDate = new OrderPriceByDate();

        priceByDate.setOrderDate(LocalDate.now());
        priceByDate.setSoftwarePrice(BigDecimal.TEN);
        priceByDate.setEbookPrice(BigDecimal.ZERO);
        priceByDate.setDigitalMusicPrice(BigDecimal.ONE);
        priceByDate.setOnlineCoursePrice(BigDecimal.valueOf(5.0));
        priceByDate.setStreamingServicePrice(BigDecimal.ZERO);

        OrderPriceByDate priceByDate2 = new OrderPriceByDate();

        priceByDate2.setOrderDate(LocalDate.now());
        priceByDate2.setSoftwarePrice(BigDecimal.valueOf(1.0));
        priceByDate2.setEbookPrice(BigDecimal.valueOf(2.0));
        priceByDate2.setDigitalMusicPrice(BigDecimal.valueOf(3.0));
        priceByDate2.setOnlineCoursePrice(BigDecimal.valueOf(4.0));
        priceByDate2.setStreamingServicePrice(BigDecimal.valueOf(5.0));

        List<OrderPriceByDate> orderPriceByDates = Arrays.asList(
                priceByDate, priceByDate2);

        when(ordersService.getAllOrderGroupedByDate()).thenReturn(orderPriceByDates);

        ResultActions result = mockMvc.perform(get("/api/ecommerce/orders/by-date")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].orderDate", is(notNullValue())))
                .andExpect(jsonPath("$[0].softwarePrice", is(equalTo(10))))
                .andExpect(jsonPath("$[0].onlineCoursePrice", is(equalTo(5.0))))
                .andExpect(jsonPath("$[0].ebookPrice", is(equalTo(0))))
                .andExpect(jsonPath("$[0].digitalMusicPrice", is(equalTo(1))))
                .andExpect(jsonPath("$[0].streamingServicePrice", is(equalTo(0))))
                .andExpect(jsonPath("$[1].orderDate", is(notNullValue())))
                .andExpect(jsonPath("$[1].softwarePrice", is(equalTo(1.0))))
                .andExpect(jsonPath("$[1].onlineCoursePrice", is(equalTo(4.0))))
                .andExpect(jsonPath("$[1].ebookPrice", is(equalTo(2.0))))
                .andExpect(jsonPath("$[1].digitalMusicPrice", is(equalTo(3.0))))
                .andExpect(jsonPath("$[1].streamingServicePrice", is(equalTo(5.0))));
    }

    @Test
    void testGetSaleRecapByWeek() throws Exception {

        List<SalesWeeklyRecap> salesWeeklyRecaps = Arrays.asList(
                new SalesWeeklyRecap("Week1", BigDecimal.valueOf(100.0))

        );

        when(ordersService.getWeeklySaleRecaps(4)).thenReturn(salesWeeklyRecaps);

        ResultActions result = mockMvc.perform(get("/api/ecommerce/orders/weekly-sales")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].weekId", is(equalTo("Week1"))))
                .andExpect(jsonPath("$[0].price", is(equalTo(100.0))));
    }

    @Test
    void testGetMarketingCosts() throws Exception {

        MarketingCost cost = new MarketingCost();
        cost.setId(1);
        cost.setSubcategoryId(1);
        cost.setDateRecorded(LocalDate.now());
        cost.setCost(50.0);

        List<MarketingCost> marketingCosts = Arrays.asList(
                cost, cost);

        when(marketingCostService.getAll()).thenReturn(marketingCosts);

        ResultActions result = mockMvc.perform(get("/api/ecommerce/costs/all")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(equalTo(1))))
                .andExpect(jsonPath("$[0].cost", is(equalTo(50.0))));
    }

    @Test
    void testGetMarketingCostsGroupedByCategories() throws Exception {

        CategoryCost cost = new CategoryCost();
        cost.setName("SubCategory1");
        cost.setTotalCost(50.0);

        CategoryCost cost2 = new CategoryCost();
        cost2.setName("SubCategory2");
        cost2.setTotalCost(150.0);

        Map<String, List<CategoryCost>> categoryCostMap = Collections.singletonMap("categories", Arrays.asList(
                cost,
                cost2

        ));

        when(marketingCostService.getTotalsByAllCategories()).thenReturn(categoryCostMap);

        ResultActions result = mockMvc.perform(get("/api/ecommerce/costs/categories")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)))
                .andExpect(jsonPath("$['categories'][0].name", is(equalTo("SubCategory1"))))
                .andExpect(jsonPath("$['categories'][0].totalCost", is(equalTo(50.0))))
                .andExpect(jsonPath("$['categories'][1].name", is(equalTo("SubCategory2"))))
                .andExpect(jsonPath("$['categories'][1].totalCost", is(equalTo(150.0))));
    }

    @Test
    void testGetRecentCost() throws Exception {

        MarketingCostRecap recap = new MarketingCostRecap();
        recap.setCostId(1);
        recap.setSubcategoryName("SubCategory1");
        recap.setCategoryName("Category1");
        recap.setDateRecorded(LocalDate.now());
        recap.setCost(50.0);

        List<MarketingCostRecap> marketingCostRecaps = Arrays.asList(
                recap);

        when(marketingCostService.getRecentCost()).thenReturn(marketingCostRecaps);

        ResultActions result = mockMvc.perform(get("/api/ecommerce/costs/recents")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].costId", is(equalTo(1))))
                .andExpect(jsonPath("$[0].subcategoryName", is(equalTo("SubCategory1"))))
                .andExpect(jsonPath("$[0].cost", is(equalTo(50.0))));
    }

    @Test
    void testGetMarkingCostSum() throws Exception {

        BigDecimal costSum = BigDecimal.valueOf(150.0);

        when(marketingCostService.getMarketingCostSum()).thenReturn(costSum);

        ResultActions result = mockMvc.perform(get("/api/ecommerce/costs/sum")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", is(equalTo(150.0))));
    }

    @Test
    void testGetBalanceWeeklyRecap() throws Exception {

        List<BalanceWeeklyRecap> balanceWeeklyRecaps = Arrays.asList(
                new BalanceWeeklyRecap("Week1", BigDecimal.valueOf(1000.0), 500.0, 500.0)

        );

        when(eCommerceService.getBalanceWeeklyRecap()).thenReturn(balanceWeeklyRecaps);

        ResultActions result = mockMvc.perform(get("/api/ecommerce/balance/weekly-recap")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].weekId", is(equalTo("Week1"))))
                .andExpect(jsonPath("$[0].eowPrice", is(equalTo(1000.0))))
                .andExpect(jsonPath("$[0].eowCost", is(equalTo(500.0))))
                .andExpect(jsonPath("$[0].eowBalance", is(equalTo(500.0))));
    }

    @Test
    void testGetTotalBalance() throws Exception {
        // Mocking
        BigDecimal totalBalance = BigDecimal.valueOf(5000.0);

        when(eCommerceService.getBalance()).thenReturn(totalBalance);

        ResultActions result = mockMvc.perform(get("/api/ecommerce/balance")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", is(equalTo(5000.0))));
    }

    @Test
    void testGetOrdersByLatestOrderWeek() throws Exception {

        ECommerceDashboardCard dashboardCard = new ECommerceDashboardCard();
        OrderCard orderCard = new OrderCard();
        orderCard.setLatestWeekPercentageChange(100);
        orderCard.setTotalOrderCount(0);
        dashboardCard.setOrderCard(orderCard);

        when(ordersService.getTotalOrderCount()).thenReturn(100L);
        when(ordersService.getOrderLatestWeekPercentageChange()).thenReturn(10.0);

        ResultActions result = mockMvc.perform(get("/api/ecommerce/dashboard-cards")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.orderCard.totalOrderCount", is(equalTo(100))))
                .andExpect(jsonPath("$.orderCard.latestWeekPercentageChange", is(equalTo(10.0))));
    }
}
