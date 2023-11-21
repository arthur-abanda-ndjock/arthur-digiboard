package com.example.test.healthcheck;

import java.util.Random;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.healthcheck.model.CrmDashboardCard;

@RestController
public class MyNumberGeneratorController {

	@GetMapping(value = "/api/rnumbers", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CrmDashboardCard> hello() {

		double clients = getRandomDouble(900, 1500);
		double admin = getRandomDouble(10, 30);

		double expenses = getRandomDouble(60000, 100000);
		CrmDashboardCard response = new CrmDashboardCard(clients, admin, expenses);
		ResponseEntity<CrmDashboardCard> a = new ResponseEntity<CrmDashboardCard>(response,
				HttpStatusCode.valueOf(200));

		return a;
	}

	private double getRandomDouble(double leftLimit, double rightLimit) {
		double generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
		return generatedDouble;
	}

}
