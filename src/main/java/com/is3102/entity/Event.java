package com.is3102.entity;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;
import javax.persistence.*;

@Entity
@Table(name="event")
@NamedQueries({
        @NamedQuery(name = Event.ALL, query = "SELECT u FROM Event u "),
        @NamedQuery(name = Event.TOTAL, query = "SELECT COUNT(u) FROM Event u"),
        @NamedQuery(name = Event.USERID, query = "SELECT u FROM Event u WHERE u.employee.id = :userid")})

public class Event extends BaseEntity implements Serializable{

    public final static String ALL = "Event.populateEvents";
    public final static String TOTAL = "Event.countEventsTotal";
    public final static String USERID = "Event.getUsingUserId";

    public String event;

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date date;

    @ManyToOne
    @JoinColumn(name = "employeeid")
    private Employee employee;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}