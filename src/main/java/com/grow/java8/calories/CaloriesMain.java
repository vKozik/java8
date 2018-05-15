package com.grow.java8.calories;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.service.Calories;
import com.grow.java8.calories.service.impl.CaloriesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaloriesMain {
    public static final String CALORIES_NORMAL = "The amount of calories is normal";
    public static final String CALORIES_EXCEEDED = "The number of calories exceeded";
    private static Logger logger = LoggerFactory.getLogger(CaloriesMain.class);
    public static void main(String[] args) throws IOException {
        if (args.length < 4){
            logger.error("Error, No parameters are available!");
            return;
        }

        Double  noramaCalories = Double.valueOf(args[0]);
        String fileName = args[1];
        LocalDate fromDate;
        LocalDate toDate;

        try {
            fromDate = LocalDate.parse(args[2], DateTimeFormatter.ISO_LOCAL_DATE);
            toDate = LocalDate.parse(args[3], DateTimeFormatter.ISO_LOCAL_DATE);
        }catch (DateTimeParseException ex){
            logger.error("Error in date parameters", ex);
            return;
        }

        List<Food> foods = null;
        try (FileInputStream inFile = new FileInputStream(fileName)){
            ObjectMapper objectMapper = new ObjectMapper();
            foods = objectMapper.readValue(inFile, new TypeReference<List<Food>>(){});
        } catch (IOException ex) {
            logger.error("Error! ", ex);
            return;
        }

        if (foods == null){
            logger.error("Foods not found!");
        }

        Calories calories = new CaloriesImpl(foods, noramaCalories);
        logger.info(calories.checkDailyLimit(fromDate, toDate) ? CALORIES_NORMAL : CALORIES_EXCEEDED);
    }
}
