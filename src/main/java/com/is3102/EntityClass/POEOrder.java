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
    private Employee employee;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Medication medication;
    
    @OneToOne(cascade = {CascadeType.PERSIST})
    private MedicalProcedure mProcedure;
    
    @OneToOne(cascade = {CascadeType.PERSIST})
    private AppointmentProcedure aProcedure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployeeDoctor() {
        return employee;
    }

    public void setEmployeeDoctor(Employee employeeDoctor) {
        this.employee = employeeDoctor;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public MedicalProcedure getmProcedure() {
        return mProcedure;
    }

    public void setmProcedure(MedicalProcedure mProcedure) {
        this.mProcedure = mProcedure;
    }

    public AppointmentProcedure getaProcedure() {
        return aProcedure;
    }

    public void setaProcedure(AppointmentProcedure aProcedure) {
        this.aProcedure = aProcedure;
    }
}