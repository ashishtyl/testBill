package com.is3102.controller;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.Appointment;
import com.is3102.EntityClass.Patient;
import com.is3102.entity.Employee;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.is3102.service.AdministrativeAdmissionRemote;
import org.primefaces.event.RowEditEvent;

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
    String NRIC_PIN5;
    String name;
    String name2;
    Date birthday;
    Date birthday2;
    String address;
    String addres2;
    String nationality;

    String contact;
    String contact2;
    String height;
    String weight;
    String weight2;

    String gender;
    String bloodgroup;
    Date appDate;
    int docID;
    String bedNo;
    String appID;
    long CIN;
    Patient patient;
    List<Appointment> appointments = new ArrayList<Appointment>();
    List<Bed> beds = new ArrayList<Bed>();

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    public long getCIN() {
        return CIN;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }
    
    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }
    
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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

    public String getNRIC_PIN5() {
        return NRIC_PIN5;
    }

    public void setNRIC_PIN5(String NRIC_PIN5) {
        this.NRIC_PIN5 = NRIC_PIN5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
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
    
    public String getWeight2() {
        return weight2;
    }
    
    public void setWeight2(String weight2) {
        this.weight2 = weight2;
    }

    public Date getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(Date birthday2) {
        this.birthday2 = birthday2;
    }

    public String getAddres2() {
        return addres2;
    }

    public void setAddres2(String addres2) {
        this.addres2 = addres2;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
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
            String pin = am.addPatient(NRIC_PIN1, name, format.format(birthday), address, contact, nationality, height, weight, gender, bloodgroup);
            context.addMessage(null, new FacesMessage("Patient Record " + name + " with PIN " + NRIC_PIN1 + " successfully created!"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record could not be Created!", null));
        }
    }

    public void doMakeAppointment(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String id = am.makeAppointment(NRIC_PIN2, format.format(appDate), docID);

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

    public void doUpdatePatient(ActionEvent actionEvent) {

        patient = am.getPatient(NRIC_PIN5);
    }

    public void showAppointments(ActionEvent event) {
        Patient patient = am.getPatient(NRIC_PIN4);
        if (patient != null) {
            appointments = am.getPatientAppointments(NRIC_PIN4);
        } else {
            appointments = null;
        }
    }

    public void onEdit(RowEditEvent event) {
        try {
            String newName = (String) ((Patient) event.getObject()).getName();
            String newAddress = (String) ((Patient) event.getObject()).getAddress();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String newBirthday = format.format(((Patient) event.getObject()).getBirthday());
            String newNumber = (String) ((Patient) event.getObject()).getcNumber();
            String newWeight= (String) ((Patient) event.getObject()).getWeight();
            am.update(patient.getNRIC_PIN(), newName, newAddress, newBirthday, newNumber, newWeight);
            FacesMessage msg = new FacesMessage("Patient Record Edited", ((Patient) event.getObject()).getNRIC_PIN());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record could not be Edited!", null));
        }
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Patient Record Not Edited", ((Patient) event.getObject()).getNRIC_PIN());
        FacesContext.getCurrentInstance().addMessage(null, msg);
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