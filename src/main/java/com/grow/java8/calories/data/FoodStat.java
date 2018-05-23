package com.grow.java8.calories.data;

import java.time.LocalDateTime;
import java.util.Objects;

public class FoodStat {
    private String name;
    private double calories;
    private LocalDateTime dateOfEating ;
    private double caloriesPerDay;

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", dateOfEating=" + dateOfEating +
                ", caloriesPerDay=" + caloriesPerDay +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public LocalDateTime getDateOfEating() {
        return dateOfEating;
    }

    public void setDateOfEating(LocalDateTime dateOfEating) {
        this.dateOfEating = dateOfEating;
    }

    public double getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(double caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodStat foodStat = (FoodStat) o;
        return Double.compare(foodStat.calories, calories) == 0 &&
                Double.compare(foodStat.caloriesPerDay, caloriesPerDay) == 0 &&
                Objects.equals(name, foodStat.name) &&
                Objects.equals(dateOfEating, foodStat.dateOfEating);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, calories, dateOfEating, caloriesPerDay);
    }
}
