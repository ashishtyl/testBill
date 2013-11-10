/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.service.*;

import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.Medical_Anamnesis;
import com.is3102.EntityClass.Nursing_Anamnesis;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.CaseException;
import com.is3102.Exception.ExistException;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ben
 */
@Stateful
public class NursingAdmissionBean implements NursingAdmissionRemote {

    @PersistenceContext()
    EntityManager em;
    Nursing_Anamnesis anamnesis;
    Diagnosis diagnosis;
    mCase mcase;

    public NursingAdmissionBean() {
    }

    private mCase getCaseInfo(Long caseId) throws CaseException {
        System.out.println("In Nursing Admission Bean: getCaseInfo()");
        mcase = em.find(mCase.class, new Long(caseId));
        if (mcase == null) {
            System.out.println("Case not found!");
            throw new CaseException("Case does not exsit");
        }
        System.out.println("Case found!");
        return mcase;
    }

    //lack of query sentence to database?
    public void addAnamnesis(Long caseId, Long AnamnesisId, String currentdiagnosis, String orientation, String communication_ability, String contactsInfo,
            String nutrition, String mobility, String personalHygiene, String vitalSigns) throws ExistException, CaseException {

        System.out.println("In Nursing Admission Bean: addAnamnesis()");
        anamnesis = em.find(Nursing_Anamnesis.class, AnamnesisId);

        if (anamnesis != null) {
            System.out.println("Anamnesis Already Exists!");
            throw new ExistException("Anamnesis Already Exists!");
        }

        System.out.println("Creating Anamnesis ");
        anamnesis = new Nursing_Anamnesis();
        anamnesis.create(currentdiagnosis, orientation, communication_ability, contactsInfo, nutrition, mobility, personalHygiene, vitalSigns);
        System.out.println("Anamnesis Created ");
        System.out.println("Searching mCase");
        mcase = getCaseInfo(caseId);
        System.out.println("mCase found");
        mcase.setNursingAnamnesis(anamnesis);
        System.out.println("anamnesis set");
        em.persist(mcase);
    }

    public Nursing_Anamnesis getNursingAnamnesis(Long anamnesisId) {
        return em.find(Nursing_Anamnesis.class, anamnesisId);
    }

    public void removeAnamnesis(Long anamnesisId) throws ExistException {
        System.out.println("In Nursing Admission Bean: removeAnamnesis()");
        anamnesis = em.find(Nursing_Anamnesis.class, anamnesisId);
        if (anamnesis==null){
            System.out.println("Anamnesis not found!");
            throw new ExistException("Anamnesis not found!");
        }
        em.remove(anamnesis);
    }
}