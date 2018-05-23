package com.grow.java8.calories.builder;

import com.grow.java8.calories.service.CaloriesCalculator;
import com.grow.java8.calories.service.FoodService;
import com.grow.java8.calories.service.impl.CaloriesCalculatorImpl;

public class CaloriesCalculatorBuilder {
    private FoodService foodService;
    private Double noramaCalories;

    public CaloriesCalculatorBuilder addFoodService(final FoodService foodService){
        this.foodService = foodService;
        return this;
    }

    public CaloriesCalculatorBuilder addNoramaCalories(final Double noramaCalories){
        this.noramaCalories = noramaCalories;
        return this;
    }

    public CaloriesCalculator build(){
        CaloriesCalculatorImpl result = new CaloriesCalculatorImpl();
        result.setFoodService(foodService);
        result.setNoramaCalories(noramaCalories);
        return result ;
    }
}
