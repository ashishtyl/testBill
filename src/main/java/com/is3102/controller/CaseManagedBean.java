/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import com.is3102.EntityClass.LabRadProcedure;
import com.is3102.EntityClass.Medication;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import com.is3102.EntityClass.mCase;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.is3102.service.AdministrativeAdmissionRemote;
import com.is3102.service.OrderEntryRemote;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author user
 */
/**
 *
 * @author Swarit
 */
@ManagedBean
@ViewScoped
public class CaseManagedBean implements Serializable {

    @EJB
    public AdministrativeAdmissionRemote am;
    @EJB
    public OrderEntryRemote oem;
    mCase mcase;
    String CIN;
    Date dateDischarged;
    String type;
    List<Medication> medication = new ArrayList <Medication>();
    List<LabRadProcedure> procedures = new ArrayList <LabRadProcedure>();
    

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public mCase getMcase() {
        return mcase;
    }

    public void setMcase(mCase mcase) {
     this.mcase = mcase;
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

    public List<Medication> getMedication() {
        return medication;
    }

    public void setMedication(List<Medication> medication) {
        this.medication = medication;
    }

    public List<LabRadProcedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<LabRadProcedure> procedures) {
        this.procedures = procedures;
    }

    public CaseManagedBean() {
    }

    public void doUpdateCase(ActionEvent actionEvent) {
        mcase = am.getmCase(CIN);
    }
    
    public void doViewMedication(ActionEvent actionEvent) {
        medication = oem.listMedication(CIN);
    }
    
    public void doViewProcedures(ActionEvent actionEvent) {
        procedures = oem.listLabRadProcedures(CIN);
    }

    public void onEdit(RowEditEvent event) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String newDateDischarge = format.format(((mCase) event.getObject()).getDateDischarged());
            String newType = (String) ((mCase) event.getObject()).getType();           
            am.updateCase(mcase.getCIN(), newDateDischarge, newType);
            //am.update(patient.getPatientId(), newPassport_NRIC, newName, newAddress, newBirthday, newNumber, newWeight);
            FacesMessage msg = new FacesMessage("Case Successfully Edited!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Case could not be Edited!", null));
        }
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Case Not Edited!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}