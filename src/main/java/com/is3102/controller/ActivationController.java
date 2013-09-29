package com.is3102.controller;

import com.is3102.entity.EmployeeGroup;
import com.is3102.service.EmployeeService;
import com.is3102.util.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: NguyenTranQuan
 * Date: 9/20/13
 * Time: 4:08 AM
 * This controller is used to activate the account
 */

@Named
@RequestScoped
public class ActivationController implements Serializable {
    private @Inject
    EmployeeService das;

    private boolean valid;

    private Logger logger = new Logger();

    @PostConstruct
    public void init(){
        logger.log("postConstruct " +  (das == null ? "null" : "notnull"));
        setValid(das.activate());
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String navigateString = "/index.xhtml";
        if (this.isValid()){
            try {
                context.addMessage(null,  new FacesMessage("Successful activated!", "Successful activated"));
                context.getExternalContext().getFlash().setKeepMessages(true);
                context.getExternalContext().redirect(request.getContextPath() + navigateString);
                logger.log("context path: " +  request.getContextPath()+ navigateString);
            } catch (IOException ex) {
                context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
            }
        }
    }

    public ActivationController(){
        logger.log("Activation Controller Initiation");;
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
}