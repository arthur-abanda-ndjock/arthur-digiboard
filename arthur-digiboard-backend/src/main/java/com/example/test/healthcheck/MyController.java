package com.example.test.healthcheck;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		ResponseEntity<String> a = new ResponseEntity<String>("hello", HttpStatusCode.valueOf(200));
		return a;
	}
}
