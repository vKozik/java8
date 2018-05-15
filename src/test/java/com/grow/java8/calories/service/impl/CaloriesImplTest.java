package com.grow.java8.calories.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.service.Calories;
import com.grow.java8.calories.service.impl.CaloriesImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Java8 stream API tests")
@TestInstance(Lifecycle.PER_CLASS)
class CaloriesImplTest {
    private Calories calories;

    @BeforeAll
    void init(){
        final ArrayList<Food> foods = new ArrayList<>();
        foods.add(getFood("2018-01-01T10:10:30+01:01", 100d));
        foods.add(getFood("2018-01-01T10:11:31+01:01", 200d));
        foods.add(getFood("2018-01-01T10:12:20+01:01", 520d));
        foods.add(getFood("2018-01-01T10:20:00+01:01", 130d));
        foods.add(getFood("2018-01-01T10:21:00+01:01", 300d));
        foods.add(getFood("2018-01-01T10:22:00+01:01", 300d));

        foods.add(getFood("2018-01-02T10:22:00+01:01", 300d));
        foods.add(getFood("2018-01-02T10:22:00+01:01", 300d));
        foods.add(getFood("2018-01-02T10:22:00+01:01", 300d));

        foods.add(getFood("2018-01-03T10:22:00+01:01", 300d));
        foods.add(getFood("2018-01-03T10:22:00+01:01", 300d));
        foods.add(getFood("2018-01-03T10:22:00+01:01", 300d));

        foods.add(getFood("2018-01-04T10:22:00+01:01", 300d));
        foods.add(getFood("2018-01-04T10:22:00+01:01", 700d));

        foods.add(getFood("2018-01-05T10:22:00+01:01", 300d));
        foods.add(getFood("2018-01-05T10:22:00+01:01", 300d));
        foods.add(getFood("2018-01-05T10:22:00+01:01", 300d));

        foods.add(getFood("2018-01-07T09:20:00+01:01", 200d));
        foods.add(getFood("2018-01-07T18:16:00+01:01", 500d));
        foods.add(getFood("2018-01-07T20:20:00+01:01", 800d));
        foods.add(getFood("2018-01-07T22:22:00+01:01", 200d));

        calories = new CaloriesImpl(foods, Double.valueOf(1500));
    }

    @Test
    void ShouldReturnNormalCaloriesOneDay() {
        assertTrue(calories.checkDailyLimit(LocalDate.of(2018,01, 02)));
    }

    @Test
    void ShouldReturnExceededCaloriesOneDay() {
        assertFalse(calories.checkDailyLimit(LocalDate.of(2018,01, 01)));
    }

    @Test
    void ShouldReturnNormalCaloriesInterval() {
        LocalDate fromDate = LocalDate.of(2018,01, 02);
        LocalDate toDate = LocalDate.of(2018,01, 04);
        assertTrue(calories.checkDailyLimit(fromDate, toDate));
    }

    @Test
    void ShouldReturnExceededCaloriesInterval() {
        LocalDate fromDate = LocalDate.of(2018,01, 02);
        LocalDate toDate = LocalDate.of(2018,01, 07);
        assertFalse(calories.checkDailyLimit(fromDate, toDate));
    }

    Food getFood(String date, Double calories){
        Food result = new Food();
        result.setCalories(calories);
        result.setDateOfEating(LocalDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        return result;
    }

}