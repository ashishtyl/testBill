/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.DischargeSummary;
import com.is3102.EntityClass.NursingDischarge;
import com.is3102.EntityClass.Transfer;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.CaseException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ashish
 */
@Stateless
public class NursingDischargeAndTransfer implements NursingDischargeAndTransferRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext()
    EntityManager em;
    NursingDischarge dischargeSummary;
    Transfer transfer;
    mCase mcase;

    public NursingDischargeAndTransfer() {
    }

    private mCase getCaseInfo(Long caseId) throws CaseException {
        mcase = em.find(mCase.class, new Long(caseId));
        if (mcase == null) {
            throw new CaseException("Case does not exsit");
        }
        return mcase;
    }

    public void generateDischargeSummary(Long caseId, String activityLevel, String diet, String woundcare) throws CaseException {
        System.out.println("In NDTB: generate summary ");
        dischargeSummary = new NursingDischarge();
        try {
            System.out.println("In try");
            mcase = getCaseInfo(caseId);
            System.out.println("Case found");
            dischargeSummary.create(activityLevel, diet, woundcare);
            System.out.println("Discharge summary created");
            mcase.setNursingDischarge(dischargeSummary);
            System.out.println("dischrage set");
            em.persist(mcase);
            System.out.println("persisted");
        } catch (Exception e) {
            System.out.println("Case Exception");
            System.out.println(e.getMessage());
        }
        System.out.println("Leaving generate summary");
    }

    public DischargeSummary getDischargeSummary(Long dischargeId) {
        return em.find(DischargeSummary.class, dischargeId);
    }

    public void deleteDischargeSummary(Long dischargeId) {
        dischargeSummary = em.find(NursingDischarge.class, dischargeId);
        em.remove(dischargeSummary);
    }


}
