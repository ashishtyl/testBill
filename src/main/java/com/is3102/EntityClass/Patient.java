/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Ben
 */
@Entity(name = "Patient")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientId;
    private String passport_NRIC;
    private String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;
    private String address;
    private String cNumber;
    private String nationality;
    private String bloodGroup;
    private String height;
    private String weight;
    private String gender;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "patient")
    private List<Appointment> appointment = new ArrayList<Appointment>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "patient")
    private List<mCase> mcase = new ArrayList<mCase>();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Vitals> vitals = new ArrayList<Vitals>();

     @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "patient")
    private List<OutpatientAppointment> appointment2 = new ArrayList<OutpatientAppointment>();


    public Patient() {
    }

    public void create(String passport_NRIC, String name, Date birthday, String address, String cNumber, String nationality, String height, String weight, String gender, String bloodGroup) {
        this.setPassport_NRIC(passport_NRIC);
        this.setName(name);
        this.setBirthday(birthday);
        this.setAddress(address);
        this.setcNumber(cNumber);
        this.setNationality(nationality);
        this.setGender(gender);
        this.setBloodGroup(bloodGroup);
        this.setHeight(height);
        this.setWeight(weight);
    }

    public Long getPatientId() {
        return patientId;
    }

    public List<OutpatientAppointment> getAppointment2() {
        return appointment2;
    }

    public void setAppointment2(List<OutpatientAppointment> appointment2) {
        this.appointment2 = appointment2;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPassport_NRIC() {
        return passport_NRIC;
    }

    public void setPassport_NRIC(String passport_NRIC) {
        this.passport_NRIC = passport_NRIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getcNumber() {
        return cNumber;
    }

    public void setcNumber(String cNumber) {
        this.cNumber = cNumber;
    }

    public List<Appointment> getAppointments() {
        return appointment;
    }

    public void setAppointments(List<Appointment> appointment) {
        this.appointment = appointment;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<mCase> getmCases() {
        return mcase;
    }

    public void setmCases(List<mCase> mcase) {
        this.mcase = mcase;
    }

    public List<Vitals> getVitals() {
        return vitals;
    }

    public void setVitals(List<Vitals> vitals) {
        this.vitals = vitals;
    }
}
