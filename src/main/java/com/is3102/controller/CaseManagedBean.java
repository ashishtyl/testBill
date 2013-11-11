/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.LabRadProcedure;
import com.is3102.EntityClass.Medical_Anamnesis;
import com.is3102.EntityClass.Medical_Procedure;
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
import com.is3102.service.CodingBeanRemote;
import com.is3102.service.DecisionMakingandPlaningRemote;
import com.is3102.service.MedicalAdmissionBean1Remote;
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
    private MedicalAdmissionBean1Remote mam;
    @EJB
    private DecisionMakingandPlaningRemote dmm;
    @EJB
    private CodingBeanRemote cbm;
    @EJB
    public OrderEntryRemote oem;
    mCase mcase;
    String CIN;
    Date dateDischarged;
    String type;
    String name;
    String NRIC_PIN;
    Date dateAdmitted;
    Medical_Anamnesis anamnesis;
    List<Diagnosis> diagnosis = new ArrayList<Diagnosis>();
    List<Medical_Procedure> mprocedures = new ArrayList<Medical_Procedure>();
    //List<Medical_Diagnosis> diagnoses = new ArrayList<Medical_Diagnosis>();
    List<Medication> medication = new ArrayList<Medication>();
    List<LabRadProcedure> procedures = new ArrayList<LabRadProcedure>();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNRIC_PIN() {
        return NRIC_PIN;
    }

    public void setNRIC_PIN(String NRIC_PIN) {
        this.NRIC_PIN = NRIC_PIN;
    }

    public Date getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(Date dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public List<Medical_Procedure> getMprocedures() {
        return mprocedures;
    }

    public void setMprocedures(List<Medical_Procedure> mprocedures) {
        this.mprocedures = mprocedures;
    }

    public List<Medication> getMedication() {
        return medication;
    }

    public void setMedication(List<Medication> medication) {
        this.medication = medication;
    }

    public Medical_Anamnesis getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(Medical_Anamnesis anamnesis) {
        this.anamnesis = anamnesis;
    }

    public List<Diagnosis> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(List<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
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
        FacesContext context = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        mcase = am.retrievePatientCase(name, NRIC_PIN, format.format(dateAdmitted));
        if (mcase != null) {
            CIN = Long.toString(mcase.getCIN());
        } else {
            CIN = Long.toString(0);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Case could not be found!", null));
        }
    }

    public void doUpdateAnamnesis(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        anamnesis = mam.listAnamnesis(CIN);
        if (anamnesis == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Anamnesis could not be found!", null));
        }
    }

    public void doListProcedure(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        mprocedures = dmm.listProcedures(CIN);
        if (mprocedures == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Procedures do not exist!", null));
        }
    }
    
    public void doViewDiagnoses(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        diagnosis = cbm.listDiagnoses(CIN);
        if (diagnosis == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Diagnoses do not exist!", null));
        }
    }

    public void doViewMedication(ActionEvent actionEvent) {
        medication = oem.listMedication(CIN);
    }

    public void doViewProcedures(ActionEvent actionEvent) {
        procedures = oem.listLabRadProcedures(CIN);
    }

    public void onEditCase(RowEditEvent event) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String newDateDischarge;
            if (mcase.getDateDischarged() == null) {
                newDateDischarge = null;
            } else {
                if (((mCase) event.getObject()).getDateDischarged() == null) {
                    newDateDischarge = null;
                } else {
                    newDateDischarge = format.format(((mCase) event.getObject()).getDateDischarged());
                }
            }
            String newType = (String) ((mCase) event.getObject()).getType();
            am.updateCase(mcase.getCIN(), newDateDischarge, newType);
            FacesMessage msg = new FacesMessage("Case Successfully Edited!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Case could not be Edited!", null));
        }
    }

    public void onCancelCase(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Case Not Edited!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onEditAnamnesis(RowEditEvent event) {
        try {
            String newDiseaseHistory = (String) ((Medical_Anamnesis) event.getObject()).getDiseaseHistory();
            String newSocialHistory = (String) ((Medical_Anamnesis) event.getObject()).getSocialHistory();
            String newMedicalHistory = (String) ((Medical_Anamnesis) event.getObject()).getMedicalHistory();
            String newFamilyHistory = (String) ((Medical_Anamnesis) event.getObject()).getFamilyHistory();
            String newAllergies = (String) ((Medical_Anamnesis) event.getObject()).getAllergies();
            String newSymptoms = (String) ((Medical_Anamnesis) event.getObject()).getSymptoms();
            mam.updateAnamnesis(anamnesis.getAnamnesisId(), newDiseaseHistory, newSocialHistory, newFamilyHistory, newMedicalHistory, newAllergies, newSymptoms);
            FacesMessage msg = new FacesMessage("Anamnesis Successfully Edited!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Anamnesis could not be Edited!", null));
        }
    }

    public void onCancelAnamnesis(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Anamnesis Not Edited!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /*public void onEditProcedure(RowEditEvent event) {
        try {
            String newCode = (String) ((Medical_Procedure) event.getObject()).getProcedureCode().getCode();
            String newComments = (String) ((Medical_Procedure) event.getObject()).getComments();
            String newConsent = (String) ((Medical_Procedure) event.getObject()).getPatientConsent();
            dmm.updateProcedure((Long) ((Medical_Procedure) event.getObject()).getMpId(), newComments, newConsent);
            FacesMessage msg = new FacesMessage("Procedure Successfully Edited!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Procedure could not be Edited!", null));
        }
    }

    public void onCancelProcedure(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Procedure Not Edited!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onEditDiagnosis(RowEditEvent event) {
        try {
            String newDescription = (String) ((Medical_Procedure) event.getObject()).getComments();
            cbm.updateDiagnosis((Long) ((Diagnosis) event.getObject()).getId(), newDescription);
            FacesMessage msg = new FacesMessage("Diagnosis Successfully Edited!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Procedure could not be Edited!", null));
        }
    }

    public void onCancelDiagnosis(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Diagnosis Not Edited!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }*/
}