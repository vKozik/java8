package com.grow.java8.calories.converters;

import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.data.FoodStat;

public class FoodStatConverter {
    public static FoodStat getFoodStat(final Food food, final double caloriesPerDay){
        FoodStat foodStat = new FoodStat();
        foodStat.setCalories(food.getCalories());
        foodStat.setDateOfEating(food.getDateOfEating());
        foodStat.setName(food.getName());
        foodStat.setCaloriesPerDay(caloriesPerDay);
        return foodStat;
    }
}
