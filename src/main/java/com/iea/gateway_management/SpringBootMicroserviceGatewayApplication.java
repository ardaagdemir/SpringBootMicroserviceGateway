package com.iea.gateway_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
@SpringBootApplication
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class SpringBootMicroserviceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroserviceGatewayApplication.class, args);
	}

}
