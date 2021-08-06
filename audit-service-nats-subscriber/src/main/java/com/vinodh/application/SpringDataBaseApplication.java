package com.vinodh.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.vinodh.*")
@EntityScan("com.vinodh.model")	
@EnableJpaRepositories(basePackages = "com.vinodh.repository")
public class SpringDataBaseApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SpringDataBaseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDataBaseApplication.class, args);
		log.info("server started successfully  : ");
		
	}

}
