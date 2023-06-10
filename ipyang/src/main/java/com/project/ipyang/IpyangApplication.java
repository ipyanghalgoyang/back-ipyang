package com.project.ipyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class IpyangApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpyangApplication.class, args);
	}

}
