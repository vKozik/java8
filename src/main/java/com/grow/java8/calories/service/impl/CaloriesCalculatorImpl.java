package com.grow.java8.calories.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.grow.java8.calories.Processor;
import com.grow.java8.calories.converters.FoodStatConverter;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.data.FoodStat;
import com.grow.java8.calories.service.CaloriesCalculator;
import com.grow.java8.calories.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaloriesCalculatorImpl implements CaloriesCalculator {
    private static Logger logger = LoggerFactory.getLogger(Processor.class);

    private Double noramaCalories;
    private FoodService foodService;

    @Override
    public List<Food> getFoods(final LocalDateTime from, final LocalDateTime to) {
        return foodService.getStream()
                .filter(food -> food.getDateOfEating().isAfter(from) && food.getDateOfEating().isBefore(to))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkDailyLimit(final LocalDate fromDate, final LocalDate toDate) {
        if (fromDate == null){
            throw new IllegalArgumentException("argument fromDate of checkDailyLimit() are null");
        }
        if (toDate == null){
            throw new IllegalArgumentException("argument fromDate of checkDailyLimit() are null");
        }

        final LocalDateTime fromDateTime = fromDate.atTime(0,0);
        final LocalDateTime toDateTime = toDate.plusDays(1).atTime(0,0);
        return checkLimit(fromDateTime, toDateTime);
    }

    @Override
    public boolean checkDailyLimit(final LocalDate date){
        if (date == null){
            throw new IllegalArgumentException("argument checkDailyLimit of checkDailyLimit() are null");
        }

        final LocalDateTime fromDateTime = date.atTime(0,0);
        final LocalDateTime toDateTime = date.plusDays(1).atTime(0,0);
        return checkLimit(fromDateTime, toDateTime);
    }

    @Override
    public List<FoodStat> getStatByDays(final LocalDate fromDate, final LocalDate toDate){
        if (fromDate == null){
            throw new IllegalArgumentException("argument fromDate of checkDailyLimit() are null");
        }
        if (toDate == null){
            throw new IllegalArgumentException("argument fromDate of checkDailyLimit() are null");
        }

        final LocalDateTime fromDateTime = fromDate.atTime(0,0);
        final LocalDateTime toDateTime = toDate.plusDays(1).atTime(0,0);
        return getStatByDays(fromDateTime, toDateTime);
    }

    @Override
    public List<FoodStat> getStatByDay(LocalDate date) {
        if (date == null){
            throw new IllegalArgumentException("argument checkDailyLimit of checkDailyLimit() are null");
        }

        final LocalDateTime fromDateTime = date.atTime(0,0);
        final LocalDateTime toDateTime = date.plusDays(1).atTime(0,0);
        return getStatByDays(fromDateTime, toDateTime);
    }

    private List<FoodStat> getStatByDays(final LocalDateTime fromDate, final LocalDateTime toDate){
        Map<LocalDate, List<Food>> filteredFoods = foodService.getStream()
                .filter(food ->  food.getDateOfEating().isAfter(fromDate)
                        && food.getDateOfEating().isBefore(toDate))
                .peek(food ->  logger.info("filtered food: " + food))
                .collect(Collectors.groupingBy(food->food.getDateOfEating().toLocalDate(),
                        HashMap::new, Collectors.toList()));

        return filteredFoods.values().stream()
                .flatMap(list -> {
                    double caloriesPerDay = list.stream()
                            .map(Food::getCalories)
                            .peek(food -> logger.info("Calculatin caloriesPerDay"))
                            .reduce(0d, (a, b)->a + b);

                    return list.stream()
                            .map(food -> FoodStatConverter.getFoodStat(food, caloriesPerDay));
                }).sorted(Comparator.comparing(FoodStat::getDateOfEating))
                .collect(Collectors.toList());
    }

    private boolean checkLimit(final LocalDateTime fromDate, final LocalDateTime toDate){
        return foodService.getStream()
                .filter(food ->  food.getDateOfEating().isAfter(fromDate)
                        && food.getDateOfEating().isBefore(toDate))
                .collect(Collectors.groupingBy(food->food.getDateOfEating().toLocalDate(),
                        HashMap::new, Collectors.summingDouble(Food::getCalories)))
                .values().stream()
                .allMatch(c -> c.compareTo(noramaCalories) < 0);
    }

    public FoodService getFoodService() {
        return foodService;
    }

    public void setFoodService(final FoodService foodService) {
        this.foodService = foodService;
    }

    public Double getNoramaCalories() {
        return noramaCalories;
    }

    public void setNoramaCalories(final Double noramaCalories) {
        this.noramaCalories = noramaCalories;
    }
}
