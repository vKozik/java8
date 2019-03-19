package com.grow.tickets.web;

import com.grow.tickets.TicketsMain;
import com.grow.tickets.enums.TimePoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes =  TicketsMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TicketAPIControllerRepeatableReadTest extends TicketAPIControllerTest{
    private static final String ISOLATION_LEVEL = "repeatableread";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldNotDirtRead() throws Exception {
        Map<TimePoint, Long> result = tryDirtyRead(ISOLATION_LEVEL);

        assertThat(result.get(TimePoint.START), is(result.get(TimePoint.FINISH)));
    }


    @Test
    public void shouldNonRepeatebleRead() throws Exception {
        Map<TimePoint, Long> result = tryNonRepeatebleRead(ISOLATION_LEVEL);

        assertThat(result.get(TimePoint.START), is(result.get(TimePoint.FINISH)));
    }

    @Test
    public void shouldPhantomRead() throws Exception {
        Map<TimePoint, Long> result = tryPhantomRead(ISOLATION_LEVEL);

        assertThat(result.get(TimePoint.FINISH), is(result.get(TimePoint.START)));
    }

    @Override
    protected MockMvc getMockMvc() {
        return mockMvc;
    }
}