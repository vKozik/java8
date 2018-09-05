package com.grow.java8.calories.service.impl;

import com.grow.java8.calories.dao.FoodDAO;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodDAO foodDAO;


    @Override
    public List<Food> getAll() {
        return foodDAO.getStream().collect(Collectors.toList());
    }

    @Override
    public Food getFood(Long id) {
        return foodDAO.getFood(id);
    }

    @Override
    public Food setFood(Food food) {
        return foodDAO.setFood(food);
    }

    @Override
    public Food addFood(Food food) {
        return foodDAO.setFood(food);
    }
}
