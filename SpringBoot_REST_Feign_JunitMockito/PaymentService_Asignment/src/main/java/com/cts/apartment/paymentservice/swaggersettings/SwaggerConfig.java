package com.cts.apartment.paymentservice.swaggersettings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	 @Bean
	    public Docket productApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.cts.apartment"))
	                //.paths(PathSelectors.any())//for all controllers
	                .paths(regex("/paymentservice.*"))
	                .build()
	                .apiInfo(metaData());
	    }
	    private ApiInfo metaData() {
	        ApiInfo apiInfo = new ApiInfo(
	                "Payment Services",
	                "Spring Boot REST API",
	                "1.0",
	                "Terms of service",
	                new Contact("Swarnendu Biswas", "http://www.cognizant.com/", "Swarnendu.Biswas@cognizant.com"),
	               "Apache License Version 2.0",
	                "https://www.apache.org/licenses/LICENSE-2.0");
	        return apiInfo;
	    }
}
