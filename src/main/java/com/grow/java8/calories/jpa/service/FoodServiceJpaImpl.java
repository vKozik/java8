package com.grow.java8.calories.jpa.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.grow.java8.calories.dao.FoodDAO;
import com.grow.java8.calories.jpa.entity.FoodEntity;
import com.grow.java8.calories.service.FoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Profile("hibernate")
public class FoodServiceJpaImpl implements FoodService<FoodEntity> {
    private static final String CANNOT_FIND_FOOD = "Cannot find the food, id: %d";

    @Autowired
    private FoodDAO<FoodEntity> foodDAO;

    @Override
    @Cacheable(value = "myCache", key = "'AllFood'")
    public List<FoodEntity> getAll() {
        return foodDAO.getStream().collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "myCache", key = "#id")
    public FoodEntity getFood(Long id) {
        return foodDAO.getFood(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(CANNOT_FIND_FOOD, id)));
    }

    @Override
    @CachePut(value = "myCache", key="#food.id")
    public FoodEntity setFood(FoodEntity food) {
        return foodDAO.setFood(food);
    }
    
    @Override
    @CachePut(value = "myCache", key="#id")
    @CacheEvict(value = "myCache", key = "'AllFood'")
    public FoodEntity setFood(final Long id, final String name, final LocalDateTime date, final Double calories) {
        FoodEntity food = foodDAO.getFood(id).orElseThrow(() -> new IllegalArgumentException(CANNOT_FIND_FOOD));

        food.setName(name);
        food.setDateOfEating(date);
        food.setCalories(calories);
    
        return setFood(food);
    }
    
    @Override
    @Caching(evict = {
            @CacheEvict(value = "myCache", key = "'AllFood'"),
    })
    public FoodEntity addFood(final String name, final LocalDateTime date, final Double calories) {
        FoodEntity food = new FoodEntity();
        
        food.setName(name);
        food.setDateOfEating(date);
        food.setCalories(calories);
        
        return setFood(food);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "myCache", key = "'AllFood'"),
            @CacheEvict(value = "myCache", key = "#id")
    })
    public void removeFood(Long id) {
        final FoodEntity food = foodDAO.getFood(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(CANNOT_FIND_FOOD, id)));

        foodDAO.removeFood(food);
    }

}
