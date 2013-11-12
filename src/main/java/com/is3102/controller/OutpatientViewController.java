/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.LabRadProcedure;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.EntityClass.Medication;
import java.util.List;
import javax.ejb.EJB;
import com.is3102.EntityClass.mCase;
import com.is3102.service.OutpatientViewRemote;
import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ben
 */
@ManagedBean
@ViewScoped
public class OutpatientViewController implements Serializable {

    @EJB
    public OutpatientViewRemote opv;
    Long CIN;
    String passwordId;
    List<mCase> caseList;
    List<Diagnosis> dList;
    List<Medical_Procedure> mpList;
    List<Medication> mList;
    List<LabRadProcedure> lrpList;

    public void DoListmCase() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            System.out.println("In DoListmCase");
            caseList = opv.ListmCase(passwordId);
            //System.out.println("temp size:" + temp.size());
           // this.setCaseList(temp);
            System.out.println("Set case list");
            if (caseList.isEmpty() == true) {
                System.out.println("Error occured");
            }
            //return caseList;
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No records found, Please check your NRIC/PASSWORD Id!", null));
        }
        //return caseList;    
    }

    public List<Medical_Procedure> DoListMedical_Procedure(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            System.out.println("HelloHhhhhhhhhhhhhhhhhhhhhhhh");
            mpList = opv.ListMedical_Procedure(CIN);
            System.out.println("HelloHello");
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Case Not Match!", null));
        }
        return mpList;
    }

    public List<Medication> DoListMedication(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            System.out.println("Hello");
            mList = opv.ListMedication(CIN);
            System.out.println("HelloHello");
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Case Not Match!", null));
        }
        return mList;
    }

    public List<Diagnosis> DoListDiagnosis(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            System.out.println("Hello");
            dList = opv.ListDiagnosis(CIN);
            System.out.println("HelloHello");
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Case Not Match!", null));
        }
        return dList;
    }

    public List<LabRadProcedure> DoListLabRadProcedure(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            System.out.println("Hello");
            lrpList = opv.ListLabRadProcedure(CIN);
            System.out.println("HelloHello");
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Case Not Match!", null));
        }
        return lrpList;
    }

    public OutpatientViewRemote getOpv() {
        return opv;
    }

    public void setOpv(OutpatientViewRemote opv) {
        this.opv = opv;
    }

    public Long getCIN() {
        return CIN;
    }

    public void setCIN(Long CIN) {
        this.CIN = CIN;
    }

    public String getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(String passwordId) {
        this.passwordId = passwordId;
    }

    public List<mCase> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<mCase> caseList) {
        this.caseList = caseList;
    }

    public List<Diagnosis> getdList() {
        return dList;
    }

    public void setdList(List<Diagnosis> dList) {
        this.dList = dList;
    }

    public List<Medical_Procedure> getMpList() {
        return mpList;
    }

    public void setMpList(List<Medical_Procedure> mpList) {
        this.mpList = mpList;
    }

    public List<Medication> getmList() {
        return mList;
    }

    public void setmList(List<Medication> mList) {
        this.mList = mList;
    }

    public List<LabRadProcedure> getLrpList() {
        return lrpList;
    }

    public void setLrpList(List<LabRadProcedure> lrpList) {
        this.lrpList = lrpList;
    }
}
