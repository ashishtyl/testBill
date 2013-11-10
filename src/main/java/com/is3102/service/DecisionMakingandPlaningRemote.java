/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Finding;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.Exception.ExistException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ashish
 */
import javax.ejb.Local;
@Local
public interface DecisionMakingandPlaningRemote {

    public void AddPlanedProcedure(Long CIN, String procedure_code, String procedure_name, String finding, String comments) throws ExistException;

    public void GetConsent(Long procedureId, String patient_comment) throws ExistException;

    public List<Medical_Procedure> RetrieveCarePlaning(Long CIN) throws ExistException;

    public void RetrieveMedicalKnowledge();
    
    public List<Medical_Procedure> listProcedures(String CIN);
    
}
