package com.grow.java8.calories.builder;

import java.time.format.DateTimeFormatter;

import com.grow.java8.calories.Processor;
import com.grow.java8.calories.service.FoodService;
import com.grow.java8.calories.service.impl.FoodServiceImpl;

public class ProcessorBuilder {
    private String[] args;
    private DateTimeFormatter dateTimeFormatter;
    private FoodService foodService;

    public ProcessorBuilder addArgs(final String[] args){
        this.args = args;
        return this;
    }

    public ProcessorBuilder addDateTimeFormatter(final DateTimeFormatter dateTimeFormatter){
        this.dateTimeFormatter = dateTimeFormatter;
        return this;
    }

    public ProcessorBuilder addFoodService(final FoodService foodService){
        this.foodService = foodService;
        return this;
    }

    public Processor buildProcessor(){
        Processor processor = new Processor();
        processor.setFoodService(foodService != null ? foodService : new FoodServiceImpl());
        processor.setArguments(args);
        processor.setTimeFormatter(dateTimeFormatter);
        return processor;
    }
}
