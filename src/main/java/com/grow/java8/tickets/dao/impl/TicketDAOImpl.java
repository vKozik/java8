package com.grow.java8.tickets.dao.impl;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.grow.java8.tickets.dao.TicketDAO;
import com.grow.java8.tickets.data.Ticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("hibernate")
public class TicketDAOImpl implements TicketDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketDAOImpl.class);
   
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Ticket setTicket(final Ticket ticket) {
        return entityManager.merge(ticket);
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
    public Long getCuntSold(){
        return (Long) entityManager.createQuery("SELECT count(1) FROM Ticket t where not t.buyer is null").getSingleResult();
    }

}
