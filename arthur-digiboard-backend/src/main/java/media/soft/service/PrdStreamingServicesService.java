package media.soft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import media.soft.model.PrdStreamingService;
import media.soft.repository.PrdStreamingServicesRepositoryDao;

@Service
public class PrdStreamingServicesService {

    private final PrdStreamingServicesRepositoryDao prdStreamingServicesRepositoryDao;

    public PrdStreamingServicesService(@Autowired PrdStreamingServicesRepositoryDao prdStreamingServicesRepositoryDao) {
        this.prdStreamingServicesRepositoryDao = prdStreamingServicesRepositoryDao;
    }

    public void insertPrdStreamingServices(PrdStreamingService prdStreamingService) {
        prdStreamingServicesRepositoryDao.insert(prdStreamingService);
    }

    public void updatePrdStreamingServicesById(int serviceId, PrdStreamingService prdStreamingService) {
        prdStreamingServicesRepositoryDao.updateById(serviceId, prdStreamingService);
    }

    public void deletePrdStreamingServicesById(int serviceId) {
        prdStreamingServicesRepositoryDao.deleteById(serviceId);
    }
}
