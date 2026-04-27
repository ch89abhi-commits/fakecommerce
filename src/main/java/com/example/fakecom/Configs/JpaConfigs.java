package com.example.fakecom.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  // frequired to enable the auditinhg in the entity fields for the hibernate
public class JpaConfigs{

}

