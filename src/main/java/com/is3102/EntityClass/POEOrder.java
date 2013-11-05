/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import com.is3102.entity.Employee;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Ben
 */
@Entity
public class POEOrder implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @ManyToOne
    private Employee employeeDoctor;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Medication medication;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Medical_Procedure mProcedure;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Appointment_Procedure aProcedure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployeeDoctor() {
        return employeeDoctor;
    }

    public void setEmployeeDoctor(Employee employeeDoctor) {
        this.employeeDoctor = employeeDoctor;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Medical_Procedure getmProcedure() {
        return mProcedure;
    }

    public void setmProcedure(Medical_Procedure mProcedure) {
        this.mProcedure = mProcedure;
    }

    public Appointment_Procedure getaProcedure() {
        return aProcedure;
    }

    public void setaProcedure(Appointment_Procedure aProcedure) {
        this.aProcedure = aProcedure;
    }
}