/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.ICD10_Code;
import com.is3102.EntityClass.ICD10_PCS;
import com.is3102.EntityClass.ICNP_Code;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;

import java.util.*;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ashish
 */
@Stateless
public class CodingBean implements CodingBeanRemote {

    @PersistenceContext
    EntityManager em;

    public void removeDiagnosis(Long diagnosisId) throws ExistException {

        Diagnosis diagnosis = em.find(Diagnosis.class, diagnosisId);
        if (diagnosis == null) {
            throw new ExistException("No such Diagnosis entitiy exists!");
        }
        em.remove(diagnosis);
        System.out.println("Diagnosis " + diagnosisId);

    }
    //also works as add Diagnosis

    public void addDiagnosis(Long caseId, String display, String description) throws ExistException {
        mCase mcase = em.find(mCase.class, caseId);
        if (mcase == null) {
            System.out.println("Case is null");
            em.flush();
            throw new ExistException("No such case exists!");
        }
        Diagnosis diagnosis = new Diagnosis();
        ICD10_Code code = getCode(display);
        diagnosis.create(code, description);
        System.out.println("diagnosis created");
        mcase.getDiagnosis().add(diagnosis);
        em.persist(mcase);

    }

    public List<Diagnosis> listAllDiagnosis(Long caseId) throws ExistException {

        mCase mcase = em.find(mCase.class, caseId);
        if (mcase == null) {
            em.flush();
            throw new ExistException("No such case exists!");
        }
        return mcase.getDiagnosis();

    }

    public void addCode(String id, String Chapter, String block, String Disease, String name) throws ExistException {
        ICD10_Code code = em.find(ICD10_Code.class, id);
        if (code != null) {
            throw new ExistException("Such a disease code already exists!");
        }

        code = new ICD10_Code();
        code.create(id, Chapter, block, Disease, name);
        em.persist(code);
        System.out.println("ICD 10 Code " + code.getCode() + " " + code.getName() + "added sucessfully!");
    }

    public List<ICD10_Code> listAllCodes() {
        Query q = em.createQuery("SELECT c FROM ICD10_Code c");
        List<ICD10_Code> codeset = new ArrayList<ICD10_Code>();

        for (Object o : q.getResultList()) {
            ICD10_Code code = (ICD10_Code) o;
            codeset.add(code);
        }

        return codeset;
    }

    public List<ICD10_PCS> listAllMedicalProceures() {
        Query q = em.createQuery("SELECT c FROM ICD10_PCS c");
        List<ICD10_PCS> codeset = new ArrayList<ICD10_PCS>();

        for (Object o : q.getResultList()) {
            ICD10_PCS code = (ICD10_PCS) o;
            codeset.add(code);
        }

        return codeset;

    }

    public void addMedicalProcedure(String id, String name, Long price) throws ExistException {
        ICD10_PCS code = em.find(ICD10_PCS.class, id);
        if (code != null) {
            throw new ExistException("Such a procedure code already exists!");
        } else {

            code = new ICD10_PCS();
            code.create(id, name, price);
            em.persist(code);
            System.out.println("ICD 10 Code " + code.getCode() + " " + code.getName() + " added succefully");
        }
    }

    private ICD10_Code getCode(String display) {
        System.out.println("In getCode() description:" + display);

        Query q = em.createQuery("select i from ICD10_Code i where i.display=:param");
        q.setParameter("param", display);
        ICD10_Code code = (ICD10_Code) q.getSingleResult();
        return code;
    }

    public List<Diagnosis> listDiagnoses(String CIN) {
        mCase mcase = em.find(mCase.class, new Long(CIN));
        if (mcase != null) {
            List<Diagnosis> diagnoses = mcase.getDiagnosis();
            System.out.println(diagnoses.size());
            return diagnoses;
        } else {
            return null;
        }
    }

    public void updateDiagnosis(Long diagnosisId, String newDescription) {
        Diagnosis diagnosis = em.find(Diagnosis.class, diagnosisId);
        Date dDate = new Date();
        diagnosis.setDate(dDate);
        diagnosis.setDescription(newDescription);
        em.merge(diagnosis);
    }

    public void addNursingProcedure(String category, String id, String name, Long price) throws ExistException {
        ICNP_Code code = em.find(ICNP_Code.class, id);
        if (code != null) {
            throw new ExistException("Such a procedure code already exists");
        } else {
            code = new ICNP_Code();
            code.create(id, name, category, price);
            em.persist(code);
            System.out.println("ICNP Code " + code.getCode() + " " + code.getName() + " added succefully");

        }
    }

    public List<ICNP_Code> listAllNursingProcedures() {
        Query q = em.createQuery("SELECT c FROM ICNP_Code c");
        List<ICNP_Code> codeset = new ArrayList<ICNP_Code>();

        for (Object o : q.getResultList()) {
            ICNP_Code code = (ICNP_Code) o;
            codeset.add(code);
        }
        return codeset;
    }
    
    @Override
    public void addProcedure(String id, String name, Long price) throws ExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}