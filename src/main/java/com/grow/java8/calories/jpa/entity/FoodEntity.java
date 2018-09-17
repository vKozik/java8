package com.grow.java8.calories.jpa.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.grow.java8.calories.data.Food;

@Entity
@Table(name = "Food")
public class FoodEntity extends Food {
    @Id
    @GeneratedValue(generator="SEQUENCE_GENERATOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="SEQUENCE_GENERATOR",sequenceName="Food_seq", allocationSize=10)
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public double getCalories() {
        return calories;
    }

    @Override
    public LocalDateTime getDateOfEating() {
        return dateOfEating;
    }
}
