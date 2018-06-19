package com.grow.java8.calories.data;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

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
        return ToStringBuilder.reflectionToString(this, JSON_STYLE);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Food food = (Food) o;
        return Double.compare(food.calories, calories) == 0 &&
                Objects.equals(name, food.name) &&
                Objects.equals(dateOfEating, food.dateOfEating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, calories, dateOfEating);
    }
}
