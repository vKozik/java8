package com.grow.java8.calories.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.data.FoodStat;
import com.grow.java8.calories.mapper.FoodStatMapper;
import com.grow.java8.calories.service.CaloriesCalculator;
import com.grow.java8.calories.dao.FoodDAO;
import org.apache.commons.lang3.Validate;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaloriesCalculatorImpl implements CaloriesCalculator {
    private static final Logger logger = LoggerFactory.getLogger(CaloriesCalculatorImpl.class);

    private static final String ARGUMENT_ERROR_MESSAGE = "argument %s of checkDailyLimit() are null";

    @Autowired
    private FoodDAO<?> foodDAO;

    @Override
    public boolean checkDailyLimit(final LocalDate fromDate, final LocalDate toDate, Double normaCalories) {
        Validate.notNull(fromDate, String.format(ARGUMENT_ERROR_MESSAGE, "fromDate"));
        Validate.notNull(toDate, String.format(ARGUMENT_ERROR_MESSAGE, "toDate"));

        LocalDateTime fromDateTime = fromDate.atTime(0,0);
        LocalDateTime toDateTime = toDate.plusDays(1).atTime(0,0);
        return checkLimit(fromDateTime, toDateTime, normaCalories);
    }

    @Override
    public boolean checkDailyLimit(final LocalDate date, Double normaCalories){
        Validate.notNull(date, String.format(ARGUMENT_ERROR_MESSAGE, "date"));

        final LocalDateTime fromDateTime = date.atTime(0,0);
        final LocalDateTime toDateTime = date.plusDays(1).atTime(0,0);
        return checkLimit(fromDateTime, toDateTime, normaCalories);
    }

    @Override
    public List<FoodStat> getStatByDays(final LocalDate fromDate, final LocalDate toDate){
        Validate.notNull(fromDate, String.format(ARGUMENT_ERROR_MESSAGE, "fromDate"));
        Validate.notNull(toDate, String.format(ARGUMENT_ERROR_MESSAGE, "toDate"));

        final LocalDateTime fromDateTime = fromDate.atTime(0,0);
        final LocalDateTime toDateTime = toDate.plusDays(1).atTime(0,0);
        return getStatByDays(fromDateTime, toDateTime);
    }

    @Override
    public List<FoodStat> getStatByDay(final LocalDate date) {
        Validate.notNull(date, String.format(ARGUMENT_ERROR_MESSAGE, "date"));

        final LocalDateTime fromDateTime = date.atTime(0,0);
        final LocalDateTime toDateTime = date.plusDays(1).atTime(0,0);
        return getStatByDays(fromDateTime, toDateTime);
    }

    private List<FoodStat> getStatByDays(final LocalDateTime fromDate, final LocalDateTime toDate){
        Map<LocalDate, List<Food>> filteredFoods = foodDAO.getStream()
                .filter(food ->  food.getDateOfEating().isAfter(fromDate)
                        && food.getDateOfEating().isBefore(toDate))
                .peek(food ->  logger.info("filtered food: " + food))
                .collect(Collectors.groupingBy(food->food.getDateOfEating().toLocalDate(),
                        HashMap::new, Collectors.toList()));

        FoodStatMapper mapper = Mappers.getMapper(FoodStatMapper.class);

        return filteredFoods.values().stream()
                .flatMap(list -> {
                    double caloriesPerDay = list.stream()
                            .map(Food::getCalories)
                            .peek(calories -> logger.info("Calculating caloriesPerDay. Added " + calories))
                            .reduce(0d, (a, b)->a + b);

                    return list.stream()
                            .map(food -> mapper.foodToFoodStat(food, caloriesPerDay));
                }).sorted(Comparator.comparing(FoodStat::getDateOfEating))
                .collect(Collectors.toList());
    }

    private boolean checkLimit(final LocalDateTime fromDate, final LocalDateTime toDate, Double normaCalories){
        return foodDAO.getStream()
                .filter(food ->  food.getDateOfEating().isAfter(fromDate)
                        && food.getDateOfEating().isBefore(toDate))
                .collect(Collectors.groupingBy(food->food.getDateOfEating().toLocalDate(),
                        HashMap::new, Collectors.summingDouble(Food::getCalories)))
                .values().stream()
                .allMatch(c -> Double.compare(c, normaCalories) < 0);

    }

    public void setFoodDAO(final FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }
}
