package media.soft.service;

import media.soft.model.PrdCustomer;
import media.soft.repository.PrdCustomersRepositoryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrdCustomersService {

    private final PrdCustomersRepositoryDao prdCustomersRepositoryDao;

    public PrdCustomersService(@Autowired PrdCustomersRepositoryDao prdCustomersRepositoryDao){
        this.prdCustomersRepositoryDao = prdCustomersRepositoryDao;
    }
    public void insertPrdCustomers(PrdCustomer prdCustomers) {
        prdCustomersRepositoryDao.insert(prdCustomers);
    }

    public void updatePrdCustomersById(int customerId, PrdCustomer prdCustomers) {
        prdCustomersRepositoryDao.updateById(customerId, prdCustomers);
    }

    public void deletePrdCustomersById(int customerId) {
        prdCustomersRepositoryDao.deleteById(customerId);
    }
}
