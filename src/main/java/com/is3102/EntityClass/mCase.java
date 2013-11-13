/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ashish
 */
@Entity(name = "mcase")
public class mCase implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long CIN;
    @Temporal(value = TemporalType.DATE)
    private Date dateAdmitted;
    @Temporal(value = TemporalType.DATE)
    private Date dateDischarged;
    private String type;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Medication> medication = new ArrayList<Medication>();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<LabRadProcedure> labRadProcedure = new ArrayList<LabRadProcedure>();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<POEOrder> orders = new ArrayList<POEOrder>();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Medical_Procedure> mProcedures = new ArrayList<Medical_Procedure>();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Nursing_Procedure> nProcedures = new ArrayList<Nursing_Procedure>();
    @OneToOne(cascade = {CascadeType.ALL})
    private Transfer transfer;
    @OneToOne(mappedBy = "mcase")
    private Appointment appointment;
    @OneToOne(mappedBy = "mcase")
    private OutpatientAppointment appointment2;
    @OneToOne(cascade = {CascadeType.ALL})
    private DischargeSummary dischargeSummary;   
    @OneToOne(cascade = {CascadeType.ALL})
    private Medical_Anamnesis medicalAnamnesis;
    @OneToOne(cascade = {CascadeType.ALL})
    private Nursing_Anamnesis nursingAnamnesis;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Bed bed;
   @ManyToOne
    private Patient patient;
    @OneToMany(cascade = {CascadeType.PERSIST})
    private List<Diagnosis> diagnosis = new ArrayList<Diagnosis>();
    @OneToOne(cascade = {CascadeType.ALL})
    private NursingDischarge nursingDischarge;

    

    public mCase() {
    }

    public void create(Date dateAdmitted, String type) {
        this.setDateAdmitted(dateAdmitted);
        this.setType(type);
    }

    public Long getCIN() {
        return CIN;
    }

    public void setCIN(Long CIN) {
        this.CIN = CIN;
    }

    public Date getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(Date dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public Date getDateDischarged() {
        return dateDischarged;
    }

    public void setDateDischarged(Date dateDischarged) {
        this.dateDischarged = dateDischarged;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Medical_Procedure> getmProcedures() {
        return mProcedures;
    }

    public void setmProcedures(List<Medical_Procedure> mProcedures) {
        this.mProcedures = mProcedures;
    }

    public List<LabRadProcedure> getLabRadProcedure() {
        return labRadProcedure;
    }

    public void setLabRadProcedure(List<LabRadProcedure> labRadProcedure) {
        this.labRadProcedure = labRadProcedure;
    }

    public List<Medication> getMedication() {
        return medication;
    }

    public void setMedication(List<Medication> medication) {
        this.medication = medication;
    }

    public List<POEOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<POEOrder> orders) {
        this.orders = orders;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Nursing_Procedure> getnProcedures() {
        return nProcedures;
    }

    public void setnProcedures(List<Nursing_Procedure> nProcedures) {
        this.nProcedures = nProcedures;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public void addmedicalProcedure(Medical_Procedure procedure) {
        this.mProcedures.add(procedure);

    }

    public DischargeSummary getDischargeSummary() {
        return dischargeSummary;
    }

    public void setDischargeSummary(DischargeSummary dischargeSummary) {
        this.dischargeSummary = dischargeSummary;
    }

    public Medical_Anamnesis getMedicalAnamnesis() {
        return medicalAnamnesis;
    }

    public void setMedicalAnamnesis(Medical_Anamnesis medicalAnamnesis) {
        this.medicalAnamnesis = medicalAnamnesis;
    }

    public Nursing_Anamnesis getNursingAnamnesis() {
        return nursingAnamnesis;
    }

    public void setNursingAnamnesis(Nursing_Anamnesis nursingAnamnesis) {
        this.nursingAnamnesis = nursingAnamnesis;
    }

    public List<Diagnosis> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(List<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }
     public OutpatientAppointment getAppointment2() {
        return appointment2;
    }

    public void setAppointment2(OutpatientAppointment appointment2) {
        this.appointment2 = appointment2;
    }
     public NursingDischarge getNursingDischarge() {
        return nursingDischarge;
    }

    public void setNursingDischarge(NursingDischarge nursingDischarge) {
        this.nursingDischarge = nursingDischarge;
    }
    
     public void addnProcedure(Nursing_Procedure nprocedure) {
        this.getnProcedures().add(nprocedure);
    }
      
    
}
