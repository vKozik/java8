package com.grow.java8.calories.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.dao.FoodDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoodDAOJsonImpl implements FoodDAO {
    private static Logger logger = LoggerFactory.getLogger(FoodDAOJsonImpl.class);
    private String fileName;

    public FoodDAOJsonImpl(String fileName){
        this.fileName = fileName;
    }

    private List<Food> read() {
        Optional<URL> urlFile = Optional.ofNullable(this.getClass().getClassLoader().getResource(fileName));
        try (FileInputStream inFile = new FileInputStream(urlFile.orElseThrow(FileNotFoundException::new).getFile())){
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inFile, new TypeReference<List<Food>>(){});
        } catch (FileNotFoundException ex) {
            logger.error("File " + fileName + "not found! ", ex);
            return Collections.emptyList();
        } catch (IOException ex) {
            logger.error("Error reading file! ", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Food> getAll(){
        return read();
    }

    @Override
    public Stream<Food> getStream(){
        return read().stream();
    }
}
