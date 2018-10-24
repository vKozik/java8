package com.grow.java8.calories.mapper;

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
}
