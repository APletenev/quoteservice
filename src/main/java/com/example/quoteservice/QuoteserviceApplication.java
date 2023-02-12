package com.example.quoteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuoteserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteserviceApplication.class, args);
    }

}
