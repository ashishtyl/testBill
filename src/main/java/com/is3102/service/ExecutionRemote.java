/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.ExecutionLog;
import com.is3102.Exception.ConsentException;
import com.is3102.Exception.ExistException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ashish
 */
import javax.ejb.Local;

@Local
public interface ExecutionRemote {

    public void AddExecutionRecordMedical(Long procedure_id, Long doctor_id, String exeuction_comment) throws ExistException, ConsentException;

    public void AddExecutionRecordNursing(Long procedure_id, Long doctor_id, String exeuction_comment) throws ExistException;

    public List<ExecutionLog> CreateEvaluationReport(Long procedure_id) throws ExistException;  
    
    public void recordVitals (Long caseId, String bp, String tmp, String HR, String spO2, String glucose, String rRate)throws ExistException;
 
}