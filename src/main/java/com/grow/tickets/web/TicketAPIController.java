package com.grow.tickets.web;

import com.grow.tickets.data.Ticket;
import com.grow.tickets.enums.TimePoint;
import com.grow.tickets.exception.GeneratedException;
import com.grow.tickets.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public abstract class TicketAPIController {
    @GetMapping(value = "/buyTicket")
    public ResponseEntity<Ticket> soldTickets(@RequestParam(value = "buyer") String buyer) {
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
    public ResponseEntity<Map<TimePoint, Long>> soldTickets() {
        return new ResponseEntity(getTicketService().getCountSold(), HttpStatus.OK);
    }

    @GetMapping(value = "/free")
    public ResponseEntity<Map<TimePoint, Long>> freeTickets() {
        return new ResponseEntity(getTicketService().getCountFree(), HttpStatus.OK);
    }

    @GetMapping(value = "/addFree")
    public ResponseEntity<Ticket> setBuyer() {
        return new ResponseEntity(getTicketService().addFreeTicket(), HttpStatus.OK);
    }

    abstract TicketService getTicketService();
}
