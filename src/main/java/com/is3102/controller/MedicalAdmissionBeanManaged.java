/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import com.is3102.EntityClass.Medical_Anamnesis;
import com.is3102.EntityClass.mCase;
import com.is3102.service.MedicalAdmissionBean1;
import com.is3102.service.MedicalAdmissionBean1Remote;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.List;

/**
 *
 * @author Ben
 */
@ManagedBean
public class MedicalAdmissionBeanManaged {

    @EJB
    private MedicalAdmissionBean1Remote mab;
    private Long caseId;
    private Long anamnesisId;
    private String diseaseHistory;
    private String socialHistory;
    private String medicalHistory;
    private String familyHistory;
    private String allergies;
    private String symptoms;
    private String isPregnant;
    private Medical_Anamnesis mAnamnesis;
    //list testing
    private List<Medical_Anamnesis> list;
    private List<mCase> caseList;
    private mCase mcase;
    //list testing

    /*public void addAnamnesis(Long caseId, Long AnamnesisId, String diseaseHistory,
     String socialHistory, String medicalHistory,
     String familyHistory, String allergies, String symptoms) throws ExistException,  CaseException;
     public Medical_Anamnesis getAnamnesis(Long anamnesisId);
     public void removeAnamnesis(Long anamnesisId);*/
    public void DoAddAnamnesis(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            //System.out.println(anamnesisId);
            mab.addAnamnesis(caseId, diseaseHistory, socialHistory, medicalHistory, familyHistory, allergies, symptoms, isPregnant);
            context.addMessage(null, new FacesMessage("Anamnesis added successfully"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Anamnesis cannot be added!", null));
        }
    }
    


    public void DoGetAnamnesis(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Medical_Anamnesis anamnesis = mab.getAnamnesis(anamnesisId);
            this.setmAnamnesis(anamnesis);
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Anamnesis cannot be found!", null));
        }
    }

    public void DoRemoveAnamnesis(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            mab.removeAnamnesis(anamnesisId);
            context.addMessage(null, new FacesMessage("Anamnesis removed successfully"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Anamnesis cannot be found!", null));
        }
    }

    public Medical_Anamnesis getmAnamnesis() {
        return mAnamnesis;
    }

    public void setmAnamnesis(Medical_Anamnesis mAnamnesis) {
        this.mAnamnesis = mAnamnesis;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getAnamnesisId() {
        return anamnesisId;
    }

    public void setAnamnesisId(Long anamnesisId) {
        this.anamnesisId = anamnesisId;
    }

    public String getDiseaseHistory() {
        return diseaseHistory;
    }

    public void setDiseaseHistory(String diseaseHistory) {
        this.diseaseHistory = diseaseHistory;
    }

    public String getSocialHistory() {
        return socialHistory;
    }

    public void setSocialHistory(String socialHistory) {
        this.socialHistory = socialHistory;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public MedicalAdmissionBean1Remote getMab() {
        return mab;
    }

    public void setMab(MedicalAdmissionBean1Remote mab) {
        this.mab = mab;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public void setMab(MedicalAdmissionBean1 mab) {
        this.mab = mab;
    }

    public List<Medical_Anamnesis> getList() {
        System.out.println("Hello");
        list = mab.ListMedical_Anamnesis();
        System.out.println("HelloHello");
        return list;
    }
 
    public void setList(List<Medical_Anamnesis> list) {
        this.list = list;
    }
    
     public List<mCase> getCaseList() {
        caseList = mab.ListmCase();
        return caseList;
    }

    public void setCaseList(List<mCase> list1) {
        this.caseList = list1;
    }

    /**
     * @return the isPregnant
     */
    public String getIsPregnant() {
        return isPregnant;
    }

    /**
     * @param isPregnant the isPregnant to set
     */
    public void setIsPregnant(String isPregnant) {
        this.isPregnant = isPregnant;
    }
    
}
