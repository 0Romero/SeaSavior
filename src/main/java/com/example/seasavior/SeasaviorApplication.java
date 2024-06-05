package com.example.seasavior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class SeasaviorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeasaviorApplication.class, args);
		
	}
	@RequestMapping
	@ResponseBody
	public String home(){ 
		return "SeaSavior";
	}
}
