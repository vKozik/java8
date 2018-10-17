package com.grow.java8.calories.service;

import com.grow.java8.calories.data.Food;

import java.time.LocalDateTime;
import java.util.List;

public interface FoodService<T extends Food> {
    List<T> getAll();

    T getFood(Long id);

    T setFood(T food);

    void setFood(Long id, String name, LocalDateTime date, Double calories);

    void removeFood(Long id);
}
