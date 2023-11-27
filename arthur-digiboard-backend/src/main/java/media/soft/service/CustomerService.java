package media.soft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import media.soft.model.Customer;
import media.soft.repository.CustomerDao;

@Service
public class CustomerService {

	private CustomerDao customerDao;

	public CustomerService(@Autowired CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public Customer getFirstCustomer() {
		return customerDao.getAllData().get(0);
	}

	public List<Customer> getCustomers() {
		return customerDao.getAllData();
	}
}
