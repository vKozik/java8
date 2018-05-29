package com.grow.java8.calories;

import java.time.format.DateTimeFormatter;

import com.grow.java8.calories.builder.ProcessorBuilder;
import com.grow.java8.calories.dao.impl.FoodDAOJsonImpl;
import com.grow.java8.calories.validation.ArgsValidator;
import com.grow.java8.calories.validation.impl.ArgsValidatorImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaloriesMain {
    private static Logger logger = LoggerFactory.getLogger(CaloriesMain.class);

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    public static void main(String[] args) {
        ArgsValidator argsValidator = new ArgsValidatorImpl(DATE_FORMAT);
        if (!argsValidator.validate(args)){
            return;
        }

        Processor processor = new ProcessorBuilder()
                .addArgs(args)
                .addDateTimeFormatter(DATE_FORMAT)
                .addFoodDAO(new FoodDAOJsonImpl(args[1]))
                .buildProcessor();

        logger.info(processor.process());
    }
}
