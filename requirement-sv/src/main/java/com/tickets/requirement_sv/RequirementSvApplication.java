package com.tickets.requirement_sv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RequirementSvApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequirementSvApplication.class, args);
	}

}