package media.soft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import media.soft.model.Customer;
import media.soft.repository.CustomerDao;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testGetFirstCustomer() {
        Customer expectedCustomer = createSampleCustomer();
        when(customerDao.getAllData()).thenReturn(Arrays.asList(expectedCustomer, createSampleCustomer()));

        Customer actualCustomer = customerService.getFirstCustomer();

        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    void testGetCustomers() {
        List<Customer> expectedCustomers = Arrays.asList(createSampleCustomer(), createSampleCustomer());
        when(customerDao.getAllData()).thenReturn(expectedCustomers);

        List<Customer> actualCustomers = customerService.getCustomers();

        assertEquals(expectedCustomers, actualCustomers);
    }

    private Customer createSampleCustomer() {
        Customer customer = new Customer(1, "John Doe");
        return customer;
    }
}
