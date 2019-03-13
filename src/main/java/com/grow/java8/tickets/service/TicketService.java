package com.grow.java8.tickets.service;

import com.grow.java8.tickets.data.Ticket;
import com.grow.java8.tickets.enums.TimePoint;

import java.util.Map;

public interface TicketService {
    Ticket sellTicket(String buyer);

    Map<TimePoint, Long> getCountSold();

    Ticket getTicket(String id);

    Ticket setBuyer(String id, String buyer);
}
