package com.grow.java8.calories.service;

import com.grow.java8.calories.data.Food;

import java.util.List;

public interface FoodService {
    List<Food> getAll();

    Food getFood(Long id);

    Food setFood(Food food);

    Food addFood(Food food);
}
