/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.Medical_Anamnesis;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.CaseException;
import com.is3102.Exception.ExistException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Ben
 */
@Stateful
public class MedicalAdmissionBean1 implements MedicalAdmissionBean1Remote {

    @PersistenceContext()
    EntityManager em;
    Medical_Anamnesis anamnesis;
    List<Medical_Anamnesis> anamnesisList;
    List<mCase> caseList;
    Diagnosis diagnosis;
    mCase mcase;

    public MedicalAdmissionBean1() {
    }

    //lack of query sentence to database?
    private mCase getCaseInfo(Long caseId) throws CaseException {
        mcase = em.find(mCase.class, caseId);
        if (mcase == null) {
            throw new CaseException("Case does not exsit");
        }
        return mcase;
    }

    @Override
    public void addAnamnesis(Long caseId, String diseaseHistory,
            String socialHistory, String medicalHistory,
            String familyHistory, String allergies, String symptoms) throws ExistException, CaseException {
        mcase = getCaseInfo(caseId);
        anamnesis = new Medical_Anamnesis();
        //System.out.println("Test1");
        anamnesis.create(diseaseHistory,
                socialHistory, medicalHistory,
                familyHistory, allergies, symptoms);
        mcase.setMedicalAnamnesis(anamnesis);
        em.persist(mcase);

    }

    @Override
    public Medical_Anamnesis getAnamnesis(Long anamnesisId) throws ExistException {
        anamnesis = em.find(Medical_Anamnesis.class, anamnesisId);
        if (anamnesis == null) {
            throw new ExistException("Anamnesis does not exsit");
        }
        return em.find(Medical_Anamnesis.class, anamnesisId);
    }
    //list testing

    public List<Medical_Anamnesis> ListMedical_Anamnesis() {
        anamnesisList = new ArrayList<Medical_Anamnesis>();
        try {
            Query qb = em.createQuery("SELECT m FROM Medical_Anamnesis m");

            for (Object ob : qb.getResultList()) {
                anamnesis = (Medical_Anamnesis) ob;
                anamnesisList.add(anamnesis);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return anamnesisList;
    }
    //list testing

    @Override
    public void removeAnamnesis(Long anamnesisId) throws ExistException {
        anamnesis = em.find(Medical_Anamnesis.class, anamnesisId);
        if (anamnesis == null) {
            throw new ExistException("Anamnesis does not exsit");
        }
        em.remove(anamnesis);
    }

    @Override
    public List<mCase> ListmCase() {
        caseList = new ArrayList<mCase>();
        try {
            Query qb = em.createQuery("SELECT m FROM mcase m");

            for (Object ob : qb.getResultList()) {
                mcase = (mCase) ob;
                caseList.add(mcase);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return caseList;
    }

    public Medical_Anamnesis listAnamnesis(String CIN) {
        System.out.println(CIN);
        mCase mcase = em.find(mCase.class, new Long(CIN));
        if (mcase != null) {
            Medical_Anamnesis mAnamnesis = mcase.getMedicalAnamnesis();
            if (mAnamnesis != null) {
                System.out.println(mAnamnesis.getAllergies());
                return mAnamnesis;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void updateAnamnesis(Long anamnesisId, String newDiseaseHistory, String newSocialHistory, String newFamilyHistory, String newMedicalHistory, String newAllergies, String newSymptoms) {
        Medical_Anamnesis anamnesis = em.find(Medical_Anamnesis.class, anamnesisId);
        anamnesis.setDiseaseHistory(newDiseaseHistory);

        anamnesis.setSocialHistory(newSocialHistory);

        anamnesis.setFamilyHistory(newFamilyHistory);

        anamnesis.setMedicalHistory(newMedicalHistory);

        anamnesis.setAllergies(newAllergies);

        anamnesis.setSymptoms(newSymptoms);

        em.merge(anamnesis);
    }
}