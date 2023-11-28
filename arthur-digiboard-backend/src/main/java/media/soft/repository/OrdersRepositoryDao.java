package media.soft.repository;
import lombok.RequiredArgsConstructor;
import media.soft.model.Order;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class OrdersRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

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
}
