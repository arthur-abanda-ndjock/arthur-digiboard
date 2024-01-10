package media.soft.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import media.soft.model.CategoryCost;
import media.soft.model.MarketingCost;
import media.soft.model.MarketingCostRecap;

@ExtendWith(MockitoExtension.class)
class MarketingCostsRepositoryDaoTest {

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @InjectMocks
    private MarketingCostsRepositoryDao marketingCostsRepositoryDao;

    @Test
    void testInsert() {
        // Mocking data
        MarketingCost marketingCost = new MarketingCost();
        marketingCost.setSubcategoryId(1);
        marketingCost.setCost(100.0);
        marketingCost.setDateRecorded(LocalDate.now());

        // Testing the insert method
        marketingCostsRepositoryDao.insert(marketingCost);

        // Verifying that the update method of NamedParameterJdbcTemplate is called
        verify(namedParameterJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testUpdateById() {
        // Mocking data
        int id = 1;
        MarketingCost marketingCost = new MarketingCost();
        marketingCost.setSubcategoryId(1);
        marketingCost.setCost(200.0);
        marketingCost.setDateRecorded(LocalDate.now());

        // Testing the updateById method
        marketingCostsRepositoryDao.updateById(id, marketingCost);

        // Verifying that the update method of NamedParameterJdbcTemplate is called
        verify(namedParameterJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testDeleteById() {
        // Mocking data
        int id = 1;

        // Testing the deleteById method
        marketingCostsRepositoryDao.deleteById(id);

        // Verifying that the update method of NamedParameterJdbcTemplate is called
        verify(namedParameterJdbcTemplate).update(anyString(), anyMap());
    }

    @Test
    void testGetAll() {
        // Mocking data
        List<MarketingCost> expectedMarketingCosts = Collections.singletonList(new MarketingCost());

        // Mocking the behavior of namedJdbcTemplate
        when(namedParameterJdbcTemplate.query(anyString(),
                any(MarketingCostsRepositoryDao.MarketingCostsRowMapper.class)))
                .thenReturn(expectedMarketingCosts);

        // Testing the getAll method
        List<MarketingCost> actualMarketingCosts = marketingCostsRepositoryDao.getAll();

        // Verifying that the query method of NamedParameterJdbcTemplate is called
        verify(namedParameterJdbcTemplate).query(anyString(),
                any(MarketingCostsRepositoryDao.MarketingCostsRowMapper.class));

        // Verifying the correctness of the result
        assertEquals(expectedMarketingCosts, actualMarketingCosts);
    }

    @Test
    void testGetMarketingCostSum() {
        // Mocking the behavior of namedJdbcTemplate
        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(SqlParameterSource.class),
                eq(BigDecimal.class)))
                .thenReturn(BigDecimal.TEN);

        // Testing the getMarketingCostSum method
        BigDecimal actualSum = marketingCostsRepositoryDao.getMarketingCostSum();

        // Verifying that the queryForObject method of NamedParameterJdbcTemplate is
        // called
        verify(namedParameterJdbcTemplate).queryForObject(anyString(), any(SqlParameterSource.class),
                eq(BigDecimal.class));

        // Verifying the correctness of the result
        assertEquals(BigDecimal.TEN, actualSum);
    }

    @Test
    void testGetRecentCost() {
        // Mocking data
        List<MarketingCostRecap> expectedRecentCosts = Collections.singletonList(new MarketingCostRecap());

        // Mocking the behavior of namedJdbcTemplate
        when(namedParameterJdbcTemplate.query(anyString(),
                any(MarketingCostsRepositoryDao.MarketingCostRecapRowMapper.class)))
                .thenReturn(expectedRecentCosts);

        // Testing the getRecentCost method
        List<MarketingCostRecap> actualRecentCosts = marketingCostsRepositoryDao.getRecentCost();

        // Verifying that the query method of NamedParameterJdbcTemplate is called
        verify(namedParameterJdbcTemplate).query(anyString(),
                any(MarketingCostsRepositoryDao.MarketingCostRecapRowMapper.class));

        // Verifying the correctness of the result
        assertEquals(expectedRecentCosts, actualRecentCosts);
    }

    @Test
    void testGetTotalAmountByCategory() {
        // Mocking data
        List<CategoryCost> expectedTotalAmounts = Collections.singletonList(new CategoryCost());

        // Mocking the behavior of namedJdbcTemplate
        when(namedParameterJdbcTemplate.query(anyString(),
                any(MarketingCostsRepositoryDao.CategoryCostRowMapper.class)))
                .thenReturn(expectedTotalAmounts);

        // Testing the getTotalAmountByCategory method
        List<CategoryCost> actualTotalAmounts = marketingCostsRepositoryDao.getTotalAmountByCategory();

        // Verifying that the query method of NamedParameterJdbcTemplate is called
        verify(namedParameterJdbcTemplate).query(anyString(),
                any(MarketingCostsRepositoryDao.CategoryCostRowMapper.class));

        // Verifying the correctness of the result
        assertEquals(expectedTotalAmounts, actualTotalAmounts);
    }

    @Test
    void testGetTotalAmountBySubcategory() {
        // Mocking data
        List<CategoryCost> expectedTotalAmounts = Collections.singletonList(new CategoryCost());

        // Mocking the behavior of namedJdbcTemplate
        when(namedParameterJdbcTemplate.query(anyString(),
                any(MarketingCostsRepositoryDao.CategoryCostRowMapper.class)))
                .thenReturn(expectedTotalAmounts);

        // Testing the getTotalAmountBySubcategory method
        List<CategoryCost> actualTotalAmounts = marketingCostsRepositoryDao.getTotalAmountBySubcategory();

        // Verifying that the query method of NamedParameterJdbcTemplate is called
        verify(namedParameterJdbcTemplate).query(anyString(),
                any(MarketingCostsRepositoryDao.CategoryCostRowMapper.class));

        // Verifying the correctness of the result
        assertEquals(expectedTotalAmounts, actualTotalAmounts);
    }

    @Test
    void testGetMarketingCostsByPeriod() {
        // Mocking data
        LocalDate start = LocalDate.now().minusDays(10);
        LocalDate end = LocalDate.now();
        List<MarketingCost> expectedMarketingCosts = Collections.singletonList(new MarketingCost());

        // Mocking the behavior of namedJdbcTemplate
        when(namedParameterJdbcTemplate.query(anyString(), any(SqlParameterSource.class),
                any(MarketingCostsRepositoryDao.MarketingCostsRowMapper.class)))
                .thenReturn(expectedMarketingCosts);

        // Testing the getMarketingCostsByPeriod method
        List<MarketingCost> actualMarketingCosts = marketingCostsRepositoryDao.getMarketingCostsByPeriod(start, end);

        // Verifying that the query method of NamedParameterJdbcTemplate is called
        verify(namedParameterJdbcTemplate).query(anyString(), any(SqlParameterSource.class),
                any(MarketingCostsRepositoryDao.MarketingCostsRowMapper.class));

        // Verifying the correctness of the result
        assertEquals(expectedMarketingCosts, actualMarketingCosts);
    }

    @Test
    void testGetLatestMarketingCostDate() {
        // Mocking data
        LocalDate expectedDate = LocalDate.now();

        // Mocking the behavior of namedJdbcTemplate
        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(SqlParameterSource.class), eq(LocalDate.class)))
                .thenReturn(expectedDate);

        // Testing the getLatestMarketingCostDate method
        LocalDate actualDate = marketingCostsRepositoryDao.getLatestMarketingCostDate();

        // Verifying that the queryForObject method of NamedParameterJdbcTemplate is
        // called
        verify(namedParameterJdbcTemplate).queryForObject(anyString(), any(SqlParameterSource.class),
                eq(LocalDate.class));

        // Verifying the correctness of the result
        assertEquals(expectedDate, actualDate);
    }

    // RowMapper
    @Test
    void testCategoryCostRowMapper() throws SQLException {
        // Mocking data
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("name")).thenReturn("TestCategory");
        when(resultSet.getDouble("totalCost")).thenReturn(123.45);

        // Testing the CategoryCostRowMapper
        MarketingCostsRepositoryDao.CategoryCostRowMapper rowMapper = new MarketingCostsRepositoryDao.CategoryCostRowMapper();
        CategoryCost categoryCost = rowMapper.mapRow(resultSet, 1);

        // Verifying the correctness of the result
        assertEquals("TestCategory", categoryCost.getName());
        assertEquals(123.45, categoryCost.getTotalCost());
    }

    // RowMapper
    @Test
    void testMarketingCostsRowMapper() throws SQLException {
        // Mocking data
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getInt("subcategory_id")).thenReturn(2);
        when(resultSet.getDouble("cost")).thenReturn(100.0);
        when(resultSet.getDate("date_recorded")).thenReturn(java.sql.Date.valueOf(LocalDate.now()));

        // Testing the MarketingCostsRowMapper
        MarketingCostsRepositoryDao.MarketingCostsRowMapper rowMapper = new MarketingCostsRepositoryDao.MarketingCostsRowMapper();
        MarketingCost marketingCost = rowMapper.mapRow(resultSet, 1);

        // Verifying the correctness of the result
        assertEquals(1, marketingCost.getId());
        assertEquals(2, marketingCost.getSubcategoryId());
        assertEquals(100.0, marketingCost.getCost());
        assertEquals(LocalDate.now(), marketingCost.getDateRecorded());
    }

    // RowMapper
    @Test
    void testMarketingCostRecapRowMapper() throws SQLException {
        // Mocking data
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("category_name")).thenReturn("TestCategory");
        when(resultSet.getString("subcategory_name")).thenReturn("TestSubcategory");
        when(resultSet.getDouble("cost")).thenReturn(200.0);
        when(resultSet.getDate("date_recorded")).thenReturn(java.sql.Date.valueOf(LocalDate.now()));

        // Testing the MarketingCostRecapRowMapper
        MarketingCostsRepositoryDao.MarketingCostRecapRowMapper rowMapper = new MarketingCostsRepositoryDao.MarketingCostRecapRowMapper();
        MarketingCostRecap marketingCostRecap = rowMapper.mapRow(resultSet, 1);

        // Verifying the correctness of the result
        assertEquals(1, marketingCostRecap.getCostId());
        assertEquals("TestCategory", marketingCostRecap.getCategoryName());
        assertEquals("TestSubcategory", marketingCostRecap.getSubcategoryName());
        assertEquals(200.0, marketingCostRecap.getCost());
        assertEquals(LocalDate.now(), marketingCostRecap.getDateRecorded());
    }
}