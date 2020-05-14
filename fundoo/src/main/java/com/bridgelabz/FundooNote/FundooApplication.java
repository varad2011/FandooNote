package com.bridgelabz.FundooNote;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bridgelabz.FundooNote.vertX.ServerVertical;

import io.vertx.core.Vertx;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@EnableScheduling
public class FundooApplication {
//	@Autowired
//	ServerVertical simpleVertx;
	
	public static void main(String[] args) {
		SpringApplication.run(FundooApplication.class, args);
	}

//	@PostConstruct
//	public void deployVerticle() {
//	Vertx.vertx().deployVerticle(simpleVertx);
//	}
}
