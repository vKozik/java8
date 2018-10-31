package com.grow.java8.calories.mapper;

import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.mapstruct.*;

import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.data.FoodStat;

@Mapper
public interface FoodStatMapper {
    
    @Mappings({
            @Mapping(source = "food.id", target = "id"),
            @Mapping(source = "food.name", target = "name"),
            @Mapping(source = "food.calories", target = "calories"),
            @Mapping(source = "food.dateOfEating", target = "dateOfEating"),
            @Mapping(source = "caloriesPerDay", target = "caloriesPerDay")
    })
    FoodStat FoodToFoodStat(Food food, Double caloriesPerDay);
    
    @AfterMapping
    default void setPhones(@MappingTarget FoodStat foodStat) {
        foodStat.setName(Optional.of(foodStat.getName()).orElse(Strings.EMPTY).toUpperCase());
    }
}
