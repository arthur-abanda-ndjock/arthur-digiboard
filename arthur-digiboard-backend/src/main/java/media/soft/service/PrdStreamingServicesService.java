package media.soft.service;

import lombok.RequiredArgsConstructor;
import media.soft.model.PrdStreamingService;
import media.soft.repository.PrdStreamingServicesRepositoryDao;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrdStreamingServicesService {

    private final PrdStreamingServicesRepositoryDao prdStreamingServicesRepositoryDao;

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
