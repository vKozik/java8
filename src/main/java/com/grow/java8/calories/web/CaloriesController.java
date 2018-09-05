package com.grow.java8.calories.web;

import java.time.LocalDate;
import java.util.List;

import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.data.FoodStat;
import com.grow.java8.calories.service.CaloriesCalculator;

import com.grow.java8.calories.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CaloriesController {
    private static final String READ_ONLY_PROFILE = "json";

    @Autowired
    CaloriesCalculator caloriesCalculator;

    @Autowired
    FoodService foodService;

    @Autowired
    private Environment environment;

    @GetMapping("/stat")
    public String getStat(
            @RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            @RequestParam(value = "norma", required = false) Double norma,
            Model model) {
    
        if (fromDate == null) {
            fromDate = LocalDate.now();
        }
    
        if (toDate == null) {
            toDate = fromDate;
        }
        
        final List<FoodStat> foodStats = caloriesCalculator.getStatByDays(fromDate, toDate);
        
        model.addAttribute("norma", norma);
        model.addAttribute("foodStats", foodStats);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        
        return "stat";
    }


    @GetMapping("/foods")
    public String getAllFoods(Model model) {
        String[] activeProfiles = environment.getActiveProfiles();

        final List<Food> foods = foodService.getAll();
        model.addAttribute("foods", foods);
        model.addAttribute("isReadOnly", READ_ONLY_PROFILE.equals(activeProfiles[0]));

        return "foods";
    }

}
