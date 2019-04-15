package com.cts.apartment.paymentservice.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@ComponentScan("com.cts")
//@PropertySource("application-test.yml")
@EnableAutoConfiguration
@EntityScan( basePackages = {"com.cts.apartment.paymentservice"} )
@EnableJpaRepositories("com.cts.apartment.paymentservice.repository")
public class SpringBootTestExecutor {
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootTestExecutor.class,args);
	}
}
