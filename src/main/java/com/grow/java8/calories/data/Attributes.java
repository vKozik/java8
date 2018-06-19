package com.grow.java8.calories.data;

import java.time.LocalDate;

public class Attributes {
    private Double  noramaCalories;
    private LocalDate fromDate;
    private LocalDate toDate;
    
    public Double getNoramaCalories() {
        return noramaCalories;
    }
    
    public void setNoramaCalories(final Double noramaCalories) {
        this.noramaCalories = noramaCalories;
    }
    
    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(final LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(final LocalDate toDate) {
        this.toDate = toDate;
    }
}
