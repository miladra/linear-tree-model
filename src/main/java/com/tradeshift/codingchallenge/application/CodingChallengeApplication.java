package com.tradeshift.codingchallenge.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(value = "com.tradeshift.codingchallenge")
//@Configuration
//@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "com.tradeshift.codingchallenge.repository")
@EntityScan("com.tradeshift.codingchallenge.entity")
//@EnableTransactionManagement
public class CodingChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingChallengeApplication.class, args);
	}

}
