/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Medical_Anamnesis;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.CaseException;
import com.is3102.Exception.ExistException;
import javax.ejb.Remote;
import java.util.List;

/**
 *
 * @author Ben
 */
@Remote
public interface MedicalAdmissionBean1Remote {
  
    public void addAnamnesis(Long caseId, String diseaseHistory,
                             String socialHistory, String medicalHistory,
                             String familyHistory, String allergies, String symptoms, String pregnancy) throws ExistException,  CaseException;
    public Medical_Anamnesis getAnamnesis(Long anamnesisId)throws ExistException;
    public void removeAnamnesis(Long anamnesisId)throws ExistException;
    public List<Medical_Anamnesis> ListMedical_Anamnesis();
     public List<mCase> ListmCase();
     public Medical_Anamnesis listAnamnesis(String CIN);
     public void updateAnamnesis(Long anamnesisId, String newDiseaseHistory, String newSocialHistory, String newFamilyHistory, String newMedicalHistory, String newAllergies, String newSymptoms);
   /* public void codeDiagnosis(String diseaseCode, String description);
    public void removeDiagnosis(Long diagnosisId);
    public Diagnosis getDiagnosis(Long diagnosisId);*/
    
}
