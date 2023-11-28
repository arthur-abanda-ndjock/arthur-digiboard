package media.soft.repository;
import media.soft.model.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class OrdersRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public OrdersRepositoryDao(@Autowired NamedParameterJdbcTemplate namedJdbcTemplate){
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

    public List<Order> readAll() {
        String sql = "SELECT * FROM Orders";
        return namedJdbcTemplate.query(sql, new OrderRowMapper());
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
}
