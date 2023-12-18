package media.soft.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import media.soft.model.Customer;

@ExtendWith(MockitoExtension.class)
class CustomerDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CustomerDao customerDao;

    @Test
    void testInsertData() {
        // Mocking data
        int id = 1;
        String name = "TestCustomer";

        // Testing the insertData method
        customerDao.insertData(id, name);

        // Verifying that the update method of JdbcTemplate is called
        verify(jdbcTemplate).update(anyString(), eq(id), eq(name));
    }

    @Test
    void testGetAllData() throws SQLException {
        int id = 1;
        String name = "TestCustomer";
        Customer expectedCustomer = new Customer(id, name);

        // Mocking the behavior of JdbcTemplate
        when(jdbcTemplate.query(anyString(), ArgumentMatchers.<RowMapper<Customer>>any()))
                .thenReturn(Arrays.asList(new Customer(1, "TestCustomer")));

        // Testing the getAllData method
        List<Customer> actualCustomers = customerDao.getAllData();

        // Verifying that the query method of JdbcTemplate is called
        verify(jdbcTemplate).query(anyString(), ArgumentMatchers.<RowMapper<Customer>>any());

        // Verifying the correctness of the result
        assertEquals(1, actualCustomers.size());
        assertEquals(expectedCustomer.getId(), actualCustomers.get(0).getId());
        assertEquals(expectedCustomer.getName(), actualCustomers.get(0).getName());

    }

    @Test
    void testCustomerRowMapper() throws SQLException {
        // Mocking data
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("TestCustomer");

        // Testing the CustomerRowMapper
        CustomerDao.CustomerRowMapper rowMapper = new CustomerDao.CustomerRowMapper();
        Customer customer = rowMapper.mapRow(resultSet, 1);

        // Verifying the correctness of the result
        assertEquals(1, customer.getId());
        assertEquals("TestCustomer", customer.getName());
    }
}
