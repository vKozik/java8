package com.grow.java8.tickets.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "ticket")
@NamedQuery(name="Ticket.findFree", query="SELECT t FROM Ticket t where t.buyer is null", lockMode = LockModeType.WRITE)
@NamedQuery(name="Ticket.find", query="SELECT t FROM Ticket t where t.id = :id")
@NamedQuery(name="Ticket.findSold", query="SELECT count (t) FROM Ticket t where not t.buyer is null")
public class Ticket{
    private String id;
    private String buyer;
    private Long version;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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

    @Version
    @Column(name="version")
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
