package com.swarn.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.swarn")
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.swarn")
public class PaymentClientExecutor {
	public static void main(String[] args) {

    	SpringApplication.run(PaymentClientExecutor.class, args);

	}
}
