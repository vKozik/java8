package com.grow.java8.calories.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.grow.java8.calories.data.Food;

public interface Calories {
    List<Food> getFoods(LocalDateTime from, LocalDateTime to);
    boolean checkDailyLimit(LocalDate fromDate, LocalDate toDate);
    boolean checkDailyLimit(LocalDate date);
}
