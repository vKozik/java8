package com.grow.java8.calories;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.grow.java8.calories.data.Attributes;

import static com.grow.java8.calories.constants.ArgumentConstants.*;

public class ArgumentParser {
    private DateTimeFormatter dateTimeFormatter;

    public static Attributes parse(final String[] args, final DateTimeFormatter dateFormatter){
        Attributes result = new Attributes();
        result.setNoramaCalories(Double.valueOf(args[NORMS_INDEX]));
        result.setFileName(args[FILE_NAME_INDEX]);
        result.setFromDate(StringToLocalDate(args[FROM_DATE_INDEX], dateFormatter));
        result.setToDate(StringToLocalDate(args[TO_DATE_INDEX], dateFormatter));

        return result;
    }

    private static LocalDate StringToLocalDate(final String date, final DateTimeFormatter dateFormatter){
        return LocalDate.parse(date, dateFormatter);
    }
}
