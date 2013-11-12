/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import com.is3102.EntityClass.ExecutionLog;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.Vitals;
import com.is3102.Exception.ExistException;
import com.is3102.service.ExecutionRemote;
import com.is3102.service.PatientIdandCheckingRemote;
import com.is3102.service.VisitorInfoServiceRemote;
import com.is3102.util.HandleDates;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class ExecutionBeanManaged implements Serializable {

    @EJB
    private ExecutionRemote em;
    @EJB
    public PatientIdandCheckingRemote pm;
    @EJB
    public VisitorInfoServiceRemote vm;
    private String name;
    private String passport_NRIC;
    private String case_Id;
    private String procedure_id;
    private String employee_id;
    private String exeuction_comment;
    private List<ExecutionLog> executionLogList;
    private String bloodPressure;
    private String temperature;
    private String heartRate;
    private String spO2;
    private String glucoseLevel;
    private String respiratoryRate;
    Patient patient1;
    private int age;
    List<Vitals> vitals = new ArrayList<Vitals>();
    private Long patientId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport_NRIC() {
        return passport_NRIC;
    }

    public void setPassport_NRIC(String passport_NRIC) {
        this.passport_NRIC = passport_NRIC;
    }

    public List<Vitals> getVitals() {
        return vitals;
    }

    public void setVitals(List<Vitals> vitals) {
        this.vitals = vitals;
    }

    public Patient getPatient1() {
        return patient1;
    }

    public void setPatient1(Patient patient1) {
        this.patient1 = patient1;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void doAddExecutionLog(ActionEvent actionEvent) {

        FacesContext context = FacesContext.getCurrentInstance();
        Long pId = Long.valueOf(procedure_id);
        Long dId = Long.valueOf(employee_id);
        try {
            em.AddExecutionRecordMedical(pId, dId, exeuction_comment);
            context.addMessage(null, new FacesMessage("Execution record added!"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot update diagnosis!", null));
        }
    }

    public void doCreateEvaluationReport(ActionEvent actionEvent) {

        FacesContext context = FacesContext.getCurrentInstance();
        try {

            Long pId = Long.valueOf(procedure_id);
            List<ExecutionLog> result = em.CreateEvaluationReport(pId);
            this.setExecutionLogList(result);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot update diagnosis!", null));
        }
    }

    public void doEnterVitals(ActionEvent ActionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Patient patient = pm.getPatientInfo(name, passport_NRIC);
            if (patient != null) {
                em.recordVitals(patient.getPatientId(), bloodPressure, temperature, heartRate, spO2, glucoseLevel, respiratoryRate);
                context.addMessage(null, new FacesMessage("Patient Vitals Recorded!"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Unable to add Vitals", null));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record Does not Exist!", null));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Update Vitals!", null));
        }
    }

    public void doretrievePInfo(ActionEvent actionEvent) throws ExistException {
        FacesContext context = FacesContext.getCurrentInstance();
        patient1 = vm.getPatient(name, passport_NRIC);
        if (patient1 != null) {
            patientId = patient1.getPatientId();
            String birthday = HandleDates.convertDateToString(patient1.getBirthday());
            System.out.println("Birthday: " + birthday);
            age = HandleDates.getAge(birthday);
            System.out.println("Age: " + age);
            System.out.println("Patient Id: " + patientId);
        } else {
            patientId = (long) 0;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record could not be found!", null));
        }
    }

    public void doListVitals(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            vitals = em.listVitals(patientId);
            if (vitals == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Vitals Not Found!", null));
            }
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Update Vitals!", null));
        }
    }

    public String getProcedure_id() {
        return procedure_id;
    }

    public void setProcedure_id(String procedure_id) {
        this.procedure_id = procedure_id;
    }

    public String getExeuction_comment() {
        return exeuction_comment;
    }

    public void setExeuction_comment(String exeuction_comment) {
        this.exeuction_comment = exeuction_comment;
    }

    public List<ExecutionLog> getExecutionLogList() {
        return executionLogList;
    }

    public void setExecutionLogList(List<ExecutionLog> executionLogList) {
        this.executionLogList = executionLogList;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getCase_Id() {
        return case_Id;
    }

    public void setCase_Id(String case_Id) {
        this.case_Id = case_Id;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getSpO2() {
        return spO2;
    }

    public void setSpO2(String spO2) {
        this.spO2 = spO2;
    }

    public String getGlucoseLevel() {
        return glucoseLevel;
    }

    public void setGlucoseLevel(String glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
    }

    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(String respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }
}