package com.grow.java8.calories.jpa.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.grow.java8.calories.dao.FoodDAO;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.jpa.entity.FoodEntity;
import com.grow.java8.calories.service.FoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Profile("hibernate")
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodDAO foodDAO;

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
    public void setFood(final Long id, final String name, final LocalDateTime date, final Double calories) {
        Food food =  foodDAO.getFood(id);
        if (food == null){
            food = new FoodEntity();
        }
    
        food.setName(name);
        food.setDateOfEating(date);
        food.setCalories(calories);
    
        setFood(food);
    }

    @Override
    public void removeFood(Long id) {
        final Food food = foodDAO.getFood(id);
        if (food != null) {
            foodDAO.removeFood(food);
        }
    }

}
