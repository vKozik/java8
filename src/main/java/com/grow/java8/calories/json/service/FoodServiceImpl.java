package com.grow.java8.calories.json.service;

import com.grow.java8.calories.dao.FoodDAO;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("json")
public class FoodServiceImpl implements FoodService {
    private static final String READ_ONLY_MESSAGE = "The profile is read only";

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
        throw new UnsupportedOperationException(READ_ONLY_MESSAGE);
    }
    
    @Override
    public void setFood(final Long id, final String name, final LocalDateTime date, final Double calories) {
        throw new UnsupportedOperationException(READ_ONLY_MESSAGE);
    }

    @Override
    public void removeFood(Long id) {
        throw new UnsupportedOperationException(READ_ONLY_MESSAGE);
    }

}
