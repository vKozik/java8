package com.grow.java8.calories.json.data;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.data.LocalDateDeserializer;
import com.grow.java8.calories.data.LocalDateSerializer;


public class FoodJson extends Food {
    
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Override
    public LocalDateTime getDateOfEating() {
        return dateOfEating;
    }

}
