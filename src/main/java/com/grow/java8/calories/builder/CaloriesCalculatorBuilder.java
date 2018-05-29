package com.grow.java8.calories.builder;

import com.grow.java8.calories.service.CaloriesCalculator;
import com.grow.java8.calories.dao.FoodDAO;
import com.grow.java8.calories.service.impl.CaloriesCalculatorImpl;

public class CaloriesCalculatorBuilder {
    private FoodDAO foodDAO;
    private Double noramaCalories;

    public CaloriesCalculatorBuilder addFoodDAO(final FoodDAO foodDAO){
        this.foodDAO = foodDAO;
        return this;
    }

    public CaloriesCalculatorBuilder addNoramaCalories(final Double noramaCalories){
        this.noramaCalories = noramaCalories;
        return this;
    }

    public CaloriesCalculator build(){
        return new CaloriesCalculatorImpl(noramaCalories, foodDAO);
    }
}
