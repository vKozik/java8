package com.grow.java8.calories.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.service.FoodService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoodServiceImpl implements FoodService {
    private static Logger logger = LoggerFactory.getLogger(FoodServiceImpl.class);
    private List<Food> foods;

    @Override
    public void read(final String fileName) {
        try (FileInputStream inFile = new FileInputStream(fileName)){
            ObjectMapper objectMapper = new ObjectMapper();
            foods = objectMapper.readValue(inFile, new TypeReference<List<Food>>(){});
        } catch (IOException ex) {
            logger.error("Error reading file! ", ex);
        }
    }

    @Override
    public List<Food> getAll(){
        return foods;
    }

    @Override
    public Stream<Food> getStream(){
        return foods == null ? Stream.empty() : foods.stream();
    }
}
