package com.grow.tickets.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grow.tickets.enums.TimePoint;
import com.grow.tickets.util.Util;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

abstract class TicketAPIControllerTest {
    private static final String URL_BUY_TICKET = "/tickets/%s/buyTicket";
    private static final String URL_SOLD_TICKETS = "/tickets/%s/sold";
    private static final String URL_FREE_TICKETS = "/tickets/%s/free";
    private static final String URL_ADD_FREE_TICKET = "/tickets/%s/addFree";

    private static final String BUYER_FOR_ERROR = "exception";
    private static final String BUYER = "buyer";
    private static final String PARAM_FOR_BUY = "buyer";

    protected Map<TimePoint, Long> tryDirtyRead(String isolationLevel) throws Exception {
        Thread request = new Thread(() -> {
            try {
                getMockMvc().perform(get(getUrlBuyTicket(isolationLevel))
                        .param(PARAM_FOR_BUY, BUYER_FOR_ERROR))
                        .andExpect(status().isOk());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        request.start();

        Util.pause(2000);

        String soldResult = getMockMvc().perform(get(getUrlSoldTickets(isolationLevel)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        request.join();

        return new ObjectMapper().readValue(soldResult, new TypeReference<HashMap<TimePoint, Long>>(){});
    }

    protected Map<TimePoint, Long>  tryNonRepeatebleRead(String isolationLevel) throws Exception {
        String[] soldResult = new String[1];

        Thread request = new Thread(() -> {
            try {
                soldResult[0] = getMockMvc().perform(get(getUrlSoldTickets(isolationLevel)))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        request.start();

        Util.pause(2000);

        getMockMvc().perform(get(getUrlBuyTicket(isolationLevel))
                .param(PARAM_FOR_BUY, BUYER))
                .andExpect(status().isOk());

        request.join();

        return new ObjectMapper().readValue(soldResult[0], new TypeReference<HashMap<TimePoint, Long>>(){});
    }

    protected Map<TimePoint, Long> tryPhantomRead(String isolationLevel) throws Exception {
        String[] soldResult = new String[1];

        Thread request = new Thread(() -> {
            try {
                soldResult[0] = getMockMvc().perform(get(getUrlFreeTickets(isolationLevel)))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        request.start();

        Util.pause(2000);

        getMockMvc().perform(get(getUrlAddFreeTicket(isolationLevel)))
                .andExpect(status().isOk());

        request.join();
        return new ObjectMapper().readValue(soldResult[0], new TypeReference<HashMap<TimePoint, Long>>(){});
    }

    private String getUrlBuyTicket(String isolationLevel){
        return String.format(URL_BUY_TICKET, isolationLevel);
    }

    private String getUrlSoldTickets(String isolationLevel){
        return String.format(URL_SOLD_TICKETS, isolationLevel);
    }

    private String getUrlFreeTickets(String isolationLevel){
        return String.format(URL_FREE_TICKETS, isolationLevel);
    }

    private String getUrlAddFreeTicket(String isolationLevel){
        return String.format(URL_ADD_FREE_TICKET, isolationLevel);
    }

    protected abstract MockMvc getMockMvc();
}