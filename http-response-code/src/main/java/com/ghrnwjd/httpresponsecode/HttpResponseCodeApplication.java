package com.ghrnwjd.httpresponsecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HttpResponseCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpResponseCodeApplication.class, args);
	}

}
