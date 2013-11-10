/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import com.is3102.entity.Employee;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Swarit
 */
@Entity
public class POEOrder implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Temporal(value = TemporalType.DATE)
    private Date dateOrdered;
    @ManyToOne
    private Employee employee;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private mCase mcase;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Medication medication;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private LabRadProcedure labRadProcedure;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private AppointmentProcedure aProcedure;

    public POEOrder() {
    }

    public void create(Date setDateOrdered) {
        this.setDateOrdered(dateOrdered);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employeeDoctor) {
        this.employee = employeeDoctor;
    }

    public mCase getMcase() {
        return mcase;
    }

    public void setMcase(mCase mcase) {
        this.mcase = mcase;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public LabRadProcedure getLabRadProcedure() {
        return labRadProcedure;
    }

    public void setLabRadProcedure(LabRadProcedure labRadProcedure) {
        this.labRadProcedure = labRadProcedure;
    }
    
    public AppointmentProcedure getaProcedure() {
        return aProcedure;
    }

    public void setaProcedure(AppointmentProcedure aProcedure) {
        this.aProcedure = aProcedure;
    }
}