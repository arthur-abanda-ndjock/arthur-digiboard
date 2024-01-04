package media.soft.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import media.soft.model.PrdCustomer;
import media.soft.repository.PrdCustomersRepositoryDao;

@ExtendWith(MockitoExtension.class)
class PrdCustomersServiceTest {

    @Mock
    private PrdCustomersRepositoryDao prdCustomersRepositoryDao;

    @InjectMocks
    private PrdCustomersService prdCustomersService;

    @Test
    void testInsertPrdCustomers() {
        PrdCustomer prdCustomer = new PrdCustomer();
        prdCustomersService.insertPrdCustomers(prdCustomer);

        verify(prdCustomersRepositoryDao).insert(prdCustomer);
    }

    @Test
    void testUpdatePrdCustomersById() {
        int customerId = 1;
        PrdCustomer prdCustomer = new PrdCustomer();
        prdCustomersService.updatePrdCustomersById(customerId, prdCustomer);

        verify(prdCustomersRepositoryDao).updateById(customerId, prdCustomer);
    }

    @Test
    void testDeletePrdCustomersById() {
        int customerId = 1;
        prdCustomersService.deletePrdCustomersById(customerId);

        verify(prdCustomersRepositoryDao).deleteById(customerId);
    }
}
