package com.is3102.service;

import com.is3102.entity.Employee;
import com.is3102.entity.EmployeeGroup;
import com.is3102.entity.Event;
import com.is3102.util.DateUtility;
import com.is3102.util.JavaMail;
import com.is3102.util.SHA;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

@Stateless
public class EventService extends DataAccessService<Event> implements Serializable{

    @PersistenceContext
    private EntityManager em;

    public EventService(){
        super(Event.class);
    }

    @Override
    public Event create(Event t) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String username = request.getUserPrincipal().toString();
        if (username == null) return null;
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);

        List<Employee> users = this.findWithNamedQuery(Employee.USERNAME, params);
        Employee user = users.get(0);
        t.setEmployee(user);

        t.setDate(DateUtility.getStringFromDate(t.getDate()));

        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    @Override
    public Event update(Event t) {

        t.setDate(DateUtility.getStringFromDate(t.getDate()));
        System.out.println("update information");
        System.out.println(t.getId());

        return (Event) this.em.merge(t);
    }

    public boolean shareEvents(Event[] items) {
        for (Event item : items) {
            FacesContext context = FacesContext.getCurrentInstance();
            Map<String, String> params = (Map<String, String>) context.getExternalContext().getRequestParameterMap();
            String username = params.get("shareEventForm:sharename_input");
            this.createWithUser(item, username);
        }
        return true;
    }

    public Event createWithUser(Event t, String username) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);

        List<Employee> users = this.findWithNamedQuery(Employee.USERNAME, params);
        Employee user = users.get(0);
        t.setEmployee(user);

        t.setDate(DateUtility.getStringFromDate(t.getDate()));

        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }
}
