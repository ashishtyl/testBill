/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Ben
 */
@Entity(name="outpatient_appointment")
public class OutpatientAppointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    
    
    @ManyToOne
    private Patient patient;

    @OneToOne(cascade = {CascadeType.PERSIST})
    private mCase mcase;

   // @ManyToOne
   // private Employee employee;


    public OutpatientAppointment(){}

    public void create(Date startDate, Date endDate){
            this.setStartDate(startDate);
            this.setEndDate(endDate);
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     *
     * @return
     */
    public mCase getmCase() {
        return mcase;
    }

    public void setmCase(mCase mcase) {
        this.mcase = mcase;
    }

   

    
}

