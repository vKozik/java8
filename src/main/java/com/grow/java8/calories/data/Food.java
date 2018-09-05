package com.grow.java8.calories.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

@Entity
@Table(name = "Food")
public class Food {
    private Long id;
    private String name;
    private double calories;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime dateOfEating ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
