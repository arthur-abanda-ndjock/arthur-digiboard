package media.soft.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import media.soft.model.PrdSoftwareApplication;
import media.soft.repository.PrdSoftwareApplicationsRepositoryDao;

@ExtendWith(MockitoExtension.class)
class PrdSoftwareApplicationsServiceTest {

    @Mock
    private PrdSoftwareApplicationsRepositoryDao prdSoftwareApplicationsRepositoryDao;

    @InjectMocks
    private PrdSoftwareApplicationsService prdSoftwareApplicationsService;

    @Test
    void testInsertPrdSoftwareApplications() {
        PrdSoftwareApplication prdSoftwareApplication = new PrdSoftwareApplication();
        prdSoftwareApplicationsService.insertPrdSoftwareApplications(prdSoftwareApplication);

        verify(prdSoftwareApplicationsRepositoryDao).insert(prdSoftwareApplication);
    }

    @Test
    void testUpdatePrdSoftwareApplicationsById() {
        int softwareId = 1;
        PrdSoftwareApplication prdSoftwareApplication = new PrdSoftwareApplication();
        prdSoftwareApplicationsService.updatePrdSoftwareApplicationsById(softwareId, prdSoftwareApplication);

        verify(prdSoftwareApplicationsRepositoryDao).updateById(softwareId, prdSoftwareApplication);
    }

    @Test
    void testDeletePrdSoftwareApplicationsById() {
        int softwareId = 1;
        prdSoftwareApplicationsService.deletePrdSoftwareApplicationsById(softwareId);

        verify(prdSoftwareApplicationsRepositoryDao).deleteById(softwareId);
    }
}
