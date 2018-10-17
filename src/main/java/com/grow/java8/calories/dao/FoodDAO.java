package com.grow.java8.calories.dao;

import java.util.Optional;
import java.util.stream.Stream;

import com.grow.java8.calories.data.Food;

public interface FoodDAO<T extends Food> {
    Stream<? extends T> getStream();

    Optional<T> getFood(Long id);

    T setFood(T food);

    void removeFood(T food);
}
