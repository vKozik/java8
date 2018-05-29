package com.grow.java8.calories.builder;

import java.time.format.DateTimeFormatter;

import com.grow.java8.calories.Processor;
import com.grow.java8.calories.dao.FoodDAO;

public class ProcessorBuilder {
    private String[] args;
    private DateTimeFormatter dateTimeFormatter;
    private FoodDAO foodDAO;

    public ProcessorBuilder addArgs(final String[] args){
        this.args = args;
        return this;
    }

    public ProcessorBuilder addDateTimeFormatter(final DateTimeFormatter dateTimeFormatter){
        this.dateTimeFormatter = dateTimeFormatter;
        return this;
    }

    public ProcessorBuilder addFoodDAO(final FoodDAO foodDAO){
        this.foodDAO = foodDAO;
        return this;
    }

    public Processor buildProcessor(){
        Processor processor = new Processor();
        processor.setFoodDAO(foodDAO);
        processor.setArguments(args);
        processor.setTimeFormatter(dateTimeFormatter);
        return processor;
    }
}
