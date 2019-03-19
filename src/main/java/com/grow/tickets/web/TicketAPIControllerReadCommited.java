package com.grow.tickets.web;

import com.grow.tickets.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tickets/commited")
public class TicketAPIControllerReadCommited extends TicketAPIController {
    @Autowired
    @Qualifier("ticketServiceReadCommited")
    private TicketService ticketService;

    @Override
    TicketService getTicketService() {
        return ticketService;
    }
}
