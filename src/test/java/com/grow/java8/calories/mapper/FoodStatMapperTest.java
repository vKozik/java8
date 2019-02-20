package com.grow.java8.calories.mapper;

import java.time.LocalDateTime;

import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.data.FoodStat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class FoodStatMapperTest {
    private static final double DELTA = 1e-10;
    
    private final FoodStatMapper mapper = Mappers.getMapper(FoodStatMapper.class);
    
    @Test
    void shouldMapFoodStat() {
        Food food = new Food();
        food.setId(1L);
        food.setName("test-food");
        food.setCalories(500);
        food.setDateOfEating(LocalDateTime.now());
        double caloriesPerDay = 1000d;
        
        FoodStat foodStat = mapper.foodToFoodStat(food, caloriesPerDay);
    
        assertEquals(food.getId(), foodStat.getId());
        assertEquals(food.getName().toUpperCase(), foodStat.getName());
        assertEquals(food.getCalories(), foodStat.getCalories(), DELTA);
        assertEquals(food.getDateOfEating(), foodStat.getDateOfEating());
        assertEquals(caloriesPerDay, foodStat.getCaloriesPerDay(), DELTA);
    }
    
}