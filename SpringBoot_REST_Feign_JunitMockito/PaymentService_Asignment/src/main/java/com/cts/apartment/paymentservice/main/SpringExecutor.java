package com.cts.apartment.paymentservice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.cts.apartment.paymentservice")
@EntityScan( basePackages = {"com.cts.apartment.paymentservice"} )
@EnableJpaRepositories("com.cts.apartment.paymentservice.repository")

public class SpringExecutor {
	public static void main(String[] args)
	{
		SpringApplication.run(SpringExecutor.class, args);
	}
}
