package com.grow.java8.calories.json.service;

import com.grow.java8.calories.dao.FoodDAO;
import com.grow.java8.calories.jpa.entity.FoodEntity;
import com.grow.java8.calories.json.data.FoodJson;
import com.grow.java8.calories.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("json")
public class FoodServiceJsonImpl implements FoodService<FoodJson> {
    private static final String READ_ONLY_MESSAGE = "The profile is read only";
    private static final String CANNOT_FIND_FOOD = "Cannot find the food, id: %d";

    @Autowired
    private FoodDAO<FoodJson> foodDAO;

    @Override
    @Cacheable(value = "myCache")
    public List<FoodJson> getAll() {
        return foodDAO.getStream().collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "myCache")
    public FoodJson getFood(Long id) {
        return foodDAO.getFood(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(CANNOT_FIND_FOOD, id)));
    }

    @Override
    public FoodJson setFood(FoodJson food) {
        throw new UnsupportedOperationException(READ_ONLY_MESSAGE);
    }
    
    @Override
    public FoodEntity setFood(final Long id, final String name, final LocalDateTime date, final Double calories) {
        throw new UnsupportedOperationException(READ_ONLY_MESSAGE);
    }
    
    @Override
    public FoodEntity addFood(final String name, final LocalDateTime date, final Double calories) {
        throw new UnsupportedOperationException(READ_ONLY_MESSAGE);
    }

    @Override
    public void removeFood(Long id) {
        throw new UnsupportedOperationException(READ_ONLY_MESSAGE);
    }

}
