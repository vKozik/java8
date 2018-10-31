package com.grow.java8.calories.web;

import com.grow.java8.calories.CaloriesMain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes =  CaloriesMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestAPIControllerTest {

    @InjectMocks
    private RestAPIController restAPIController;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restAPIController).build();
    }

    @Test
    public void shouldReturnTestMessage()
            throws Exception {

        mockMvc.perform(get("/api/test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ok")));
    }

    @Disabled
    @Test
    public void shouldReturnStatistics()
            throws Exception {

        mockMvc.perform(get("/api/stat?fromDate=2018-08-01&toDate=2018-08-05")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ok")));
    }

}