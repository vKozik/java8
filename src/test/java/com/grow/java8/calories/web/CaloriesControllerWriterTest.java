package com.grow.java8.calories.web;

import com.grow.java8.calories.CaloriesMain;
import org.apache.logging.log4j.util.Strings;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes =  CaloriesMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CaloriesControllerWriterTest {
    private static final String FOODS_URL = "/foods";
    private static final String FOODS_ADD_URL = "/foods/add";
    private static final String FOODS_UPDATE_URL = "/foods/update";
    private static final String FOODS_EDIT_URL = "/foods/update/10001";
    private static final String FOODS_EDIT_URL2 = "/foods/update/10025";
    private static final String DELETE_FOODS_URL = "/foods/delete/10026";

    private static final String ADD_NEW = "Add new";
    private static final String UPDATE = "Update";

    private static final long FOOD_ID = 10001L;
    private static final String FOOD_NAME = "food-1";
    private static final LocalDateTime FOOD_DATE = LocalDateTime.parse("2018-08-01T10:10:30");
    private static final double FOOD_CALORIES = 1500.0;

    private static final long FOOD_ID_FOR_UPDATE = 10025L;
    private static final String NEW_FOOD_NAME = "food-1-new";
    private static final double NEW_FOOD_CALORIES = 2700.0;
    private static final String NEW_FOOD_DATE_STRING = "2018-08-11 10:16:35";
    private static final LocalDateTime NEW_FOOD_DATE = LocalDateTime.parse("2018-08-11T10:16:35");

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnAddNewPage()
            throws Exception {
        mockMvc.perform(get(FOODS_ADD_URL))
                .andExpect(status().isOk())
                .andExpect(view().name("foodEdit"))
                .andExpect(model().attribute("pageHeader", is(ADD_NEW)));
    }

    @Test
    public void shouldReturnEditPage()
            throws Exception {
        mockMvc.perform(get(FOODS_EDIT_URL))
                .andExpect(status().isOk())
                .andExpect(view().name("foodEdit"))
                .andExpect(model().attribute("pageHeader", is(UPDATE)))
                .andExpect(model().attribute("foodId", is(FOOD_ID)))
                .andExpect(model().attribute("foodName", is(FOOD_NAME)))
                .andExpect(model().attribute("foodDate", is(FOOD_DATE)))
                .andExpect(model().attribute("foodCalories", is(FOOD_CALORIES)));
    }

    @Test
    public void shouldUpdateFood()
            throws Exception {
        mockMvc.perform(post("/foods/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("foodId", String.valueOf(FOOD_ID_FOR_UPDATE))
                .param("foodName", NEW_FOOD_NAME)
                .param("foodDate", NEW_FOOD_DATE_STRING)
                .param("foodCalories",  String.valueOf(NEW_FOOD_CALORIES))
        ).andExpect(redirectedUrl("/foods"));

        mockMvc.perform(get(FOODS_EDIT_URL2))
                .andExpect(status().isOk())
                .andExpect(view().name("foodEdit"))
                .andExpect(model().attribute("pageHeader", is(UPDATE)))
                .andExpect(model().attribute("foodId", is(FOOD_ID_FOR_UPDATE)))
                .andExpect(model().attribute("foodName", is(NEW_FOOD_NAME)))
                .andExpect(model().attribute("foodDate", is(NEW_FOOD_DATE)))
                .andExpect(model().attribute("foodCalories", is(NEW_FOOD_CALORIES)));
    }

    @Test
    public void shouldAddNewFood()
            throws Exception {
        List foods = (List)mockMvc.perform(get(FOODS_URL))
                .andExpect(status().isOk())
                .andReturn().getModelAndView().getModelMap().get("foods");

        mockMvc.perform(post(FOODS_UPDATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("foodId", Strings.EMPTY)
                .param("foodName", NEW_FOOD_NAME)
                .param("foodDate", NEW_FOOD_DATE_STRING)
                .param("foodCalories",  String.valueOf(NEW_FOOD_CALORIES))
        ).andExpect(redirectedUrl("/foods"));

        mockMvc.perform(get(FOODS_URL))
                .andExpect(status().isOk())
                .andExpect(model().attribute("foods", hasSize(foods.size() + 1)));
    }

    @Test
    public void shouldDeleteFood()
            throws Exception {
        List foods = (List) mockMvc.perform(get(FOODS_URL))
                .andExpect(status().isOk())
                .andReturn().getModelAndView().getModelMap().get("foods");

        mockMvc.perform(get(DELETE_FOODS_URL))
                .andExpect(redirectedUrl("/foods"));

        mockMvc.perform(get(FOODS_URL))
                .andExpect(status().isOk())
                .andExpect(model().attribute("foods", hasSize(foods.size() - 1)));
    }
}