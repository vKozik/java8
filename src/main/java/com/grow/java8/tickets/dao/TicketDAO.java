package com.grow.java8.tickets.dao;

import java.util.Optional;

import com.grow.java8.tickets.data.Ticket;

public interface TicketDAO {
    Ticket setTicket(Ticket ticket);

    Ticket getTicket(String id);

    Optional<Ticket> getLastFree();

    Long getCountSold();
}
