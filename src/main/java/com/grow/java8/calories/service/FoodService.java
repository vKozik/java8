package com.grow.java8.calories.service;

import java.util.List;
import java.util.stream.Stream;

import com.grow.java8.calories.data.Food;

public interface FoodService {
    void read(String fileName);
    List<Food> getAll();
    Stream<Food> getStream();
}
