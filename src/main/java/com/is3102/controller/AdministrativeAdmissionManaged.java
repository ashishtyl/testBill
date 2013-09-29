package com.is3102.controller;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.Appointment;
import com.is3102.EntityClass.Patient;
import com.is3102.entity.Employee;
import com.is3102.service.AdministrativeAdmissionRemote;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Swarit
 */
@ManagedBean
@ViewScoped
public class AdministrativeAdmissionManaged implements Serializable {

    @EJB
    public AdministrativeAdmissionRemote am; //adminadmManager;
    //Input i = new Input();
    String NRIC_PIN1;
    String NRIC_PIN2;
    String NRIC_PIN3;
    String NRIC_PIN4;
    String name;
    Date birthday;
    String address;
    String contact;
    Date appDate;
    String place;
    int docID;
    String bedNo;
    String appID;
    long CIN;
    List<Appointment> appointments = new ArrayList<Appointment>();
    List<Bed> beds = new ArrayList<Bed>();

    public long getCIN() {
        return CIN;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Bed> getBeds() {
        return beds;
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

    public String getNRIC_PIN3() {
        return NRIC_PIN3;
    }

    public void setNRIC_PIN3(String NRIC_PIN3) {
        this.NRIC_PIN3 = NRIC_PIN3;
    }

    public String getNRIC_PIN4() {
        return NRIC_PIN4;
    }

    public void setNRIC_PIN4(String NRIC_PIN4) {
        this.NRIC_PIN4 = NRIC_PIN4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getDocID() {
        return docID;
    }

    public void setDocID(int docID) {
        this.docID = docID;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public AdministrativeAdmissionManaged() {
    }

    public void doAddPatient(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String pin = am.addPatient(NRIC_PIN1, name, format.format(birthday), address, contact);
            context.addMessage(null, new FacesMessage("Patient Record " + name + " with PIN " + NRIC_PIN1 + " successfully created!"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record could not be  created!", null));
        }
    }

    public void doMakeAppointment(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String id = am.makeAppointment(NRIC_PIN2, format.format(appDate), place, docID);

            Employee employee = am.getEmployee(docID);
            context.addMessage(null, new FacesMessage("Appointment " + id + " for " + NRIC_PIN2 + " on date " + format.format(appDate) + " by doctor " + employee.getUsername() + " successfully made!"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Appointment could not be made!", null));
        }
    }

    public void doCreateCase(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            //appointments = am.getPatientAppointments(NRIC_PIN3);
            beds = am.getAvailBeds();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCreated = new Date();
            CIN = am.createCase(bedNo, appID);
            context.addMessage(null, new FacesMessage("Case " + CIN + " for " + NRIC_PIN3 + " on date " + format.format(dateCreated) + " successfully created!"));
        } catch (Exception ex) {
            ex.printStackTrace();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Case could not be created!", null));
        }
    }

    public void doListAvailBeds(ActionEvent actionEvent) {
        beds = am.getAvailBeds();
    }

    public void showAppointments(ActionEvent event) {
        Patient patient = am.getPatient(NRIC_PIN4);
        if (patient != null) {
            appointments = am.getPatientAppointments(NRIC_PIN4);
        } else {
            appointments = null;
        }
    }

    /* public void doListPatientCases() {
     System.out.println("\n\n\t=== CASE DETAILS ===");
     List<mCase> mcases = am.getPatientCases(getNRIC_PIN());
     if (mcases.isEmpty()) {
     System.out.println("Patient has no Case.\n");
     return;
     }

     for (mCase mcase : mcases) {
     System.out.println("\nCIN: " + mcase.getCIN() + "\nDate of Creation: " + mcase.getDateAdmitted() + "\nDate of Appointment: " + mcase.getAppointment());
     }
     } */

    /* public void doListCases() {
     System.out.println("\n\n\t=== CASE DETAILS ===");
     List<mCase> mcases = am.getmCases();
     if (mcases.isEmpty()) {
     System.out.println("No Case to Display.\n");
     return;
     }

     for (mCase mcase : mcases) {
     System.out.println("\nPatient's Name: " + mcase.getPatient().getName() + "\nDate of Creation: " + mcase.getDateAdmitted() + "\nDate of Appointment: " + mcase.getAppointment());
     }
     } */
}