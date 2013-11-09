/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Ben
 */
@Entity(name = "Patient")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private Long id;
    @Id
    private String NRIC_PIN;
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
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "patient")
    private Collection<Appointment> appointment = new ArrayList<Appointment>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "patient")
    private Collection<mCase> mcase = new ArrayList<mCase>();

    public Patient() {
    }

    public void create(String NRIC_PIN, String name, Date birthday, String address, String cNumber, String nationality, String height, String weight, String gender, String bloodGroup) {
        this.setNRIC_PIN(NRIC_PIN);
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

    /* public Long getId() {
     return id;
     }

     public void setId(Long id) {
     this.id = id;
     } */
    public String getNRIC_PIN() {
        return NRIC_PIN;
    }

    public void setNRIC_PIN(String NRIC_PIN) {
        this.NRIC_PIN = NRIC_PIN;
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

    public Collection<Appointment> getAppointments() {
        return appointment;
    }

    public void setAppointments(Collection<Appointment> appointment) {
        this.appointment = appointment;
    }

    public Collection<mCase> getmCases() {
        return mcase;
    }

    public void setmCases(Collection<mCase> mcase) {
        this.mcase = mcase;
    }
}
