/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Consent;
import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.Finding;
import com.is3102.EntityClass.ICD10_PCS;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import com.is3102.service.DecisionMakingandPlaningRemote;
import java.util.List;
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
public class DecisionMakingandPlaning implements DecisionMakingandPlaningRemote {

    @PersistenceContext()
    EntityManager em;

    public void AddPlanedProcedure(Long CIN, String procedure_code, String procedure_name, String finding, String comments) throws ExistException {
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
        procedure.create(code, procedure_name, finding, comments);
        System.out.println("created procedure ");

        em.persist(procedure);

        mcase.addmedicalProcedure(procedure);
        System.out.println("added procedure");
        procedure.setMcase(mcase);
        System.out.println("set mcase");

        System.out.println("Medical Procedure " + procedure.getId()
                + "added to case " + mcase.getCIN());

        em.persist(mcase);
        em.flush();
    }

    public void GetConsent(Long procedureId, String patient_comment) throws ExistException {
        Medical_Procedure procedure = em.find(Medical_Procedure.class, procedureId);
        if (procedure == null) {
            throw new ExistException("No such procedure exists!");
        }

       procedure.setPateintConsent(patient_comment);

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
}
