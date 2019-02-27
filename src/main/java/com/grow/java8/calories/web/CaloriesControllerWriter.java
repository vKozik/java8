package com.grow.java8.calories.web;

import com.grow.java8.calories.data.Food;
import com.grow.java8.calories.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@Profile("hibernate")
public class CaloriesControllerWriter {
    @Autowired
    private FoodService foodService;

    @GetMapping("/foods/add")
    public String addFood(Model model) {
        model.addAttribute("pageHeader", "Add new");
        
        return "foodEdit";
    }
    
    @GetMapping("/foods/update/{id}")
    public String updateFood(@PathVariable(value = "id") Long id, Model model) {
        Food food = foodService.getFood(id);
    
        model.addAttribute("pageHeader", "Update");
        model.addAttribute("foodId", food.getId());
        model.addAttribute("foodName", food.getName());
        model.addAttribute("foodDate", food.getDateOfEating());
        model.addAttribute("foodCalories", food.getCalories());
        
        return "foodEdit";
    }

    @PostMapping("/foods/update")
    public String saveFood(@RequestParam Long foodId,
            @RequestParam String foodName,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime foodDate,
            @RequestParam Double foodCalories) {
        if (foodId != null) {
            foodService.setFood(foodId, foodName, foodDate, foodCalories);
        } else {
            foodService.addFood(foodName, foodDate, foodCalories);
        }
        
        return "redirect:/foods" ;
    }

    @GetMapping("/foods/delete/{id}")
    public String deleteFood(@PathVariable(value = "id") Long id) {
        foodService.removeFood(id);

        return "redirect:/foods" ;
    }
}