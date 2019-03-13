package com.grow.java8.tickets.web;

import com.grow.java8.tickets.data.Ticket;
import com.grow.java8.tickets.enums.TimePoint;
import com.grow.java8.tickets.exception.GeneratedException;
import com.grow.java8.tickets.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public abstract class TicketAPIController {
    @GetMapping(value = "/buyTicket")
    public ResponseEntity<Ticket> buyTicket(@RequestParam(value = "buyer") String buyer) {
        try {
            Ticket ticket = getTicketService().sellTicket(buyer);
            return new ResponseEntity(ticket, HttpStatus.OK);
        } catch (GeneratedException ex) {
            return new ResponseEntity("Calld user for exception", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("Error buying ticket " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/sold")
    public ResponseEntity<Map<TimePoint, Long>> buyTicket() {
        return new ResponseEntity(getTicketService().getCountSold(), HttpStatus.OK);
    }

    @GetMapping(value = "/getTicket")
    public ResponseEntity<Ticket> getTicket(@RequestParam(value = "id") String id) {
        return new ResponseEntity(getTicketService().getTicket(id), HttpStatus.OK);
    }

    @GetMapping(value = "/setBuyer")
    public ResponseEntity<Ticket> setBuyer(@RequestParam(value = "id") String id, @RequestParam(value = "buyer") String buyer) {
        return new ResponseEntity(getTicketService().setBuyer(id, buyer), HttpStatus.OK);
    }

    abstract TicketService getTicketService();
}
