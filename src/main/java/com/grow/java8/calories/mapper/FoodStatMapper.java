package com.grow.java8.calories.mapper;

import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.mapstruct.*;

import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.data.FoodStat;

@Mapper
public interface FoodStatMapper {
    FoodStat foodToFoodStat(Food food, Double caloriesPerDay);
    
    @AfterMapping
    default void setPhones(@MappingTarget FoodStat foodStat) {
        foodStat.setName(Optional.of(foodStat.getName()).orElse(Strings.EMPTY).toUpperCase());
    }
}
