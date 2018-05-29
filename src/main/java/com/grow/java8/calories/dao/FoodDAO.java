package com.grow.java8.calories.dao;

import java.util.List;
import java.util.stream.Stream;

import com.grow.java8.calories.data.Food;

public interface FoodDAO {
    List<Food> getAll();
    Stream<Food> getStream();
}
