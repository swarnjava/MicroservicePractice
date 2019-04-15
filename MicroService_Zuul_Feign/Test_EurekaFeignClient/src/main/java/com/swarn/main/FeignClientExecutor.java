package com.swarn.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.swarn")
@SpringBootApplication
@EnableAutoConfiguration
@EnableCircuitBreaker
@ComponentScan("com.swarn")
public class FeignClientExecutor {
	public static void main(String[] args) {

    	SpringApplication.run(FeignClientExecutor.class, args);

	}
}
