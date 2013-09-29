package com.is3102.service;

import com.is3102.entity.Employee;
import com.is3102.entity.Message;
import com.is3102.util.DateUtility;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class MessageService extends DataAccessService<Message> implements Serializable{

    @PersistenceContext
    private EntityManager em;

    public MessageService(){
        super(Message.class);
    }

    @Override
    public Message create(Message t) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String username = request.getUserPrincipal().toString();
        if (username == null) return null;
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);

        List<Employee> users = this.findWithNamedQuery(Employee.USERNAME, params);
        Employee user = users.get(0);
        t.setSender(user);

        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
        String receivername = parameters.get("newMessageForm:newreceivername");
        System.out.println(receivername);
        params = new HashMap<String, String>();
        params.put("username", receivername);

        List<Employee> receivers =this.findWithNamedQuery(Employee.USERNAME, params);
        Employee receiver = receivers.get(0);
        t.setReceiver(receiver);

        t.setDate(DateUtility.getCurrentDateTime());

        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }
}
