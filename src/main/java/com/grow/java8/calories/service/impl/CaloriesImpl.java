package com.grow.java8.calories.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.service.Calories;

public class CaloriesImpl implements Calories {
    private Double noramaCalories;
    private List<Food> foods;

    public CaloriesImpl(final List<Food> foods, final Double noramaCalories) {
        this.noramaCalories = noramaCalories;
        this.foods = foods;
    }

    @Override
    public List<Food> getFoods(final LocalDateTime from, final LocalDateTime to) {
        return foods.stream()
                .filter(food -> food.getDateOfEating().isAfter(from) && food.getDateOfEating().isBefore(to))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkDailyLimit(final LocalDate fromDate, final LocalDate toDate) {
        final LocalDateTime fromDateTime = fromDate.atTime(0,0);
        final LocalDateTime toDateTime = toDate.plusDays(1).atTime(0,0);
        return checkLimit(fromDateTime, toDateTime);
    }

    @Override
    public boolean checkDailyLimit(final LocalDate date) {
        final LocalDateTime fromDateTime = date.atTime(0,0);
        final LocalDateTime toDateTime = date.plusDays(1).atTime(0,0);
        return checkLimit(fromDateTime, toDateTime);
    }

    private boolean checkLimit(final LocalDateTime fromDate, final LocalDateTime toDate){
        return foods.stream()
                .filter(food ->  food.getDateOfEating().isAfter(fromDate)
                        && food.getDateOfEating().isBefore(toDate))
                .collect(Collectors.groupingBy(food->food.getDateOfEating().toLocalDate(),
                        HashMap::new, Collectors.summingDouble(Food::getCalories)))
                .values().stream()
                .allMatch(c -> c.compareTo(noramaCalories) < 0);
    }
}
