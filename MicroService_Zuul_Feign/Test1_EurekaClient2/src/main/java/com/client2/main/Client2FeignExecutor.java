package com.client2.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;



@EnableFeignClients(basePackages = "com.client2")
@SpringBootApplication
@EnableAutoConfiguration
@EnableCircuitBreaker
@ComponentScan("com.client2")
@EnableEurekaClient
public class Client2FeignExecutor {
	
	public static void main(String[] args) {
		SpringApplication.run(Client2FeignExecutor.class, args);
	}
}
