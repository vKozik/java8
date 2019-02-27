package com.grow.java8.tickets.web;

import com.grow.java8.tickets.service.TicketService;
import com.grow.java8.tickets.data.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        try {
            Ticket ticket = ticketService.sellTicket(buyer);
            return new ResponseEntity(ticket, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Error buyer", HttpStatus.OK);
        }
    }

    @GetMapping(value = "/sold")
    public ResponseEntity<List<Long>> buyTicket() {
        return new ResponseEntity(ticketService.getCountSold(), HttpStatus.OK);
    }
}
