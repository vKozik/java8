package com.grow.java8.calories.web;

import java.time.LocalDate;
import java.util.List;

import com.grow.java8.calories.data.FoodStat;
import com.grow.java8.calories.service.CaloriesCalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CaloriesController {
    @Autowired
    CaloriesCalculator caloriesCalculator;
    
    @GetMapping("/stat")
    public String greeting(
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
}
