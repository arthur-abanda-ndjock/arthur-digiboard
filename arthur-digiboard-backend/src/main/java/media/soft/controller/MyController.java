package media.soft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import media.soft.model.Customer;
import media.soft.service.CustomerService;

@RestController
public class MyController {

	private CustomerService service;

	public MyController(@Autowired CustomerService service) {
		this.service = service;
	}

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		Customer customer = service.getFirstCustomer();
		return new ResponseEntity<>(
				"id: " + customer.getId() + "  name:" + customer.getName(), HttpStatusCode.valueOf(200));
	}

	@GetMapping("/hello-many")
	public ResponseEntity<List<Customer>> helloMany() {
		return new ResponseEntity<>(service.getCustomers(),
				HttpStatusCode.valueOf(200));
	}

}
