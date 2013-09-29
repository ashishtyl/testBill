package com.is3102.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="message")
@NamedQueries({
        @NamedQuery(name = Message.ALL, query = "SELECT u FROM Message u "),
        @NamedQuery(name = Message.TOTAL, query = "SELECT COUNT(u) FROM Message u"),
        @NamedQuery(name = Message.SENDERID, query = "SELECT u FROM Message u WHERE u.sender.id = :senderid"),
        @NamedQuery(name = Message.RECEIVERID, query = "SELECT u FROM Message u WHERE u.receiver.id = :receiverid")})

public class Message extends BaseEntity implements Serializable{

    public final static String ALL = "Message.populateMessages";
    public final static String TOTAL = "Message.countMessagesTotal";
    public final static String SENDERID = "Message.getUsingSenderId";
    public final static String RECEIVERID = "Message.getUsingReceiverId";

    @ManyToOne
    @JoinColumn(name = "senderid")
    private Employee sender;

    @ManyToOne
    @JoinColumn(name = "receiverid")
    private Employee receiver;

    private String subject;

    private String message;

    private String date;

    public Employee getSender() {
        return sender;
    }

    public void setSender(Employee sender) {
        this.sender = sender;
    }

    public Employee getReceiver() {
        return receiver;
    }

    public void setReceiver(Employee receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
