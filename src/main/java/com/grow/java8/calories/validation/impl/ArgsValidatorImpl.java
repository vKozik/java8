package com.grow.java8.calories.validation.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.grow.java8.calories.validation.ArgsValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.grow.java8.calories.constants.ArgumentConstants.*;

@Component
public class ArgsValidatorImpl implements ArgsValidator {
    private static Logger logger = LoggerFactory.getLogger(ArgsValidatorImpl.class);
    
    private static final String MESSAGE_ERROR_NORMS = "Error parsing norms of calories per day ";
    private static final String MESSAGE_ERROR_START_DATE = "Error parsing Start date: ";
    private static final String MESSAGE_ERROR_FINISH_DATE = "Error parsing Finish date: ";

    @Value("${dateTimeFormat.patern}")
    private String dateFormat;

    @Override
    public boolean validate(final String[] args){
        if (validateCountArgs(args)) return false;
        if (validateDouble(args[NORAMA_INDEX], MESSAGE_ERROR_NORMS + args[NORAMA_INDEX])) return false;
        if (validateDate(args[FROM_DATE_INDEX], MESSAGE_ERROR_START_DATE + args[FROM_DATE_INDEX])) return false;
        return !validateDate(args[TO_DATE_INDEX], MESSAGE_ERROR_FINISH_DATE + args[TO_DATE_INDEX]);
    }

    private boolean validateCountArgs(final String[] args){
        if (args.length <= 2){
            logger.error("Error, Parameter norma of calories not found");
        }

        if (args.length <= 1){
            logger.error("Error, Parameter finish date not found");
        }

        if (args.length == 0){
            logger.error("Error, Parameter start date not found");
        }

        return args.length < 3;
    }

    private boolean validateDate(final String date, final String message) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
        }catch (DateTimeParseException ex){
            logger.error(message);
            return true;
        }
        return false;
    }
    
    private boolean validateDouble(final String value, final String message) {
        try {
            Double.parseDouble(value);
        }catch (Exception ex){
            logger.error(message);
            return true;
        }
        return false;
    }
}
