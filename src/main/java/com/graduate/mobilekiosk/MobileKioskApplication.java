package com.graduate.mobilekiosk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MobileKioskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileKioskApplication.class, args);
    }

}
