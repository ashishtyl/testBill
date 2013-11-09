package com.is3102.controller;

import com.is3102.EntityClass.Appointment;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.mCase;
import com.is3102.service.PatientIdandCheckingRemote;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Swarit
 */
@ManagedBean
@ViewScoped
public class PatientIdandCheckingManaged implements Serializable {

    @EJB
    public PatientIdandCheckingRemote pm;
    String name1;
    String name2;
    String NRIC_PIN1;
    String NRIC_PIN2;
    Date appDate;
    Patient patient;
    List<Appointment> appointments = new ArrayList<Appointment>();
    List<mCase> cases = new ArrayList<mCase>();

    public Patient getPatient() {
        return patient;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<mCase> getCases() {
        return cases;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getNRIC_PIN1() {
        return NRIC_PIN1;
    }

    public void setNRIC_PIN1(String NRIC_PIN1) {
        this.NRIC_PIN1 = NRIC_PIN1;
    }
    
    public String getNRIC_PIN2() {
        return NRIC_PIN2;
    }

    public void setNRIC_PIN2(String NRIC_PIN2) {
        this.NRIC_PIN2 = NRIC_PIN2;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public void DoCheckRecurrence(ActionEvent actionEvent) {
        if (pm.checkRecurrence(name1, NRIC_PIN1)) {
            patient = pm.getPatientInfo(name1, NRIC_PIN1);
        } else {
            patient = null;
        }
    }

    public void DoCheckAppointment(ActionEvent actionEvent) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Patient patient = pm.getPatientInfo(name2, NRIC_PIN2);
        if (patient != null) {
            if (pm.checkAppointment(name2, NRIC_PIN2, appDate)) {
                appointments = pm.getPatientAppointments(name2, NRIC_PIN2, format.format(appDate));
            } else {
                appointments = null;
            }
        } else {
            appointments = null;
        }
    }
}