package com.grow.java8.tickets.service;

import com.grow.java8.tickets.data.Ticket;

public interface TicketService {
    Ticket sellTicket(String buyer);

    Long getCountSold();
}
