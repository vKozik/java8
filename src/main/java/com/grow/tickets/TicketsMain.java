package com.grow.tickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan({"com.grow.java8.tickets"})
@EnableTransactionManagement(proxyTargetClass=true)
public class TicketsMain {
    public static void main(String[] args) {
        SpringApplication.run(TicketsMain.class, args);
    }
}
