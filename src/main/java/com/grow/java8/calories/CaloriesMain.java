package com.grow.java8.calories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.grow.java8.calories","com.grow.java8.tickets"})
@EntityScan({"com.grow.java8.calories","com.grow.java8.tickets"})
public class CaloriesMain {
    public static void main(String[] args) {
        SpringApplication.run(CaloriesMain.class, args);
    }
}
