package media.soft.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import media.soft.model.PrdStreamingService;
import media.soft.repository.PrdStreamingServicesRepositoryDao;

@ExtendWith(MockitoExtension.class)
class PrdStreamingServicesServiceTest {

    @Mock
    private PrdStreamingServicesRepositoryDao prdStreamingServicesRepositoryDao;

    @InjectMocks
    private PrdStreamingServicesService prdStreamingServicesService;

    @Test
    void testInsertPrdStreamingServices() {
        PrdStreamingService prdStreamingService = new PrdStreamingService();
        prdStreamingServicesService.insertPrdStreamingServices(prdStreamingService);

        verify(prdStreamingServicesRepositoryDao).insert(prdStreamingService);
    }

    @Test
    void testUpdatePrdStreamingServicesById() {
        int serviceId = 1;
        PrdStreamingService prdStreamingService = new PrdStreamingService();
        prdStreamingServicesService.updatePrdStreamingServicesById(serviceId, prdStreamingService);

        verify(prdStreamingServicesRepositoryDao).updateById(serviceId, prdStreamingService);
    }

    @Test
    void testDeletePrdStreamingServicesById() {
        int serviceId = 1;
        prdStreamingServicesService.deletePrdStreamingServicesById(serviceId);

        verify(prdStreamingServicesRepositoryDao).deleteById(serviceId);
    }
}
