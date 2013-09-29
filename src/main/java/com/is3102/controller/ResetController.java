package com.is3102.controller;

import com.is3102.service.EmployeeService;
import com.is3102.util.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Named
@RequestScoped
public class ResetController implements Serializable{

    private @Inject
    EmployeeService das;

    private boolean valid;

    private String email;

    private String password;

    private String id;

    private String reset;

    private Logger logger = new Logger();

    @PostConstruct
    public void init(){
        System.out.println("postConstruct " +  (das == null ? "null" : "notnull"));
        setValid(das.reset());
        this.resetPassword();
    }

    public ResetController(){
        System.out.println("reset controller");
    }

    public void resetEmail(){
        boolean result = das.resetEmail(this.email);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String navigateString = "/index.xhtml";
        if (result){
            try {
                context.getExternalContext().redirect(request.getContextPath() + navigateString);
            } catch (IOException ex) {
                context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
            }
        }
    }

    public void resetPassword(){
        logger.log("in resetPassword");
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = (Map<String, String>) context.getExternalContext().getRequestParameterMap();
        logger.log(params.get("form:password"));

        String password = params.get("form:password");
        String id = params.get("form:id");

        if (password == null) return;
        if (id == null) return;

        boolean canReset = das.resetPassword(params);
        logger.log("Can reset?" + canReset );
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String navigateString = "/index.xhtml";
        if (canReset){
            try {
                context.addMessage(null,  new FacesMessage("Successful reset!", "Successful reset"));
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.getExternalContext().redirect(request.getContextPath() + navigateString);
                System.out.println(request.getContextPath()+ navigateString);
            } catch (IOException ex) {
                context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
            }
        }
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    public boolean isValid() {
        return valid;
    }

    public EmployeeService getDas() {
        return das;
    }

    public void setDas(EmployeeService das) {
        this.das = das;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }


}
