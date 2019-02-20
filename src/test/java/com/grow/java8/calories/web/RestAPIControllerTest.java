package com.grow.java8.calories.web;

import com.grow.java8.calories.CaloriesMain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes =  CaloriesMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestAPIControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnTestMessage()
            throws Exception {

        mockMvc.perform(get("/api/test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ok")));
    }

    @Test
    public void shouldReturnStatistics()
            throws Exception {

        mockMvc.perform(get("/api/stat?fromDate=2018-08-01&toDate=2018-08-05")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(12)))
                .andExpect(jsonPath("$[0].name", is("FOOD-1")))
                .andExpect(jsonPath("$[0].calories", is(1500.0)))
                .andExpect(jsonPath("$[0].caloriesPerDay", is(2200.0)))
                .andExpect(jsonPath("$[11].name", is("FOOD-12")))
                .andExpect(jsonPath("$[11].calories", is(250.0)))
                .andExpect(jsonPath("$[11].caloriesPerDay", is(610.0)))
                .andExpect(status().isOk());
    }
    
    @Test
    public void shouldReturnTrueOnCheckLimit()
            throws Exception {
        
        mockMvc.perform(get("/api/checkLimit?norm=3500&fromDate=2018-08-01&toDate=2018-08-05")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")));
    }
    
    @Test
    public void shouldReturnFalseOnCheckLimit()
            throws Exception {
        
        mockMvc.perform(get("/api/checkLimit?norm=1500&fromDate=2018-08-01&toDate=2018-08-05")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("false")));
    }
}