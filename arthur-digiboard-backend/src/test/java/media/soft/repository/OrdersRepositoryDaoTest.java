package media.soft.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import media.soft.model.Order;
import media.soft.model.OrderStatus;
import media.soft.model.OrderSummary;

@ExtendWith(MockitoExtension.class)
class OrdersRepositoryDaoTest {

    @Mock
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @InjectMocks
    OrdersRepositoryDao ordersRepositoryDao;

    @Test
    void testInsert() {
        int orderID = 1;
        int customerID = 2;
        String productType = "UpdatedProduct";
        int productID = 4;
        LocalDate orderDate = LocalDate.now();
        BigDecimal price = BigDecimal.valueOf(20.0);
        Order order = new Order(orderID, customerID, productType, productID, orderDate, price);

        ordersRepositoryDao.insert(order);

        verify(namedJdbcTemplate).update(anyString(), ArgumentMatchers.<String, Object>anyMap());
    }

    @Test
    void testUpdateById() {
        int orderID = 1;
        int customerID = 2;
        String productType = "UpdatedProduct";
        int productID = 4;
        LocalDate orderDate = LocalDate.now();
        BigDecimal price = BigDecimal.valueOf(20.0);
        Order order = new Order(orderID, customerID, productType, productID, orderDate, price);

        ordersRepositoryDao.updateById(orderID, order);

        verify(namedJdbcTemplate).update(anyString(), ArgumentMatchers.<String, Object>anyMap());
    }

    @Test
    void testDeleteById() {
        int orderId = 1;

        ordersRepositoryDao.deleteById(orderId);

        verify(namedJdbcTemplate).update(anyString(), ArgumentMatchers.<String, Object>anyMap());
    }

    @Test
    void testGetAll() throws SQLException {

        int orderID = 1;
        int customerID = 2;
        String productType = "UpdatedProduct";
        int productID = 4;
        LocalDate orderDate = LocalDate.now();
        BigDecimal price = BigDecimal.valueOf(20.0);
        Order order = new Order(orderID, customerID, productType, productID, orderDate, price);

        when(namedJdbcTemplate.query(anyString(), any(OrdersRepositoryDao.OrderRowMapper.class)))
                .thenReturn(Collections.singletonList(order));

        List<Order> actualOrders = ordersRepositoryDao.getAll();

        verify(namedJdbcTemplate).query(anyString(), any(OrdersRepositoryDao.OrderRowMapper.class));

        assertEquals(1, actualOrders.size());
        assertEquals(order.getCustomerID(), actualOrders.get(0).getCustomerID());
        assertEquals(order.getProductID(), actualOrders.get(0).getProductID());
        assertEquals(order.getProductType(), actualOrders.get(0).getProductType());
        assertEquals(order.getPrice(), actualOrders.get(0).getPrice());
        assertEquals(order.getOrderDate(), actualOrders.get(0).getOrderDate());
        assertEquals(order.getOrderID(), actualOrders.get(0).getOrderID());

    }

    @Test
    void testGetRecentOrders() throws SQLException {

        int orderID = 1;
        String customerFullName = "FullName1";
        String productType = "UpdatedProduct";
        LocalDate orderDate = LocalDate.now();
        BigDecimal price = BigDecimal.valueOf(20.0);
        OrderStatus status = OrderStatus.COMPLETED;

        OrderSummary order = new OrderSummary(orderID, customerFullName, productType, orderDate, price, status);

        when(namedJdbcTemplate.query(anyString(), any(OrdersRepositoryDao.OrderSummaryRowMapper.class)))
                .thenReturn(Collections
                        .singletonList(order));

        List<OrderSummary> actualOrderSummaries = ordersRepositoryDao.getRecentOrders();

        verify(namedJdbcTemplate).query(anyString(), any(OrdersRepositoryDao.OrderSummaryRowMapper.class));

        assertEquals(1,
                actualOrderSummaries.size());
    }

    @Test
    void testGetLatestOrderDate() {
        LocalDate expectedDate = LocalDate.now();

        when(namedJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), eq(LocalDate.class)))
                .thenReturn(expectedDate);

        LocalDate actualDate = ordersRepositoryDao.getLatestOrderDate();

        verify(namedJdbcTemplate).queryForObject(anyString(), any(MapSqlParameterSource.class), eq(LocalDate.class));

        assertEquals(expectedDate, actualDate);
    }

    @Test
    void testGetOrdersByPeriod() throws SQLException {
        when(namedJdbcTemplate.query(anyString(), any(MapSqlParameterSource.class),
                any(OrdersRepositoryDao.OrderRowMapper.class)))
                .thenReturn(Collections.singletonList(new Order(1, 2, "Product", 3, LocalDate.now(), BigDecimal.TEN)));

        List<Order> actualOrders = ordersRepositoryDao.getOrdersByPeriod(LocalDate.now().minusDays(7), LocalDate.now());

        verify(namedJdbcTemplate).query(anyString(), any(MapSqlParameterSource.class),
                any(OrdersRepositoryDao.OrderRowMapper.class));

        assertEquals(Collections.singletonList(new Order(1, 2, "Product", 3, LocalDate.now(), BigDecimal.TEN)),
                actualOrders);
    }

    @Test
    void testGetTotalCount() {
        long expectedCount = 10;

        when(namedJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), eq(Long.class)))
                .thenReturn(expectedCount);

        long actualCount = ordersRepositoryDao.getTotalCount();

        verify(namedJdbcTemplate).queryForObject(anyString(), any(MapSqlParameterSource.class), eq(Long.class));

        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testGetOrderAmountSum() {
        BigDecimal expectedSum = BigDecimal.valueOf(1000.0);

        when(namedJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), eq(BigDecimal.class)))
                .thenReturn(expectedSum);

        BigDecimal actualSum = ordersRepositoryDao.getOrderAmountSum();

        verify(namedJdbcTemplate).queryForObject(anyString(), any(MapSqlParameterSource.class), eq(BigDecimal.class));

        assertEquals(expectedSum, actualSum);
    }

    @Test
    void testOrderRowMapper() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("OrderID")).thenReturn(1);
        when(resultSet.getInt("CustomerID")).thenReturn(2);
        when(resultSet.getString("ProductType")).thenReturn("Product");
        when(resultSet.getInt("ProductID")).thenReturn(3);
        when(resultSet.getDate("OrderDate")).thenReturn(java.sql.Date.valueOf(LocalDate.now()));
        when(resultSet.getBigDecimal("Price")).thenReturn(BigDecimal.TEN);

        OrdersRepositoryDao.OrderRowMapper orderRowMapper = new OrdersRepositoryDao.OrderRowMapper();
        Order actualOrder = orderRowMapper.mapRow(resultSet, 1);

        assertEquals(new Order(1, 2, "Product", 3, LocalDate.now(), BigDecimal.TEN), actualOrder);
    }

    @Test
    void testOrderSummaryRowMapper() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("OrderID")).thenReturn(1);
        when(resultSet.getString("FirstName")).thenReturn("John");
        when(resultSet.getString("LastName")).thenReturn("Doe");
        when(resultSet.getString("ProductType")).thenReturn("Product");
        when(resultSet.getDate("OrderDate")).thenReturn(java.sql.Date.valueOf(LocalDate.now()));
        when(resultSet.getBigDecimal("Price")).thenReturn(BigDecimal.TEN);

        OrdersRepositoryDao.OrderSummaryRowMapper orderSummaryRowMapper = new OrdersRepositoryDao.OrderSummaryRowMapper();
        OrderSummary actualOrderSummary = orderSummaryRowMapper.mapRow(resultSet, 1);

        assertEquals(1, actualOrderSummary.getOrderID());
    }
}
