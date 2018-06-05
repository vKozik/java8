package com.grow.java8.calories;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.grow.java8.calories.data.Attributes;
import com.grow.java8.calories.data.FoodStat;
import com.grow.java8.calories.service.CaloriesCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Processor {
    private static Logger logger = LoggerFactory.getLogger(Processor.class);

    private static final String CALORIES_NORMAL = "The amount of calories is normal";
    private static final String CALORIES_EXCEEDED = "The number of calories exceeded";

    private Attributes attributes;

    @Value("${dateTimeFormat.patern}")
    private String dateFormat;
    @Value("${norama.Calories}")
    private Double noramaCalories;


    @Autowired
    CaloriesCalculator caloriesCalculator;


    public void setArguments(final String[] args){
        attributes = ArgumentParser.parse(args, DateTimeFormatter.ofPattern(dateFormat));
    }

    public String process(){
        List<FoodStat> statList = caloriesCalculator.getStatByDays(attributes.getFromDate(), attributes.getToDate());

        logger.info("Result: ");
        statList.stream()
                .map(foodStat -> foodStat.toString()
                        + (foodStat.getCaloriesPerDay() > noramaCalories ? " - exceeded!" : "") )
                .forEach(line -> logger.info(line));

        return caloriesCalculator.checkDailyLimit(attributes.getFromDate(), attributes.getToDate()) ? CALORIES_NORMAL : CALORIES_EXCEEDED;
    }
}
