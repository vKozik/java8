package com.grow.java8.tickets.web;

import com.grow.java8.tickets.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tickets/serializable")
public class TicketAPIControllerSerializable extends TicketAPIController {
    @Autowired
    @Qualifier("ticketServiceSerializable")
    private TicketService ticketService;

    @Override
    TicketService getTicketService() {
        return ticketService;
    }
}
