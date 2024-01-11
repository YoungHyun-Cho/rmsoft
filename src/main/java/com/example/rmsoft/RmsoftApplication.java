package com.example.rmsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RmsoftApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmsoftApplication.class, args);
    }
}
