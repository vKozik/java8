package com.grow.java8.calories.dao.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.dao.FoodDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class FoodDAOJsonImpl implements FoodDAO {
    private static Logger logger = LoggerFactory.getLogger(FoodDAOJsonImpl.class);
    private String fileName;

    public FoodDAOJsonImpl(String fileName) {
        this.fileName = fileName;
    }

    private List<Food> read() {
        try (FileInputStream inFile = new FileInputStream(new ClassPathResource(fileName).getFile())){
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inFile, new TypeReference<List<Food>>(){});
        } catch (IOException ex) {
            logger.error("Error reading file! ", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Stream<Food> getStream(){
        return read().stream();
    }
}
