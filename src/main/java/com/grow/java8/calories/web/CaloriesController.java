package com.grow.java8.calories.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.data.FoodStat;
import com.grow.java8.calories.service.CaloriesCalculator;

import com.grow.java8.calories.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CaloriesController {
    private static final String READ_ONLY_PROFILE = "json";

    @Autowired
    private CaloriesCalculator caloriesCalculator;

    @Autowired
    private FoodService foodService;

    @Autowired
    private Environment environment;

    @GetMapping("/stat")
    public String getStat(
            @RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            @RequestParam(value = "norma", required = false) Double norma,
            Model model) {
        fromDate = Optional.ofNullable(fromDate).orElse(LocalDate.now());
        toDate = Optional.ofNullable(toDate).orElse(fromDate);

        final List<FoodStat> foodStats = caloriesCalculator.getStatByDays(fromDate, toDate);
        
        model.addAttribute("norma", norma);
        model.addAttribute("foodStats", foodStats);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("isReadOnly", isReadOnly());

        return "stat";
    }

    @GetMapping("/foods")
    public String getAllFoods(Model model) {
        final List<Food> foods = foodService.getAll();
        model.addAttribute("foods", foods);
        model.addAttribute("isReadOnly", isReadOnly());

        return "foods";
    }

    @CacheEvict(value = "myCache", allEntries = true)
    @GetMapping("/clearCache")
    public String clearCache(Model model) {
        return "redirect:/foods";
    }

    private boolean isReadOnly(){
        String[] activeProfiles = environment.getActiveProfiles();
        return READ_ONLY_PROFILE.equals(activeProfiles[0]);
    }
    
    @ExceptionHandler(value=NumberFormatException.class)
    public String handleNumberFormatException(NumberFormatException ex, Model model)
    {
        model.addAttribute("message", "Wrong parameters");
        return "exception";
    }
}
