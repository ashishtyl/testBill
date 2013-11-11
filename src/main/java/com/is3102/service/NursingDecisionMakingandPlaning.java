/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.is3102.service;

import com.is3102.EntityClass.Nursing_Procedure;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashish
 */
@Stateless
public class NursingDecisionMakingandPlaning implements NursingDecisionMakingandPlaningRemote{

    @PersistenceContext()
    EntityManager em;

    public void AddPlanedProcedure(Long CIN, String procedure_code, String procedure_name, String comments) throws ExistException {
        System.out.println("In N-DMP EJB: AddPlannedProcedure");
        mCase mcase = em.find(mCase.class, CIN);
       
        if (mcase == null) {
            System.out.println("Case not found!");
            throw new ExistException("No such case exists");
            
        }

        System.out.println("Mcase found");
        Nursing_Procedure procedure = new Nursing_Procedure();
        System.out.println("created procedure");
        procedure.create(procedure_code, procedure_name, comments);
        System.out.println("created procedure 2");

        em.persist(procedure);

        mcase.addnProcedure(procedure);
        System.out.println("added procedure");
        procedure.setMcase(mcase);
        System.out.println("set mcase");

        System.out.println("Nursing Procedure " + procedure.getNpId()
                + "added to case " + mcase.getCIN());

        em.persist(mcase);
        em.flush();
    }

    public List<Nursing_Procedure> RetrieveNursingPlaning(Long CIN) throws ExistException {
        
        System.out.println("In N-DMP EJB: Retrieve Nursing Plan");
        List<Nursing_Procedure> procedures;

        mCase mcase = em.find(mCase.class, CIN);
        if (mcase == null) {
            System.out.println("No such case exists!");
            throw new ExistException("No such case exists!");            
        }
        
        System.out.println("mCase found");
        procedures = mcase.getnProcedures();
        System.out.println("Returning procedures");
        return procedures;
    }
}
