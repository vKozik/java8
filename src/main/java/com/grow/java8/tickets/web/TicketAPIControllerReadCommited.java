package com.grow.java8.tickets.web;

import org.springframework.beans.factory.annotation.Qualifier;
import sun.security.krb5.internal.Ticket;

import com.grow.java8.tickets.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tickets/commited")
public class TicketAPIControllerReadCommited {
    @Autowired
    @Qualifier("ticketServiceReadCommited")
    private TicketService ticketService;

    
    @GetMapping(value = "/info")
    public ResponseEntity apiTest() {
        return new ResponseEntity("API for testing", HttpStatus.OK);
    }

    @GetMapping(value = "/buyTicket")
    public ResponseEntity<Ticket> buyTicket(@RequestParam(value = "buyer") String buyer) {
        return new ResponseEntity(ticketService.sellTicket(buyer), HttpStatus.OK);
    }

    @GetMapping(value = "/sold")
    public ResponseEntity<List<Long>> buyTicket() {
        return new ResponseEntity(ticketService.getCountSold(), HttpStatus.OK);
    }
}