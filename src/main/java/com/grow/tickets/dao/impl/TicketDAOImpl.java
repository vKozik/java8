package com.grow.tickets.dao.impl;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.grow.tickets.dao.TicketDAO;
import com.grow.tickets.data.Ticket;

import org.springframework.stereotype.Repository;

@Repository
public class TicketDAOImpl implements TicketDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Ticket setTicket(final Ticket ticket) {
        Ticket newTicket = entityManager.merge(ticket);
        entityManager.flush();
        return newTicket;
    }

    @Override
    public Ticket getTicket(final String id) {
        Query query = entityManager.createNamedQuery("Ticket.find", Ticket.class);
        query.setParameter("id", id);
        return (Ticket) query.getSingleResult();
    }

    @Override
    public Optional<Ticket> getLastFree() {
        Query query = entityManager.createNamedQuery("Ticket.findFree", Ticket.class);
        List<Ticket> resultSet = query.getResultList();
        if (resultSet.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.ofNullable(resultSet.get(0));
    }

    @Override
    public Long getCountSold(){
        return entityManager.createNamedQuery("Ticket.findSold", Long.class).getSingleResult();
    }

    @Override
    public Long getCountFree(){
        return entityManager.createNamedQuery("Ticket.findAllFree", Long.class).getSingleResult();
    }

}
