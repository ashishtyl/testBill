/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import com.is3102.EntityClass.ExecutionLog;
import com.is3102.service.ExecutionRemote;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author AshishLong pId = Long.valueOf(procedure_id);
 */
@ManagedBean
public class ExecutionBeanManaged implements Serializable {

    @EJB
    private ExecutionRemote ex;
    //CIN
    private String case_Id;
    //Id of procedure instance associate with a case
    private String procedure_id;
    //Doctor id
    private String employee_id;
    //Execution comment documented at the time of completion
    private String exeuction_comment;
    //list of execution log comments associated with one procedure entity
    private List<ExecutionLog> executionLogList;
    //fields for Vitals entity
    private String bloodPressure;
    private String temperature;
    private String heartRate;
    private String spO2;
    private String glucoseLevel;
    private String respiratoryRate;

    public void doAddExecutionLog(ActionEvent actionEvent) {

        FacesContext context = FacesContext.getCurrentInstance();
        Long pId = Long.valueOf(procedure_id);
        Long dId = Long.valueOf(employee_id);
        try {
            ex.AddExecutionRecordMedical(pId, dId, exeuction_comment);
            context.addMessage(null, new FacesMessage("Execution record added!"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot update diagnosis!", null));
        }
    }
    /*public void AddExecutionRecord(Long procedure_id, Long doctor_id, String exeuction_comment) throws ExistException;

     public List<ExecutionLog> CreateEvaluationReport(Long procedure_id) throws ExistException;*/

    public void doCreateEvaluationReport(ActionEvent actionEvent) {

        FacesContext context = FacesContext.getCurrentInstance();
        try {

            Long pId = Long.valueOf(procedure_id);
            List<ExecutionLog> result = ex.CreateEvaluationReport(pId);
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

            Long cId = Long.valueOf(procedure_id);
            ex.recordVitals(cId, bloodPressure, temperature, heartRate, spO2, glucoseLevel, respiratoryRate);
            context.addMessage(null, new FacesMessage("Vitals recorded!"));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot update diagnosis!", null));
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
