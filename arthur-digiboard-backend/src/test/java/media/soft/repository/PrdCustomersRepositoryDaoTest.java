package media.soft.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import media.soft.model.PrdCustomer;

@ExtendWith(MockitoExtension.class)
class PrdCustomersRepositoryDaoTest {

    @Mock
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @InjectMocks
    private PrdCustomersRepositoryDao prdCustomersRepositoryDao;

    @Test
    void testInsert() {
        PrdCustomer prdCustomer = new PrdCustomer();
        prdCustomer.setCustomerID(1);
        prdCustomer.setFirstName("John");
        prdCustomer.setLastName("Doe");
        prdCustomer.setSex('M');
        prdCustomer.setAddress("123 Main St");

        prdCustomersRepositoryDao.insert(prdCustomer);

        verify(namedJdbcTemplate).update(anyString(), ArgumentMatchers.<String, Object>anyMap());
    }

    @Test
    void testUpdateById() {
        int customerId = 1;
        PrdCustomer prdCustomer = new PrdCustomer();
        prdCustomer.setFirstName("UpdatedJohn");
        prdCustomer.setLastName("UpdatedDoe");
        prdCustomer.setSex('F');
        prdCustomer.setAddress("456 Updated St");

        prdCustomersRepositoryDao.updateById(customerId, prdCustomer);
        verify(namedJdbcTemplate).update(anyString(), ArgumentMatchers.<String, Object>anyMap());
    }

    @Test
    void testDeleteById() {
        int customerId = 1;

        prdCustomersRepositoryDao.deleteById(customerId);
        verify(namedJdbcTemplate).update(anyString(), ArgumentMatchers.<String, Object>anyMap());
    }

    @Test
    void testReadAll() throws SQLException {

        PrdCustomer prdCustomer = new PrdCustomer();
        prdCustomer.setFirstName("UpdatedJohn");
        prdCustomer.setLastName("UpdatedDoe");
        prdCustomer.setSex('F');
        prdCustomer.setAddress("456 Updated St");

        when(namedJdbcTemplate.query(anyString(), any(PrdCustomersRepositoryDao.PrdCustomerRowMapper.class)))
                .thenReturn(Collections.singletonList(prdCustomer));

        List<PrdCustomer> actualPrdCustomers = prdCustomersRepositoryDao.readAll();

        verify(namedJdbcTemplate).query(anyString(), any(PrdCustomersRepositoryDao.PrdCustomerRowMapper.class));

        assertEquals(1, actualPrdCustomers.size());
        assertEquals(prdCustomer.getAddress(), actualPrdCustomers.get(0).getAddress());
        assertEquals(prdCustomer.getLastName(), actualPrdCustomers.get(0).getLastName());
        assertEquals(prdCustomer.getSex(), actualPrdCustomers.get(0).getSex());
        assertEquals(prdCustomer.getFirstName(), actualPrdCustomers.get(0).getFirstName());

    }

    @Test
    void testPrdCustomerRowMapper() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("CustomerID")).thenReturn(1);
        when(resultSet.getString("FirstName")).thenReturn("John");
        when(resultSet.getString("LastName")).thenReturn("Doe");
        when(resultSet.getString("Sex")).thenReturn("M");
        when(resultSet.getString("Address")).thenReturn("123 Main St");

        PrdCustomersRepositoryDao.PrdCustomerRowMapper prdCustomerRowMapper = new PrdCustomersRepositoryDao.PrdCustomerRowMapper();

        PrdCustomer actualPrdCustomer = prdCustomerRowMapper.mapRow(resultSet, 1);

        PrdCustomer prdCustomer = new PrdCustomer();
        prdCustomer.setFirstName("UpdatedJohn");
        prdCustomer.setLastName("UpdatedDoe");
        prdCustomer.setSex('F');
        prdCustomer.setAddress("456 Updated St");

        assertEquals(1, actualPrdCustomer.getCustomerID());
        assertEquals("John", actualPrdCustomer.getFirstName());
        assertEquals("Doe", actualPrdCustomer.getLastName());
        assertEquals('M', actualPrdCustomer.getSex());
        assertEquals("123 Main St", actualPrdCustomer.getAddress());

    }
}
