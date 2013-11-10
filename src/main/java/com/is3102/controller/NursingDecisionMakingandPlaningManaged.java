/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import com.is3102.service.*;

import com.is3102.EntityClass.Finding;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.EntityClass.Nursing_Procedure;
import com.is3102.Exception.ExistException;

import java.io.Serializable;
import java.util.List;
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
public class NursingDecisionMakingandPlaningManaged implements Serializable {

    @EJB
    private NursingDecisionMakingandPlaningRemote ndmp;
    //case identifaction number to search for a case
    private String CIN;
    //Patient identification number to retrieve patient id
    private String PIN;
    //Procedure code for doctors to enter when entering a new procedure
    private String procedure_code;
    //{rocedure name for them to enter when entering a new procedure
    private String procedure_name;
    //Finidng entity to create a new pro
    private String comments;
    //Procedure id to add patient consent to a procedure
    private String procedureId;
    //Patient comment recorded at the time getting the consent. 
    private String patient_comment;
    //List of medical procedures associated with a Case. 
    private List<Nursing_Procedure> nursingProcedures;

    public void doAddPlannedProcedure(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("In DoAddPlannedProcedure");
        try {
            Long cin = Long.valueOf(getCIN());
            System.out.println("Enter try");
            ndmp.AddPlanedProcedure(cin, procedure_code, procedure_name, comments);
            System.out.println("Leave try");
            context.addMessage(null, new FacesMessage("Procedure " + procedure_code + " for " + getCIN() + " successfully added!"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Procedure could not be added!", null));
        }
    }

    public void doRetrieveCarePlaning() {
        System.out.println("In doRetrieveCarePlaning");
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            System.out.println("in try");
            Long cin = Long.valueOf(getCIN());
            List<Nursing_Procedure> result = ndmp.RetrieveNursingPlaning(cin);
            System.out.println("list returned");
            this.setMedicalProcedures(result);
            System.out.println("Resutls set");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Procedure could not be added!", null));
        }
    }

    public String getProcedure_code() {
        return procedure_code;
    }

    public void setProcedure_code(String procedure_code) {
        this.procedure_code = procedure_code;
    }

    public String getProcedure_name() {
        return procedure_name;
    }

    public void setProcedure_name(String procedure_name) {
        this.procedure_name = procedure_name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public String getPatient_comment() {
        return patient_comment;
    }

    public void setPatient_comment(String patient_comment) {
        this.patient_comment = patient_comment;
    }

    public List<Nursing_Procedure> getMedicalProcedures() {
        return nursingProcedures;
    }

    public void setMedicalProcedures(List<Nursing_Procedure> nursingProcedures) {
        this.nursingProcedures = nursingProcedures;
    }

    /**
     * @return the CIN
     */
    public String getCIN() {
        return CIN;
    }

    /**
     * @param CIN the CIN to set
     */
    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    /**
     * @return the PIN
     */
    public String getPIN() {
        return PIN;
    }

    /**
     * @param PIN the PIN to set
     */
    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
}
