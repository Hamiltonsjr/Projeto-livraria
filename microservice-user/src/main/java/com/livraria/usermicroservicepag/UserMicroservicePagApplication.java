package com.livraria.usermicroservicepag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.livraria.usermicroservicepag.repositories")
@EnableFeignClients
public class UserMicroservicePagApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMicroservicePagApplication.class, args);
	}
}
