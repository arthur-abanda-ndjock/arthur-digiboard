package media.soft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import media.soft.model.CategoryCost;
import media.soft.model.MarketingCost;
import media.soft.model.MarketingCostRecap;
import media.soft.model.MarketingCostWeeklyRecap;
import media.soft.repository.MarketingCostsRepositoryDao;

class MarketingCostServiceTest {

    @Mock
    private MarketingCostsRepositoryDao marketingCostDao;

    @InjectMocks
    private MarketingCostService marketingCostService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertPrdStreamingServices() {
        MarketingCost marketingCost = createSampleMarketingCost();
        marketingCostService.insertPrdStreamingServices(marketingCost);
        verify(marketingCostDao, times(1)).insert(marketingCost);
    }

    @Test
    void testUpdatePrdStreamingServicesById() {
        int id = 1;
        MarketingCost marketingCost = createSampleMarketingCost();
        marketingCostService.updatePrdStreamingServicesById(id, marketingCost);
        verify(marketingCostDao, times(1)).updateById(id, marketingCost);
    }

    @Test
    void testDeletePrdStreamingServicesById() {
        int id = 1;
        marketingCostService.deletePrdStreamingServicesById(id);
        verify(marketingCostDao, times(1)).deleteById(id);
    }

    @Test
    void testGetAll() {
        List<MarketingCost> expectedMarketingCosts = Arrays.asList(
                createSampleMarketingCost(),
                createSampleMarketingCost());
        when(marketingCostDao.getAll()).thenReturn(expectedMarketingCosts);

        List<MarketingCost> actualMarketingCosts = marketingCostService.getAll();

        assertEquals(expectedMarketingCosts.size(), actualMarketingCosts.size());
        assertEquals(expectedMarketingCosts, actualMarketingCosts);
    }

    @Test
    void testGetTotalAmountsByCategory() {
        List<CategoryCost> expectedCategoryCosts = Arrays.asList(
                createSampleCategoryCost(),
                createSampleCategoryCost());
        when(marketingCostDao.getTotalAmountByCategory()).thenReturn(expectedCategoryCosts);

        List<CategoryCost> actualCategoryCosts = marketingCostService.getTotalAmountsByCategory();

        assertEquals(expectedCategoryCosts.size(), actualCategoryCosts.size());
        assertEquals(expectedCategoryCosts, actualCategoryCosts);
    }

    @Test
    void testGetTotalAmountsBySubcategory() {
        List<CategoryCost> expectedSubcategoryCosts = Arrays.asList(
                createSampleCategoryCost(),
                createSampleCategoryCost());
        when(marketingCostDao.getTotalAmountBySubcategory()).thenReturn(expectedSubcategoryCosts);

        List<CategoryCost> actualSubcategoryCosts = marketingCostService.getTotalAmountsBySubcategory();

        assertEquals(expectedSubcategoryCosts.size(), actualSubcategoryCosts.size());
        assertEquals(expectedSubcategoryCosts, actualSubcategoryCosts);
    }

    @Test
    void testGetRecentCost() {
        List<MarketingCostRecap> expectedRecap = Arrays.asList(
                createSampleMarketingCostRecap(),
                createSampleMarketingCostRecap());
        when(marketingCostDao.getRecentCost()).thenReturn(expectedRecap);

        List<MarketingCostRecap> actualRecap = marketingCostService.getRecentCost();

        assertEquals(expectedRecap.size(), actualRecap.size());
        assertEquals(expectedRecap, actualRecap);
    }

    @Test
    void testGetTotalsByAllCategories() {
        List<CategoryCost> expectedCategoryCosts = Arrays.asList(
                createSampleCategoryCost(),
                createSampleCategoryCost());
        List<CategoryCost> expectedSubcategoryCosts = Arrays.asList(
                createSampleCategoryCost(),
                createSampleCategoryCost());
        when(marketingCostDao.getTotalAmountByCategory()).thenReturn(expectedCategoryCosts);
        when(marketingCostDao.getTotalAmountBySubcategory()).thenReturn(expectedSubcategoryCosts);

        Map<String, List<CategoryCost>> actualTotalsByAllCategories = marketingCostService.getTotalsByAllCategories();

        assertEquals(2, actualTotalsByAllCategories.size());
        assertEquals(expectedCategoryCosts, actualTotalsByAllCategories.get("categories"));
        assertEquals(expectedSubcategoryCosts, actualTotalsByAllCategories.get("subcategories"));
    }

    @Test
    void testGetMarketingCostRecapByWeek() {
        int maxRecapWeeks = 5;
        List<MarketingCostWeeklyRecap> expectedRecap = Arrays.asList(
                createSampleMarketingCostWeeklyRecap("W-5"),
                createSampleMarketingCostWeeklyRecap("W-4"),
                createSampleMarketingCostWeeklyRecap("W-3"),
                createSampleMarketingCostWeeklyRecap("W-2"),
                createSampleMarketingCostWeeklyRecap("W-1"),
                createSampleMarketingCostWeeklyRecap("this week"));
        when(marketingCostDao.getLatestMarketingCostDate()).thenReturn(LocalDate.now());
        when(marketingCostDao.getMarketingCostsByPeriod(any(), any())).thenReturn(
                Arrays.asList(
                        createSampleMarketingCost(),
                        createSampleMarketingCost()));

        List<MarketingCostWeeklyRecap> actualRecap = marketingCostService.getMarketingCostRecapByWeek(maxRecapWeeks);

        assertEquals(expectedRecap.size(), actualRecap.size());
        assertEquals(expectedRecap, actualRecap);
    }

    @Test
    void testGetMarketingCostSum() {
        BigDecimal expectedSum = new BigDecimal("100.00");
        when(marketingCostDao.getMarketingCostSum()).thenReturn(expectedSum);

        BigDecimal actualSum = marketingCostService.getMarketingCostSum();

        assertEquals(expectedSum, actualSum);
    }

    private MarketingCost createSampleMarketingCost() {

        MarketingCost marketingCost = new MarketingCost();
        marketingCost.setId(1);
        marketingCost.setSubcategoryId(1);
        marketingCost.setCost(50.0);
        marketingCost.setDateRecorded(LocalDate.now());

        return marketingCost;
    }

    private CategoryCost createSampleCategoryCost() {

        CategoryCost marketingCost = new CategoryCost();
        marketingCost.setName("Category");
        marketingCost.setTotalCost(100.0);

        return marketingCost;
    }

    private MarketingCostRecap createSampleMarketingCostRecap() {
        MarketingCostRecap costRecap = new MarketingCostRecap();
        costRecap.setCostId(1);
        costRecap.setCategoryName("Category");
        costRecap.setSubcategoryName("Subcategory");
        costRecap.setCost(50.0);
        costRecap.setDateRecorded(LocalDate.now());
        return costRecap;

    }

    private MarketingCostWeeklyRecap createSampleMarketingCostWeeklyRecap(String weekId) {
        return new MarketingCostWeeklyRecap(weekId, 100.0);
    }
}
