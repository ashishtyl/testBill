/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.ICD10_Code;
import com.is3102.EntityClass.ICD10_PCS;
import com.is3102.Exception.ExistException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author Ashish
 */import javax.ejb.Local;
@Local
public interface CodingBeanRemote1 {

    public List<ICD10_Code> getMatchingCodes(String description);

    public void updateDiagnosis(Long caseId, ICD10_Code icd10Code, String description) throws ExistException;

    public List<Diagnosis> listAllDiagnosis(long caseId) throws ExistException;

    public void addCode(String id, String Chapter, String block, String Disease, String name) throws ExistException;

    public List<ICD10_Code> listAllCodes();

    public void removeDiagnosis(long diagnosisId)throws ExistException;

    public List<ICD10_PCS> listAllProceures();

    public void addProcedure(String id, String name, Long price) throws ExistException;
    
}
