package com.grow.java8.tickets.dao;

import java.util.Optional;

import com.grow.java8.tickets.data.Ticket;

public interface TicketDAO {
    Ticket setTicket(Ticket ticket);
    
    Optional<Ticket> getLastFree();

    Long getCuntSold();
}
