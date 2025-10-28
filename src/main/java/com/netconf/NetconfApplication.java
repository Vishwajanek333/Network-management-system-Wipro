package com.netconf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NetconfApplication {
    public static void main(String[] args) {
        SpringApplication.run(NetconfApplication.class, args);
    }
}