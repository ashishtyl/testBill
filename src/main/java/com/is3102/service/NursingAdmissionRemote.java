/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Nursing_Anamnesis;
import com.is3102.Exception.CaseException;
import com.is3102.Exception.ExistException;

/**
 *
 * @author A0078597
 */
public interface NursingAdmissionRemote {

    public void addAnamnesis(Long caseId, Long AnamnesisId, String currentdiagnosis, String orientation, String communication_ability, String contactsInfo, String nutrition, String mobility, String personalHygiene, String vitalSigns) throws ExistException, CaseException;

    public Nursing_Anamnesis getNursingAnamnesis(Long anamnesisId);

    public void removeAnamnesis(Long anamnesisId) throws ExistException;
    
}
