/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;
import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.LabRadProcedure;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.EntityClass.Medication;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.NoResultException;
/**
 *
 * @author Ben
 */
@Stateful
public class OutpatientViewBean implements OutpatientViewRemote{
   
   @PersistenceContext()
    EntityManager em;
 
    List<mCase> caseList;
    mCase mcase;
    List<Diagnosis> dList;
    Diagnosis d;
    List<Medical_Procedure> mpList;
    Medical_Procedure mp;
    List<Medication> mList;
    Medication m;
    List<LabRadProcedure> lrpList;
    LabRadProcedure lrp;        
    Long Id;
    Patient patient;
 
   public OutpatientViewBean(){}
   
   public List<mCase> ListmCase(String PasswordId) throws ExistException{
        try{
           Query qb = em.createQuery( "SELECT p FROM Patient p WHERE p.passport_NRIC = :PasswordId");
           qb.setParameter("PasswordId", PasswordId);
           patient = (Patient) qb.getSingleResult();
           //test
           if (patient != null)
                System.out.println("Patient Entity is not Null");
           System.out.println(patient.getMcase().size());
           //test
           caseList=patient.getMcase();
           //test
           if (caseList==null)
                System.out.println("caseList is NULL");
           //test
       }catch (NoResultException ex){
           throw new ExistException("No Records Found, Please Check Your NRIC/PASSWORD Number");
       }
        //System.out.println(caseList.size());//manually traversing the relationship
        return caseList;
    }
  
  public List<Medical_Procedure> ListMedical_Procedure(Long CIN, String passwordId)  throws ExistException{
       System.out.println("*Begin to do list procedure*");
      try{
           Query qb = em.createQuery( "SELECT m FROM mcase m WHERE m.CIN = :CIN");
           System.out.println("CIN exists");
           qb.setParameter("CIN", CIN);
           mcase = (mCase) qb.getSingleResult();
           System.out.println(mcase.getPatient().getPassport_NRIC());
          if(mcase.getPatient().getPassport_NRIC().equals(passwordId))
          {
              System.out.println("CIN Match to Patient");
              mcase.getmProcedures().size();
              mpList=mcase.getmProcedures();
              System.out.println("Medical Procedure Exists");
              return mcase.getmProcedures();
          }
          else{
               System.out.println("CIN Not Mactch");
               return null;
          }
             
    }catch (NoResultException ex){
           throw new ExistException("No Records Found, CIN Not Exists or No Medical Procedure yet");
       }
    }
               
   @Override
       public List<Diagnosis> ListDiagnosis(Long CIN, String passwordId) throws ExistException{
             System.out.println("*Begin to do list diagnosis*");
          try{
             Query qb = em.createQuery( "SELECT m FROM mcase m WHERE m.CIN = :CIN");
             qb.setParameter("CIN", CIN);
             mcase = (mCase) qb.getSingleResult();
             if(mcase.getPatient().getPassport_NRIC().equals(passwordId))
                {
                  mcase.getDiagnosis().size();
                  dList=mcase.getDiagnosis();
                  return mcase.getDiagnosis();
                 }
             else{
                  System.out.println("CIN Not Mactch");
                 return null;
             }
           }catch (NoResultException ex){
                 throw new ExistException("No Records Found, CIN Not Exists or No Diagosis yet");
              }
       }
        
        public List<Medication> ListMedication(Long CIN, String passwordId)throws ExistException {
             System.out.println("*Begin to do list medication*");
          try{
             Query qb = em.createQuery( "SELECT m FROM mcase m WHERE m.CIN = :CIN");
             qb.setParameter("CIN", CIN);
             mcase = (mCase) qb.getSingleResult();
             if(mcase.getPatient().getPassport_NRIC().equals(passwordId))
                {
                  mcase.getMedication().size();
                  mList=mcase.getMedication();
                  return mcase.getMedication();
                 }
             else
                 return null;
           }catch (NoResultException ex){
                 throw new ExistException("No Records Found, CIN Not Exists or No Medication yet");
              }
       }
    
      public List<LabRadProcedure> ListLabRadProcedure(Long CIN, String passwordId) throws ExistException{
             System.out.println("*Begin to do list lab/rad procedure*");
          try{
             Query qb = em.createQuery( "SELECT m FROM mcase m WHERE m.CIN = :CIN");
             qb.setParameter("CIN", CIN);
             mcase = (mCase) qb.getSingleResult();
             if(mcase.getPatient().getPassport_NRIC().equals(passwordId))
                {
                  mcase.getLabRadProcedure().size();
                  lrpList=mcase.getLabRadProcedure();
                  return mcase.getLabRadProcedure();
                 }
             else
                 return null;
           }catch (NoResultException ex){
                 throw new ExistException("No Records Found, CIN Not Exists or No Procedure yet");
              }
       }
    

}
