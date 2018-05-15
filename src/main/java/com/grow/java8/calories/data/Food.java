package com.grow.java8.calories.data;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Food {
    private String name;
    private double calories;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime dateOfEating ;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(final double calories) {
        this.calories = calories;
    }

    public LocalDateTime getDateOfEating() {
        return dateOfEating;
    }

    public void setDateOfEating(final LocalDateTime dateOfEating) {
        this.dateOfEating = dateOfEating;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", dateOfEating=" + dateOfEating +
                '}';
    }
}
