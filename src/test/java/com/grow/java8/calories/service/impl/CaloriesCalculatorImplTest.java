package com.grow.java8.calories.service.impl;

import com.grow.java8.calories.dao.impl.FoodDAOJsonImpl;
import com.grow.java8.calories.data.FoodStat;
import com.grow.java8.calories.dao.FoodDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.grow.java8.calories.service.CaloriesCalculator;

import static org.junit.jupiter.api.Assertions.*;

class CaloriesCalculatorImplTest {
    private static CaloriesCalculator caloriesCalculator;

    @BeforeAll
    static private void init() throws IOException {
//        FoodDAO foodDAO = new FoodDAOJsonImpl();
//        caloriesCalculator = new CaloriesCalculatorImpl();
    }

    @Test
    void shouldReturnNormalCaloriesOneDay() {
        assertTrue(caloriesCalculator.checkDailyLimit(LocalDate.of(2018,05, 01)));
    }

    @Test
    void shouldReturnExceededCaloriesOneDay() {
        assertFalse(caloriesCalculator.checkDailyLimit(LocalDate.of(2018,05, 02)));
    }

    @Test
    void shouldReturnNormalCaloriesInterval() {
        LocalDate fromDate = LocalDate.of(2018,05, 03);
        LocalDate toDate = LocalDate.of(2018,05, 10);
        assertTrue(caloriesCalculator.checkDailyLimit(fromDate, toDate));
    }

    @Test
    void shouldReturnExceededCaloriesInterval() {
        LocalDate fromDate = LocalDate.of(2018,05, 01);
        LocalDate toDate = LocalDate.of(2018,05, 05);
        assertFalse(caloriesCalculator.checkDailyLimit(fromDate, toDate));
    }

    @Test
    void shouldReturnListFoodStat() {
        LocalDate fromDate = LocalDate.of(2018,02, 01);
        LocalDate toDate = LocalDate.of(2018,05, 05);
        List<FoodStat> result = caloriesCalculator.getStatByDays(fromDate, toDate);

        List<FoodStat> expect = Stream.of(
                "food-1,150.0,2018-05-01T10:10:30,850.0",
                "food-2,350.0,2018-05-01T10:15:30,850.0",
                "food-3,350.0,2018-05-01T10:19:30,850.0",
                "food-4,550.0,2018-05-02T10:11:30,1270.0",
                "food-5,50.0,2018-05-02T10:15:33,1270.0",
                "food-6,150.0,2018-05-02T10:19:30,1270.0",
                "food-7,520.0,2018-05-02T10:20:30,1270.0",
                "food-8,678.0,2018-05-03T10:12:30,928.0",
                "food-9,250.0,2018-05-03T10:13:20,928.0",
                "food-10,230.0,2018-05-04T10:15:30,610.0",
                "food-11,130.0,2018-05-04T10:16:35,610.0",
                "food-12,250.0,2018-05-04T10:18:30,610.0")
                .map(this::parseFoodStat)
                .collect(Collectors.toList());

        assertIterableEquals(result, expect);
    }

    private FoodStat parseFoodStat(final String value){
        String[] values =  value.split(",");
        FoodStat foodStat = new FoodStat();
        foodStat.setName(values[0]);
        foodStat.setCalories(Double.parseDouble(values[1]));
        foodStat.setDateOfEating(LocalDateTime.parse(values[2], DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        foodStat.setCaloriesPerDay(Double.parseDouble(values[3]));

        return foodStat;
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        LocalDate date = LocalDate.of(2018,01, 2);

        assertThrows(IllegalArgumentException.class, ()-> caloriesCalculator.checkDailyLimit(date, null));
        assertThrows(IllegalArgumentException.class, ()-> caloriesCalculator.checkDailyLimit(null, date));
        assertThrows(IllegalArgumentException.class, ()-> caloriesCalculator.checkDailyLimit(null, null));
        assertThrows(IllegalArgumentException.class, ()-> caloriesCalculator.checkDailyLimit(null));
    }
}