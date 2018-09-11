package com.grow.java8.calories.service;

import com.grow.java8.calories.data.Food;

import java.time.LocalDateTime;
import java.util.List;

public interface FoodService {
    List<Food> getAll();

    Food getFood(Long id);

    Food setFood(Food food);

    Food setFood(Long id, String name, LocalDateTime date, Double calories);

    void removeFood(Long id);
}
