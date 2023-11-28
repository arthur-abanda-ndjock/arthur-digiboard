package media.soft.service;

import lombok.RequiredArgsConstructor;
import media.soft.model.PrdSoftwareApplication;
import media.soft.repository.PrdSoftwareApplicationsRepositoryDao;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrdSoftwareApplicationsService {

    private final PrdSoftwareApplicationsRepositoryDao prdSoftwareApplicationsRepositoryDao;

    public void insertPrdSoftwareApplications(PrdSoftwareApplication prdSoftwareApplication) {
        prdSoftwareApplicationsRepositoryDao.insert(prdSoftwareApplication);
    }

    public void updatePrdSoftwareApplicationsById(int softwareId, PrdSoftwareApplication prdSoftwareApplication) {
        prdSoftwareApplicationsRepositoryDao.updateById(softwareId, prdSoftwareApplication);
    }

    public void deletePrdSoftwareApplicationsById(int softwareId) {
        prdSoftwareApplicationsRepositoryDao.deleteById(softwareId);
    }
}

