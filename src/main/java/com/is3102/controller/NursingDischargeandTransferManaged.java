/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import com.is3102.service.*;
import com.is3102.EntityClass.DischargeSummary;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ashish
 */
@ManagedBean
public class NursingDischargeandTransferManaged implements Serializable {

    @EJB
    private NursingDischargeAndTransferRemote dt;
    //case id to search case
    private String caseId;
    //Id for discharge entity
    private String dischargeId;
    //final diagnosis to enter in the discharge summary
    private String activityLevel;
    //final findings
    private String diet;
    //Doctors recommendation at the time of discharge
    private String woundCare;
    //
    private DischargeSummary dischargeSummary;
    //
    private Long transferid;

    public void DogenerateDischargeSummary(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("In DoGenerateDischargeSummary");
        Long cId=Long.valueOf(caseId);
        try {
            dt.generateDischargeSummary(cId,activityLevel, diet, woundCare);
            context.addMessage(null, new FacesMessage("Nursing discharge summary generated for " + caseId));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Discharge summmary could not be added!", null));
        }
    }

    public void DogetDischargeSummary(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("In DogetDischargeSummary");
        Long dId=Long.valueOf(dischargeId);
        try {
            this.setDischargeSummary(dt.getDischargeSummary(dId));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Discharge summary could not be found!", null));
        }
    }

    public void DoDeleteSummary(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("In DoDeleteSummary");
         Long dId=Long.valueOf(dischargeId);
        try {
            dt.deleteDischargeSummary(dId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Discharge summary could not be found!", null));
        }
    }

    public NursingDischargeAndTransferRemote getDt() {
        return dt;
    }

    public void setDt(NursingDischargeAndTransferRemote dt) {
        this.dt = dt;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getDischargeId() {
        return dischargeId;
    }

    public void setDischargeId(String dischargeId) {
        this.dischargeId = dischargeId;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getWoundCare() {
        return woundCare;
    }

    public void setWoundCare(String woundCare) {
        this.woundCare = woundCare;
    }

    public DischargeSummary getDischargeSummary() {
        return dischargeSummary;
    }

    public void setDischargeSummary(DischargeSummary dischargeSummary) {
        this.dischargeSummary = dischargeSummary;
    }

    public Long getTransferid() {
        return transferid;
    }

    public void setTransferid(Long transferid) {
        this.transferid = transferid;
    }

    

  
}