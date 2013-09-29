/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.Medical_Anamnesis;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.CaseException;
import com.is3102.Exception.ExistException;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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
    public void addAnamnesis(Long caseId,  String diseaseHistory,
                             String socialHistory, String medicalHistory,
                             String familyHistory, String allergies, String symptoms) throws ExistException, CaseException {
        mcase = getCaseInfo(caseId);
        anamnesis = new Medical_Anamnesis();
        //System.out.println("Test1");
        anamnesis.create(diseaseHistory,
                socialHistory, medicalHistory,
                familyHistory, allergies, symptoms);
       // System.out.println("Test2");
        mcase.setMedicalAnamnesis(anamnesis);
        //System.out.println("Test3");
        em.persist(mcase);
        //System.out.println("Test4");
        //em.flush();
    }

    @Override
    public Medical_Anamnesis getAnamnesis(Long anamnesisId) throws ExistException{
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
    public void removeAnamnesis(Long anamnesisId) throws ExistException{
        anamnesis = em.find(Medical_Anamnesis.class, anamnesisId);
        if (anamnesis == null) {
            throw new ExistException("Anamnesis does not exsit");
        }
        em.remove(anamnesis);
    }
    //lack of query sentence to database?
    //following methods have been codded in the coding bean
    /*  @Override
     public void codeDiagnosis(String diseaseCode,
     String description){
     diagnosis=new Diagnosis();
     //diagnosis.create(diseaseCode,description);
     em.persist(diagnosis);
     //need to confirm bidirectional or unidirectional for Diaggosis

     }

     @Override
     public void removeDiagnosis(Long diagnosisId) {
     diagnosis=em.find(Diagnosis.class, diagnosisId);
     em.remove(diagnosis);

     }
     @Override
     public Diagnosis getDiagnosis(Long diagnosisId){
     return em.find(Diagnosis.class, diagnosisId);

     }*/
}