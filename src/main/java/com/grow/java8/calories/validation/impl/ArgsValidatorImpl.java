package com.grow.java8.calories.validation.impl;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.grow.java8.calories.validation.ArgsValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.grow.java8.calories.constants.ArgumentConstants.*;

public class ArgsValidatorImpl implements ArgsValidator {
    private static Logger logger = LoggerFactory.getLogger(ArgsValidatorImpl.class);

    private static String MESSAGE_ERROR_NORMS = "Error parsing norms of calories per day ";
    private static String MESSAGE_FILE_NOT_FOUND = "Input file not found: ";
    private static String MESSAGE_ERROR_START_DATE = "Error parsing Start date: ";
    private static String MESSAGE_ERROR_FINISH_DATE = "Error parsing Finish date: ";

    private DateTimeFormatter dateTimeFormatter;

    public ArgsValidatorImpl(DateTimeFormatter dateTimeFormatter){
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public boolean validate(final String[] args){
        if (validateCountArgs(args)) return false;
        if (validateDouble(args[NORMS_INDEX], MESSAGE_ERROR_NORMS + args[NORMS_INDEX])) return false;
        if (validateFile(args[FILE_NAME_INDEX], MESSAGE_FILE_NOT_FOUND + args[FILE_NAME_INDEX])) return false;
        if (validateDate(args[FROM_DATE_INDEX], MESSAGE_ERROR_START_DATE + args[FROM_DATE_INDEX])) return false;
        if (validateDate(args[TO_DATE_INDEX], MESSAGE_ERROR_FINISH_DATE + args[TO_DATE_INDEX])) return false;

        return true;
    }

    private boolean validateCountArgs(final String[] args){
        if (args.length <= 3){
            logger.error("Error, Parameter finish date not found");
        }

        if (args.length <= 2){
            logger.error("Error, Parameter start date not found");
        }

        if (args.length <= 1){
            logger.error("Error, Parameter JSON file with foods not found");
        }

        if (args.length == 0){
            logger.error("Error, Parameter norma of calories not found");
        }

        return args.length < 4;
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

    private boolean validateFile(final String fileName, final String message) {
        File file = new File(fileName);
        if (file.exists()){
            return false;
        }

        logger.error(message);
        return true;
    }

    private boolean validateDate(final String date, final String message) {
        try {
            LocalDate.parse(date, dateTimeFormatter);
        }catch (DateTimeParseException ex){
            logger.error(message);
            return true;
        }
        return false;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }

    public void setDateTimeFormatter(final DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
