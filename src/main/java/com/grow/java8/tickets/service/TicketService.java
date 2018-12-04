package com.grow.java8.tickets.service;

import com.grow.java8.tickets.data.Ticket;

import java.util.List;

public interface TicketService {
    Ticket sellTicket(String Buyer);

    List<Long> getCountSold();
}
