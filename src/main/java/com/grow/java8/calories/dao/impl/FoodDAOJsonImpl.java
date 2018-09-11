package com.grow.java8.calories.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.dao.FoodDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@Profile("json")
public class FoodDAOJsonImpl implements FoodDAO {
    private static Logger logger = LoggerFactory.getLogger(FoodDAOJsonImpl.class);

    @Value("${file.name}")
    private String fileName;

    @Autowired
    private ResourceLoader resourceLoader;

    private List<Food> read() {
        final Resource resource = resourceLoader.getResource(fileName);
        try(InputStream inputStream = resource.getInputStream()) {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, new TypeReference<List<Food>>(){});
        } catch (IOException ex) {
            logger.error("Error reading file! ", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Stream<Food> getStream(){
        return read().stream();
    }

    @Override
    public Food getFood(Long id) {
        return null;
    }

    @Override
    public Food setFood(Food food) {
        return null;
    }

    @Override
    public void removeFood(Food food){

    }

}
