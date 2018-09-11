package com.grow.java8.calories.service.impl;

import com.grow.java8.calories.dao.FoodDAO;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
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
    public Food setFood(final Long id, final String name, final LocalDateTime date, final Double calories) {
        Food food =  foodDAO.getFood(id);
        if (food == null){
            food = new Food();
        }
    
        food.setName(name);
        food.setDateOfEating(date);
        food.setCalories(calories);
    
        return setFood(food);
    }

    @Override
    public void removeFood(Long id) {
        final Food food = foodDAO.getFood(id);
        if (food != null) {
            foodDAO.removeFood(food);
        }
    }

}
