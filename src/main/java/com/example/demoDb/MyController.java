package com.example.demoDb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@RequestMapping("/")
	public String welcome() {
		return "Welcome to my application..!";
	}
}
