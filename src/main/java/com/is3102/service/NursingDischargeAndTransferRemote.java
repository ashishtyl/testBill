/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.DischargeSummary;
import com.is3102.Exception.CaseException;

/**
 *
 * @author ashish
 */
public interface NursingDischargeAndTransferRemote {

    public DischargeSummary getDischargeSummary(Long dischargeId);

    public void deleteDischargeSummary(Long dischargeId);

    public void generateDischargeSummary(Long caseId, String activityLevel, String diet, String woundcare) throws CaseException;
    
}
