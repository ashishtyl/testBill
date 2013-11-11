/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.ICD10_PCS;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ashish
 */
@Stateless
public class DecisionMakingandPlaning implements DecisionMakingandPlaningRemote {

    @PersistenceContext()
    EntityManager em;

    public void AddPlanedProcedure(Long CIN, String procedure_code, String procedure_name,  String comments) throws ExistException {
        System.out.println("In DMP Bean AddPlannedProcedure");
        mCase mcase = em.find(mCase.class, CIN);
        if (mcase == null) {
            throw new ExistException("No such case exists");
        }
        System.out.println("Mcase found");
        Medical_Procedure procedure = new Medical_Procedure();
        System.out.println("creating procedure");
        String desc = procedure_code + " " + procedure_name;
        System.out.print("searching for ICD10_PCS code");
        ICD10_PCS code = getCode(desc);
        System.out.print("ICD10_PCS code object created");
        procedure.create(code, comments);
        System.out.println("created procedure ");
        mcase.getmProcedures().add(procedure);
        //mcase.addmedicalProcedure(procedure);
        System.out.println("added procedure");
        procedure.setMcase(mcase);
        System.out.println("set mcase");
        em.persist(procedure);
        em.persist(mcase);
        System.out.println("Medical Procedure " + procedure.getMpId() + "added to case " + mcase.getCIN());
        em.flush();
    }

    public void GetConsent(Long procedureId, String patient_comment) throws ExistException {
        Medical_Procedure procedure = em.find(Medical_Procedure.class, procedureId);
        if (procedure == null) {
            throw new ExistException("No such procedure exists!");
        }
        procedure.setPatientConsent(patient_comment);
    }

    public List<Medical_Procedure> RetrieveCarePlaning(Long CIN) throws ExistException {
        System.out.println("In DNP EJB");
        List<Medical_Procedure> procedures;

        mCase mcase = em.find(mCase.class, CIN);
        if (mcase == null) {
            throw new ExistException("No such case exists!");
        }
        System.out.println("mCase found");
        procedures = mcase.getmProcedures();
        System.out.println("Returning procedures");
        return procedures;
    }

    @Override
    public void RetrieveMedicalKnowledge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ICD10_PCS getCode(String description) {
        System.out.println("In getCode() description:" + description);

        Query q = em.createQuery("select i from ICD10_PCS i where i.display=:param");
        q.setParameter("param", description);
        ICD10_PCS code = (ICD10_PCS) q.getSingleResult();
        return code;
    }

    public List<Medical_Procedure> listProcedures(String CIN) {
        mCase mcase = em.find(mCase.class, new Long(CIN));
        System.out.println(CIN);
        if (mcase != null) {
            List<Medical_Procedure> procedures = mcase.getmProcedures();
            System.out.println(procedures.size());
            return procedures;
        } else {
            return null;
        }
    }

    public void updateProcedure(Long procedureId, String newComments, String newConsent) {
        Medical_Procedure procedure = em.find(Medical_Procedure.class, procedureId);
        Date pDate = new Date();
        procedure.setDate(pDate);
        procedure.setComments(newComments);
        procedure.setPatientConsent(newConsent);
        em.merge(procedure);
    }
}