/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Nursing_Procedure;
import com.is3102.Exception.ExistException;
import java.util.List;

/**
 *
 * @author A0078597
 */
public interface NursingDecisionMakingandPlaningRemote {

    public List<Nursing_Procedure> RetrieveNursingPlaning(Long CIN) throws ExistException;

    public void AddPlanedProcedure(Long CIN, String procedure_code, String procedure_name, String comments) throws ExistException;
    
}
