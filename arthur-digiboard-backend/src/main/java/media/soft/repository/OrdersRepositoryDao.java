package media.soft.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import media.soft.model.Order;
import media.soft.model.OrderSummary;

@Repository
public class OrdersRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public OrdersRepositoryDao(@Autowired NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public void insert(Order order) {
        String sql = "INSERT INTO Orders (OrderID, CustomerID, ProductType, ProductID, OrderDate) " +
                "VALUES (:orderId, :customerId, :productType, :productId, :orderDate)";

        Map<String, Object> params = new HashMap<>();
        params.put("orderId", order.getOrderID());
        params.put("customerId", order.getCustomerID());
        params.put("productType", order.getProductType());
        params.put("productId", order.getProductID());
        params.put("orderDate", order.getOrderDate());

        namedJdbcTemplate.update(sql, params);
    }

    public void updateById(int orderId, Order order) {
        String sql = "UPDATE Orders " +
                "SET CustomerID = :customerId, ProductType = :productType, " +
                "ProductID = :productId, OrderDate = :orderDate " +
                "WHERE OrderID = :orderId";

        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("customerId", order.getCustomerID());
        params.put("productType", order.getProductType());
        params.put("productId", order.getProductID());
        params.put("orderDate", order.getOrderDate());

        namedJdbcTemplate.update(sql, params);
    }

    public void deleteById(int orderId) {
        String sql = "DELETE FROM Orders WHERE OrderID = :orderId";

        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);

        namedJdbcTemplate.update(sql, params);
    }

    public List<Order> getAll() {
        String sql = "SELECT * FROM Orders";
        return namedJdbcTemplate.query(sql, new OrderRowMapper());
    }

    public List<OrderSummary> getRecentOrders() {
        String sql = "SELECT a.OrderID, c.FirstName, c.LastName, a.ProductType, a.OrderDate, a.Price " +
                "FROM Orders a JOIN PrdCustomers c on a.CustomerID = c.CustomerID" +
                " order by OrderDate desc limit 7";
        return namedJdbcTemplate.query(sql, new OrderSummaryRowMapper());
    }

    public LocalDate getLatestOrderDate() {
        String sql = "SELECT max(OrderDate) from Orders";
        return namedJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), LocalDate.class);
    }

    public List<Order> getOrdersByPeriod(LocalDate start, LocalDate end) {
        String sql = "SELECT * FROM Orders where orderdate between :start and :end ";
        MapSqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("start", start)
                .addValue("end", end);
        return namedJdbcTemplate.query(sql, paramMap, new OrderRowMapper());
    }

    private static class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setOrderID(rs.getInt("OrderID"));
            order.setCustomerID(rs.getInt("CustomerID"));
            order.setProductType(rs.getString("ProductType"));
            order.setProductID(rs.getInt("ProductID"));
            order.setOrderDate(rs.getDate("OrderDate").toLocalDate());
            order.setPrice(rs.getBigDecimal("Price"));
            return order;
        }
    }

    private static class OrderSummaryRowMapper implements RowMapper<OrderSummary> {
        @Override
        public OrderSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderSummary order = new OrderSummary();
            order.setOrderID(rs.getInt("OrderID"));
            order.setCustomerFullName(rs.getString("FirstName").concat(" ").concat(rs.getString("LastName")));
            order.setProductType(rs.getString("ProductType"));
            order.setOrderDate(rs.getDate("OrderDate").toLocalDate());
            order.setPrice(rs.getBigDecimal("Price"));
            return order;
        }
    }

    public Long getTotalCount() {
        String sql = "SELECT count(1) from Orders";
        return namedJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Long.class);
    }

    // get total cost sum
    public BigDecimal getOrderAmountSum() {
        String sql = "SELECT sum(price) FROM Orders";
        return namedJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), BigDecimal.class);
    }
}
