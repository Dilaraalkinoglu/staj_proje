package com.dilaraalk.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.dilaraalk.entities"})
@ComponentScan(basePackages = {"com.dilaraalk"})
@EnableJpaRepositories(basePackages = "com.dilaraalk.repository") 
@SpringBootApplication
public class UserLoginSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserLoginSystemApplication.class, args);
	}

}
