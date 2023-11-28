package media.soft.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import media.soft.model.Customer;

@Repository
public class CustomerDao {

	private final JdbcTemplate jdbcTemplate;

	
	public CustomerDao(@Autowired JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insertData(int id, String name) {
		String sql = "INSERT INTO customer (id,name) VALUES (?,?)";
		jdbcTemplate.update(sql, id, name);
	}

	public List<Customer> getAllData() {
		String sql = "SELECT * FROM customer";
		return jdbcTemplate.query(sql, new CustomerRowMapper());
	}

	private static class CustomerRowMapper implements RowMapper<Customer> {
		@Override
		public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			return new Customer(resultSet.getInt("id"), resultSet.getString("name"));
		}
	}
}
