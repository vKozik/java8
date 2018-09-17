package com.grow.java8.calories.service;

import java.time.LocalDate;
import java.util.List;

import com.grow.java8.calories.data.FoodStat;

public interface CaloriesCalculator {
    boolean checkDailyLimit(LocalDate fromDate, LocalDate toDate, Double normaCalories);
    boolean checkDailyLimit(LocalDate date, Double normaCalories);

    List<FoodStat> getStatByDays(LocalDate fromDate, LocalDate toDate);
    List<FoodStat> getStatByDay(LocalDate date);
}
