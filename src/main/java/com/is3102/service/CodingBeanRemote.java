/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.ICD10_Code;
import com.is3102.EntityClass.ICD10_PCS;
import com.is3102.EntityClass.ICNP_Code;
import com.is3102.Exception.ExistException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author Ashish
 */
@Remote
public interface CodingBeanRemote {

    // public List<ICD10_Code> getMatchingCodes(String description);
    public void addDiagnosis(Long caseId, String display, String description) throws ExistException;

    public void addCode(String id, String Chapter, String block, String Disease, String name) throws ExistException;

    //public Set<ICD10_Code> listAllCodes();
    public List<ICD10_Code> listAllCodes();

    public void removeDiagnosis(Long diagnosisId) throws ExistException;

    public List<Diagnosis> listAllDiagnosis(Long caseId) throws ExistException;

    public List<ICD10_PCS> listAllMedicalProceures();

    public void addProcedure(String id, String name, Long price) throws ExistException;
    
    public List<Diagnosis> listDiagnoses(String CIN);
    
    public void updateDiagnosis(Long diagnosisId, String newDescription);
    
    public void addMedicalProcedure(String id, String name, Long price) throws ExistException;
    
    public void addNursingProcedure(String category, String id, String name, Long price) throws ExistException;
    
    public List<ICNP_Code> listAllNursingProcedures();

}
