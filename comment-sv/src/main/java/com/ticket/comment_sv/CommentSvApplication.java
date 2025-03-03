package com.ticket.comment_sv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.ticket")
@EnableDiscoveryClient
@EnableFeignClients
public class CommentSvApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentSvApplication.class, args);
	}

}
