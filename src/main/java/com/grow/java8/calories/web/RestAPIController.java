package com.grow.java8.calories.web;

import java.time.LocalDate;
import java.util.Collection;

import com.grow.java8.calories.data.FoodStat;
import com.grow.java8.calories.service.CaloriesCalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPIController {
    @Autowired
    CaloriesCalculator caloriesCalculator;
    
    @RequestMapping(value = "/api/test", method =  RequestMethod.GET)
    public ResponseEntity SoftLedOn() {
        return new ResponseEntity("Ok", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/stat",
            method =  RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<FoodStat>> getStatByDays(
            @RequestParam(value = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        if (toDate == null){
            return new ResponseEntity<Collection<FoodStat>>(caloriesCalculator.getStatByDay(fromDate), HttpStatus.OK);
        }
        
        return new ResponseEntity<Collection<FoodStat>>(caloriesCalculator.getStatByDays(fromDate, toDate), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/checkLimit",
            method =  RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> getStatByDays(
            @RequestParam(value = "norm") Double norma,
            @RequestParam(value = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        
        if (toDate == null){
            return new ResponseEntity<Boolean>(caloriesCalculator.checkDailyLimit(fromDate, norma), HttpStatus.OK);
        }
        
        return new ResponseEntity<Boolean>(caloriesCalculator.checkDailyLimit(fromDate, toDate, norma), HttpStatus.OK);
    }
}
