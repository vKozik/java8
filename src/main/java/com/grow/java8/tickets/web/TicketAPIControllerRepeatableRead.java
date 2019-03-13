package com.grow.java8.tickets.web;

import com.grow.java8.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tickets/repeatableread")
public class TicketAPIControllerRepeatableRead extends TicketAPIController {
    @Autowired
    @Qualifier("ticketServiceRepeatableRead")
    private TicketService ticketService;

    @Override
    TicketService getTicketService() {
        return ticketService;
    }
}
