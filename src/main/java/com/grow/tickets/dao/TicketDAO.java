package com.grow.tickets.dao;

import java.util.Optional;

import com.grow.tickets.data.Ticket;

public interface TicketDAO {
    Ticket setTicket(Ticket ticket);

    Ticket getTicket(String id);

    Optional<Ticket> getLastFree();

    Long getCountSold();

    Long getCountFree();
}
