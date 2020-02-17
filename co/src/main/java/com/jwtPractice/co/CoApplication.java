package com.jwtPractice.co;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableAutoConfiguration
public class CoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CoApplication.class, args);
	}

    @RequestMapping("/")

    String hello() {

        return "hello world";

    }
}
