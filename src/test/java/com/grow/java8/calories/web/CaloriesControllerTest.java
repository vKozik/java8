package com.grow.java8.calories.web;

import com.grow.java8.calories.CaloriesMain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes =  CaloriesMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CaloriesControllerTest {
    private static final String STAT_URL = "/stat?fromDate=%s&toDate=%s&norma=%s";
    private static final String FOODS_URL = "/foods";
    private static final Double NORMA_CALORIES = 1500d;
    private static final String VIEW_STAT = "stat";
    private static final String VIEW_FOODS = "foods";
    private static final String VIEW_EXCEPTION = "exception";

    private static final LocalDate FROM_DATE = LocalDate.of(2018, 8, 1);
    private static final LocalDate TO_DATE = LocalDate.of(2018, 8, 3);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnStatistics()
            throws Exception {
        mockMvc.perform(get(String.format(STAT_URL, FROM_DATE.format(ISO_LOCAL_DATE), TO_DATE.format(ISO_LOCAL_DATE),
                NORMA_CALORIES)))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_STAT))
                .andExpect(model().attribute("norma", is(NORMA_CALORIES)))
                .andExpect(model().attribute("fromDate", is(FROM_DATE)))
                .andExpect(model().attribute("toDate", is(TO_DATE)))
                .andExpect(model().attribute("isReadOnly", is(false)))
                .andExpect(model().attribute("foodStats", hasSize(9)))
                .andExpect(model().attribute("foodStats", hasItem(
                        allOf(
                                hasProperty("id", is(10001L)),
                                hasProperty("name", is("FOOD-1")),
                                hasProperty("calories", is(1500.0)),
                                hasProperty("dateOfEating", is(LocalDateTime.parse("2018-08-01T10:10:30"))),
                                hasProperty("caloriesPerDay", is(2200.0))
                        ))))
                .andExpect(model().attribute("foodStats", hasItem(
                        allOf(
                                hasProperty("id", is(10004L)),
                                hasProperty("name", is("FOOD-4")),
                                hasProperty("calories", is(550.0)),
                                hasProperty("dateOfEating", is(LocalDateTime.parse("2018-08-02T10:11:11"))),
                                hasProperty("caloriesPerDay", is(1270.0))
                        ))))
                .andExpect(model().attribute("foodStats", hasItem(
                        allOf(
                                hasProperty("id", is(10008L)),
                                hasProperty("name", is("FOOD-8")),
                                hasProperty("calories", is(678.0)),
                                hasProperty("dateOfEating", is(LocalDateTime.parse("2018-08-03T10:12:43"))),
                                hasProperty("caloriesPerDay", is(928.0))
                        )
                )));
    }

    @Test
    public void shouldReturnAllFoods()
            throws Exception {
        mockMvc.perform(get(FOODS_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_FOODS))
                .andExpect(model().attribute("isReadOnly", is(false)))
                .andExpect(model().attribute("foods", hasSize(28)))
                .andExpect(model().attribute("foods", hasItem(
                        allOf(
                                hasProperty("id", is(10001L)),
                                hasProperty("name", is("food-1")),
                                hasProperty("calories", is(1500.0)),
                                hasProperty("dateOfEating", is(LocalDateTime.parse("2018-08-01T10:10:30")))
                        ))))
                .andExpect(model().attribute("foods", hasItem(
                        allOf(
                                hasProperty("id", is(10028L)),
                                hasProperty("name", is("food-14")),
                                hasProperty("calories", is(550.0)),
                                hasProperty("dateOfEating", is(LocalDateTime.parse("2018-08-12T10:20:39")))
                        ))));
    }

    @Test
    public void shouldReturnErrorPage()
            throws Exception {
        mockMvc.perform(get(String.format(STAT_URL, FROM_DATE.format(ISO_LOCAL_DATE), TO_DATE.format(ISO_LOCAL_DATE),
                "test")))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_EXCEPTION));
    }
}