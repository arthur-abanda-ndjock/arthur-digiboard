package media.soft.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import media.soft.model.PrdCustomer;

@Repository

public class PrdCustomersRepositoryDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public PrdCustomersRepositoryDao(@Autowired NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public void insert(PrdCustomer prdCustomers) {
        String sql = "INSERT INTO PrdCustomers (CustomerID, FirstName, LastName, Sex, Address) " +
                "VALUES (:customerId, :firstName, :lastName, :sex, :address)";

        Map<String, Object> params = new HashMap<>();
        params.put("customerId", prdCustomers.getCustomerID());
        params.put("firstName", prdCustomers.getFirstName());
        params.put("lastName", prdCustomers.getLastName());
        params.put("sex", prdCustomers.getSex());
        params.put("address", prdCustomers.getAddress());

        namedJdbcTemplate.update(sql, params);
    }

    public void updateById(int customerId, PrdCustomer prdCustomers) {
        String sql = "UPDATE PrdCustomers " +
                "SET FirstName = :firstName, LastName = :lastName, Sex = :sex, Address = :address " +
                "WHERE CustomerID = :customerId";

        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);
        params.put("firstName", prdCustomers.getFirstName());
        params.put("lastName", prdCustomers.getLastName());
        params.put("sex", prdCustomers.getSex());
        params.put("address", prdCustomers.getAddress());

        namedJdbcTemplate.update(sql, params);
    }

    public void deleteById(int customerId) {
        String sql = "DELETE FROM PrdCustomers WHERE CustomerID = :customerId";

        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);

        namedJdbcTemplate.update(sql, params);
    }

    public List<PrdCustomer> readAll() {
        String sql = "SELECT * FROM PrdCustomers";
        return namedJdbcTemplate.query(sql, new PrdCustomerRowMapper());
    }

    public static class PrdCustomerRowMapper implements RowMapper<PrdCustomer> {
        @Override
        public PrdCustomer mapRow(ResultSet rs, int rowNum) throws SQLException {
            PrdCustomer prdCustomer = new PrdCustomer();
            prdCustomer.setCustomerID(rs.getInt("CustomerID"));
            prdCustomer.setFirstName(rs.getString("FirstName"));
            prdCustomer.setLastName(rs.getString("LastName"));
            prdCustomer.setSex(rs.getString("Sex").charAt(0));
            prdCustomer.setAddress(rs.getString("Address"));
            return prdCustomer;
        }
    }

}
