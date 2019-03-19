package com.grow.tickets.service;

import com.grow.tickets.data.Ticket;
import com.grow.tickets.enums.TimePoint;

import java.util.Map;

public interface TicketService {
    Ticket sellTicket(String buyer);

    Map<TimePoint, Long> getCountSold();

    Map<TimePoint, Long> getCountFree();

    Ticket addFreeTicket();
}
