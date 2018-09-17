package com.grow.java8.calories.dao;

import java.util.stream.Stream;

import com.grow.java8.calories.data.Food;

public interface FoodDAO {
    Stream<? extends  Food> getStream();

    Food getFood(Long id);

    Food setFood(Food food);

    void removeFood(Food food);
}
