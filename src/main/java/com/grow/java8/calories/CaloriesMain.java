package com.grow.java8.calories;

import com.grow.java8.calories.validation.ArgsValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class CaloriesMain implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(CaloriesMain.class);

    @Autowired
    ArgsValidator argsValidator;
    @Autowired
    Processor processor;

    public static void main(String[] args) {
        SpringApplication.run(CaloriesMain.class, args);
    }

    @Override
    public void run(String... args) {
        if (argsValidator.validate(args)){
            processor.setArguments(args);
            logger.info(processor.process());
            exit(0);
        }

        exit(-1);
    }
}
