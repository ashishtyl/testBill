/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.DischargeSummary;
import com.is3102.EntityClass.Transfer;
import com.is3102.Exception.CaseException;
import com.is3102.Exception.ExistException;

import javax.ejb.Remote;
import java.util.List;

/**
 *
 * @author Ben
 */
@Remote
public interface DischargeAndTransferBean1Remote {
  
    public void generateDischargeSummary(Long caseId, String diagnosis, String findings,
                                         String recommendation, String patientState, String medicalProcedure)throws CaseException;
    public DischargeSummary getDischargeSummary(Long dischargeId)throws ExistException;
    public void deleteDischargeSummary(Long dischargeId)throws ExistException;
    public List<DischargeSummary> ListDischargeSummary();
    public void recodeTransfer(Long caseId, String referDoctor, String reason)throws CaseException;
    public Transfer getTransfer(Long transferId)throws ExistException;
    public void deleteTransfer(Long transferId)throws ExistException;
    public List<Transfer> ListTransfer();
}
