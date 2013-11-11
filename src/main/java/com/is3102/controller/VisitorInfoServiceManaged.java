package com.is3102.controller;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.Patient;
import com.is3102.Exception.ExistException;
import com.is3102.service.VisitorInfoServiceRemote;
import com.is3102.util.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;

/**
 *
 * @author Swarit
 */
@ManagedBean
@ViewScoped
//@RequestScoped
public class VisitorInfoServiceManaged implements Serializable {

    @EJB
    public VisitorInfoServiceRemote vm;
    //public AdministrativeAdmissionManaged adminadm;
    String name;
    String NRIC_PIN;
    String NRIC_PIN1;
    Date dateAdmitted;
    int countToday;
    int countMonth;
    int countAvg;
    Bed bed;
    Patient patient1; 
    Logger logger = new Logger();

    public Bed getBed() {
        return bed;
    }

    public int getCountToday() {
        return countToday;
    }

    public int getCountMonth() {
        return countMonth;
    }

    public int getCountAvg() {
        return countAvg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNRIC_PIN() {
        return NRIC_PIN;
    }

    public void setNRIC_PIN(String NRIC_PIN) {
        this.NRIC_PIN = NRIC_PIN;
    }

    public String getNRIC_PIN1() {
        return NRIC_PIN1;
    }

    public void setNRIC_PIN1(String NRIC_PIN1) {
        this.NRIC_PIN1 = NRIC_PIN1;
    }

    public Date getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(Date dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public Patient getPatient1() {
        return patient1;
    }

    public void doretrievePatientInfo(ActionEvent actionEvent) throws ExistException {
        logger.log("In doretrieve Patient Info");
        FacesContext context = FacesContext.getCurrentInstance();
        //try {
        Patient patient = vm.getPatient(name, NRIC_PIN);
        if (patient != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            bed = vm.retrievePatientInfo(patient.getPatientId(), format.format(dateAdmitted));
        } else {
            bed = null;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record could not be found!", null));
        }
    }
    
    public void doretrievePInfo(ActionEvent actionEvent) throws ExistException {
        logger.log("In doretrieve Patient Info");
        FacesContext context = FacesContext.getCurrentInstance();
        //try {
        patient1 = vm.getPatient(name, NRIC_PIN1);
        if (patient1 != null) {
            //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //bed = vm.retrievePatientInfo(patient.getPatientId(), format.format(dateAdmitted));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record could not be found!", null));
        }
    }

    /* Count the number of patients admitted today */
    public void dogetTodaysAdmissions(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        countToday = vm.getTodaysAdmissions();
        context.addMessage(null, new FacesMessage("The total is: " + countToday));
    }

    /* Count the number of patients admitted this month */
    public void dogetCurrentPatients() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            countMonth = vm.getCurrentPatients();
            context.addMessage(null, new FacesMessage("The total is: " + countMonth));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
        }
    }

    /* Count the average duration of stay */
    public void dogetStayDuration() {
        FacesContext context = FacesContext.getCurrentInstance();
        countAvg = vm.getStayDuration();
        context.addMessage(null, new FacesMessage("The total is: " + countAvg));
    }
}