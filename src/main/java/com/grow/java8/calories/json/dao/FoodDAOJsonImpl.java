package com.grow.java8.calories.json.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grow.java8.calories.dao.FoodDAO;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.json.data.FoodJson;

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
    private static final Logger logger = LoggerFactory.getLogger(FoodDAOJsonImpl.class);
    private static final String READ_ONLY_MESSAGE = "The profile is read only";

    @Value("${file.name}")
    private String fileName;

    @Autowired
    private ResourceLoader resourceLoader;

    private List<Food> read() {
        final Resource resource = resourceLoader.getResource(fileName);
        try(InputStream inputStream = resource.getInputStream()) {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, new TypeReference<List<FoodJson>>(){});
        } catch (IOException ex) {
            logger.error("Error reading file! ", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Stream<? extends  Food> getStream(){
        return read().stream();
    }

    @Override
    public Food getFood(Long id) {
        return read().stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Food with id " + id + " not found"));
    }

    @Override
    public Food setFood(Food food) {
        throw new UnsupportedOperationException(READ_ONLY_MESSAGE);
    }

    @Override
    public void removeFood(Food food){
        throw new UnsupportedOperationException(READ_ONLY_MESSAGE);
    }

}
