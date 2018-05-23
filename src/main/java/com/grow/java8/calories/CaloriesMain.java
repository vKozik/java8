package com.grow.java8.calories;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import com.grow.java8.calories.builder.ProcessorBuilder;
import com.grow.java8.calories.service.impl.FoodServiceImpl;
import com.grow.java8.calories.validation.ArgsValidator;
import com.grow.java8.calories.validation.impl.ArgsValidatorImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaloriesMain {
    private static Logger logger = LoggerFactory.getLogger(CaloriesMain.class);

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    public static void main(String[] args) throws IOException {
        ArgsValidator argsValidator = new ArgsValidatorImpl(DATE_FORMAT);
        if (!argsValidator.validate(args)){
            return;
        }

        Processor processor = new ProcessorBuilder()
                .addArgs(args)
                .addDateTimeFormatter(DATE_FORMAT)
                .addFoodService(new FoodServiceImpl())
                .buildProcessor();

        logger.info(processor.process());
    }
}
