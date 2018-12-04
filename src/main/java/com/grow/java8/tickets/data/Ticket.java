package com.grow.java8.tickets.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
@NamedQuery(name="Ticket.findFree", query="SELECT t FROM Ticket t where t.buyer is null and rownum = 1",
        lockMode = LockModeType.PESSIMISTIC_WRITE)
public class Ticket{
    private String id;
    private String buyer;
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public String getId() {
        return id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    @Column(name="buyer")
    public String getBuyer() {
        return buyer;
    }
    
    public void setBuyer(final String buyer) {
        this.buyer = buyer;
    }
}
