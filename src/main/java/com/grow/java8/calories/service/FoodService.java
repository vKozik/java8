package com.grow.java8.calories.service;

import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.jpa.entity.FoodEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface FoodService<T extends Food> {
    List<T> getAll();

    T getFood(Long id);

    T setFood(T food);

    FoodEntity setFood(Long id, String name, LocalDateTime date, Double calories);
    
    FoodEntity addFood(String name, LocalDateTime date, Double calories);

    void removeFood(Long id);
}
