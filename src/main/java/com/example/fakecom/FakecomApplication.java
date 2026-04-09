package com.example.fakecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // frequired to enable the auditinhg in the entity fields for the hibernate
public class FakecomApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakecomApplication.class, args);
	}

}
