package com.grow.java8.calories;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.grow.java8.calories.builder.CaloriesCalculatorBuilder;
import com.grow.java8.calories.data.Attributes;
import com.grow.java8.calories.data.FoodStat;
import com.grow.java8.calories.service.CaloriesCalculator;
import com.grow.java8.calories.dao.FoodDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Processor {
    private static Logger logger = LoggerFactory.getLogger(Processor.class);
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    private static final String CALORIES_NORMAL = "The amount of calories is normal";
    private static final String CALORIES_EXCEEDED = "The number of calories exceeded";

    private Attributes attributes;
    private FoodDAO foodDAO;

    public void setArguments(final String[] args){
        attributes = ArgumentParser.parse(args, timeFormatter);
    }

    public String process(){
        CaloriesCalculator caloriesCalculator = new CaloriesCalculatorBuilder()
                .addFoodDAO(foodDAO)
                .addNoramaCalories(attributes.getNoramaCalories())
                .build();

        List<FoodStat> statList = caloriesCalculator.getStatByDays(attributes.getFromDate(), attributes.getToDate());

        logger.info("Result: ");
        statList.stream()
                .map(foodStat -> foodStat.toString()
                        + (foodStat.getCaloriesPerDay() > attributes.getNoramaCalories() ? " - exceeded!" : "") )
                .forEach(line -> logger.info(line));

        return caloriesCalculator.checkDailyLimit(attributes.getFromDate(), attributes.getToDate()) ? CALORIES_NORMAL : CALORIES_EXCEEDED;
    }

    public FoodDAO getFoodDAO() {
        return foodDAO;
    }

    public void setFoodDAO(final FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }

    public DateTimeFormatter getTimeFormatter() {
        return timeFormatter;
    }

    public void setTimeFormatter(final DateTimeFormatter timeFormatter) {
        this.timeFormatter = timeFormatter;
    }
}
