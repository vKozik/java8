package com.grow.java8.calories;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.grow.java8.calories.data.Attributes;

import static com.grow.java8.calories.constants.ArgumentConstants.*;

public class ArgumentParser {
    public static Attributes parse(final String[] args, final DateTimeFormatter dateFormatter){
        Attributes result = new Attributes();
        result.setFromDate(LocalDate.parse(args[FROM_DATE_INDEX], dateFormatter));
        result.setToDate(LocalDate.parse(args[TO_DATE_INDEX], dateFormatter));
        result.setNoramaCalories(Double.valueOf(args[NORAMA_INDEX]));
        return result;
    }
}
