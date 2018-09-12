package com.grow.java8.calories.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.data.FoodStat;
import com.grow.java8.calories.dao.FoodDAO;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.grow.java8.calories.service.CaloriesCalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
class CaloriesCalculatorImplTest {
    private static final String FILE_NAME = "test.json";
    private static List<Food> foodList;

    private static final Double NORMA_CALORIES = 1000d;
    
    @Mock
    private FoodDAO foodDAO;

    @InjectMocks
    private CaloriesCalculator caloriesCalculator = new CaloriesCalculatorImpl();

    @BeforeAll
    static private void init()  {
        try (FileInputStream inFile = new FileInputStream(new ClassPathResource(FILE_NAME).getFile())){
            ObjectMapper objectMapper = new ObjectMapper();
            foodList = objectMapper.readValue(inFile, new TypeReference<List<Food>>(){});
        } catch (IOException ex) {
            foodList = Lists.emptyList();
        }
    }

    @ParameterizedTest
    @CsvSource({"2018-05-01, 900, true",
                "2018-05-02, 1300, true",
                "2018-05-01, 800, false",
                "2018-05-02, 800, false"})
    void shouldCalculateCaloriesOneDay(LocalDate date, Double normaCalories, boolean result) {
        when(foodDAO.getStream()).thenReturn(foodList.stream());
        assertEquals(result, caloriesCalculator.checkDailyLimit(date, normaCalories));
    }
    
    @ParameterizedTest
    @CsvSource({"2018-05-03, 2018-05-10, 1000, true",
            "2018-05-03, 2018-05-10, 800, false"})
    void shouldCalculateCaloriesInterval(LocalDate dateFrom, LocalDate dateTo, Double normaCalories, boolean result) {
        when(foodDAO.getStream()).thenReturn(foodList.stream());
    
        assertEquals(result, caloriesCalculator.checkDailyLimit(dateFrom, dateTo, normaCalories));
    }

    @Test
    void shouldReturnListFoodStat() {
        when(foodDAO.getStream()).thenReturn(foodList.stream());
        
        LocalDate fromDate = LocalDate.of(2018,02, 01);
        LocalDate toDate = LocalDate.of(2018,05, 05);
        List<FoodStat> result = caloriesCalculator.getStatByDays(fromDate, toDate);

        List<FoodStat> expect = Stream.of(
                "food-1,150.0,2018-05-01T10:10:30,850.0",
                "food-2,350.0,2018-05-01T10:15:30,850.0",
                "food-3,350.0,2018-05-01T10:19:30,850.0",
                "food-4,550.0,2018-05-02T10:11:30,1270.0",
                "food-5,50.0,2018-05-02T10:15:33,1270.0",
                "food-6,150.0,2018-05-02T10:19:30,1270.0",
                "food-7,520.0,2018-05-02T10:20:30,1270.0",
                "food-8,678.0,2018-05-03T10:12:30,928.0",
                "food-9,250.0,2018-05-03T10:13:20,928.0",
                "food-10,230.0,2018-05-04T10:15:30,610.0",
                "food-11,130.0,2018-05-04T10:16:35,610.0",
                "food-12,250.0,2018-05-04T10:18:30,610.0")
                .map(this::parseFoodStat)
                .collect(Collectors.toList());

        assertIterableEquals(result, expect);
    }
    
    @Test
    void shouldReturnListFoodStatByOneDay() {
        when(foodDAO.getStream()).thenReturn(foodList.stream());
        
        LocalDate date = LocalDate.of(2018,5, 2);
        List<FoodStat> result = caloriesCalculator.getStatByDay(date);
        
        List<FoodStat> expect = Stream.of(
                "food-4,550.0,2018-05-02T10:11:30,1270.0",
                "food-5,50.0,2018-05-02T10:15:33,1270.0",
                "food-6,150.0,2018-05-02T10:19:30,1270.0",
                "food-7,520.0,2018-05-02T10:20:30,1270.0")
                .map(this::parseFoodStat)
                .collect(Collectors.toList());
        
        assertIterableEquals(result, expect);
    }

    @Test
    void shouldThrowIllegalArgumentException() {
        LocalDate date = LocalDate.of(2018,01, 2);

        assertThrows(IllegalArgumentException.class, ()-> caloriesCalculator.checkDailyLimit(date, null, NORMA_CALORIES));
        assertThrows(IllegalArgumentException.class, ()-> caloriesCalculator.checkDailyLimit(null, date, NORMA_CALORIES));
        assertThrows(IllegalArgumentException.class, ()-> caloriesCalculator.checkDailyLimit(null, null, NORMA_CALORIES));
        assertThrows(IllegalArgumentException.class, ()-> caloriesCalculator.checkDailyLimit(null, NORMA_CALORIES));
        assertThrows(IllegalArgumentException.class, ()-> caloriesCalculator.getStatByDays(date, null));
        assertThrows(IllegalArgumentException.class, ()-> caloriesCalculator.getStatByDays(null, date));
        assertThrows(IllegalArgumentException.class, ()-> caloriesCalculator.getStatByDay(null));
    }
    
    private FoodStat parseFoodStat(final String value){
        String[] values =  value.split(",");
        FoodStat foodStat = new FoodStat();
        foodStat.setName(values[0]);
        foodStat.setCalories(Double.parseDouble(values[1]));
        foodStat.setDateOfEating(LocalDateTime.parse(values[2], DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        foodStat.setCaloriesPerDay(Double.parseDouble(values[3]));
        
        return foodStat;
    }
    
}