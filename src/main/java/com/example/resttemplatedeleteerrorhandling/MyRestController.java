package com.example.resttemplatedeleteerrorhandling;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	@DeleteMapping("/test")
	public void testDeleteError() {
		throw new RuntimeException("I am delete error");
	}

	@GetMapping("/test")
	public void testGetError() {
		throw new RuntimeException("I am get error");
	}
}
