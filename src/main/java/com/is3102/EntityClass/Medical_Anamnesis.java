/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ben
 */
@Entity
public class Medical_Anamnesis implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long anamnesisId;
    private String diseaseHistory;
    private String socialHistory;
    private String medicalHistory;
    private String familyHistory;
    private String allergies;
    private String symptoms;
    private boolean isPregnant;

    public Medical_Anamnesis() {
    }

    public void create(String diseaseHistory, String socialHistory, String medicalHistory, String familyHistory, String allergies, String symptoms) {
        this.setDiseaseHistory(diseaseHistory);
        this.setSocialHistory(socialHistory);
        this.setMedicalHistory(medicalHistory);
        this.setFamilyHistory(familyHistory);
        this.setAllergies(allergies);
        this.setSymptoms(symptoms);
        this.setIsPregnant(isPregnant);
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

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    /**
     * @return the isPregnant
     */
    public boolean isIsPregnant() {
        return isPregnant;
    }

    /**
     * @param isPregnant the isPregnant to set
     */
    public void setIsPregnant(boolean isPregnant) {
        this.isPregnant = isPregnant;
    }
}