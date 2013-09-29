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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Ben
 */
@Entity(name="Patient")
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String NRIC_PIN;
    private String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;
    private String address;
    private String cNumber;
    private String blood_group; // DO WE NEED THIS HERE????
    private String height; // DO WE NEED THIS HERE????
    private String weight; // DO WE NEED THIS HERE????
    
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="patient")
    private Collection<Appointment> appointment = new ArrayList<Appointment>();
    
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="patient")
    private Collection<mCase> mcase = new ArrayList<mCase>();
    
    public Patient(){}
    
    public void create(String NRIC_PIN, String name, Date birthday, String address, String cNumber) {
        this.setNRIC_PIN(NRIC_PIN);
        this.setName(name);
        this.setBirthday(birthday);
        this.setAddress(address);
        this.setcNumber(cNumber);
    }
    
   public String getNRIC_PIN() {
        return NRIC_PIN;
    }

    public void setNRIC_PIN(String NRIC_PIN) {
        this.NRIC_PIN = NRIC_PIN;
    }

  

    
    public String getName(){
       return name;
   }
   
   public void setName(String name){
       this.name=name;
   }
   
   public String getAddress(){
       return address;
   }
   public void setAddress(String address){
       this.address=address;
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
