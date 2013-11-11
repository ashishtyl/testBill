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
import java.util.ArrayList;
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
    
    int i=0;
    
    List<Diagnosis> dList;
    Diagnosis d;
    List<Medical_Procedure> mpList;
    Medical_Procedure mp;
    List<Medication> mList;
    Medication m;
    List<LabRadProcedure> lrpList;
    LabRadProcedure lrp;
    
    List<Long> CINList=new ArrayList<Long>();
    Long temporyCIN;
            
    List<Long> temporyList;
    Long temporyValue;
    Long Id;
    Patient patient;
 
   public OutpatientViewBean(){}
   
  // private Long getPatientId(String PasswordId){
      // Query qb = em.createQuery( "SELECT patientId FROM Patient WHERE passport_NRIC = PasswordId");
       //for (Object ob : qb.getResultList()) {
            //    Id = (Long) ob;}
     //  return Id;
//   }
   /* private Long getPatientId(String PasswordId) throws ExistException{
       try{
           Query qb = em.createQuery( "SELECT p FROM Patient p WHERE p.passport_NRIC = :PasswordId");
           qb.setParameter("PasswordId", PasswordId);
           patient = (Patient) qb.getSingleResult();
           if (patient != null)
               return patient.getPatientId();
       }catch (NoResultException ex){
           throw new ExistException("No Records Found, Please Check Your NRIC/PASSWORD Number");
       }
       return patient.getPatientId();
 }*/
   
   
   
 /*  private int testCase(Long CIN){
       System.out.println("#3 for advance");
       for (mCase tCase : caseList) {
		temporyCIN=tCase.getCIN();
                CINList.add(temporyCIN);
               
	}
       for (Long displayedCIN : CINList) {
		if (displayedCIN==CIN)
                    i=1;
	}
        return i;
   }*/
  

   public List<mCase> ListmCase(String PasswordId) throws ExistException{
       //Long patientId = getPatientId(PasswordId);
       // caseList = new ArrayList<mCase>();
       System.out.println("***");
        try{
           Query qb = em.createQuery( "SELECT p FROM Patient p WHERE p.passport_NRIC = :PasswordId");
           qb.setParameter("PasswordId", PasswordId);
           patient = (Patient) qb.getSingleResult();
             System.out.println(patient.getName());
           if (patient != null)
                System.out.println("test1");
           patient.getmCases().size(); //manually traversing the relationship
           caseList=patient.getmCases();
           for (Object o : caseList) {
		mcase=(mCase)o;
                temporyCIN=mcase.getCIN();
                System.out.println(temporyCIN);
                
                CINList.add(temporyCIN);//NullPointerException
	}
            System.out.println("*****Test CIN****");
            System.out.println(CINList.get(0));
            System.out.println("test2");
               if (caseList==null)
                System.out.println("list is NULL");
       }catch (NoResultException ex){
           throw new ExistException("No Records Found, Please Check Your NRIC/PASSWORD Number");
       }
          System.out.println("*********");
          System.out.println(caseList.size());
        return caseList;
    }

    /*public List<Medical_Procedure> ListMedical_Procedure(Long CIN)  throws ExistException{
        if (testCase(CIN)==0)
            throw new ExistException("CIN not match to the value in the list");
        temporyList=new ArrayList<Long>();
        try {
            //search joint table
            //Query qb = em.createQuery("SELECT MPROCEDURE_MPID FROM MCASE_MEDICAL_PROCEDURE m where m.MCASE_CIN = CIN");
            Query qb = em.createQuery("SELECT Medical_Procedure_mpId FROM MCASE_MEDICAL_PROCEDURE m where m.mcase_CIN = CIN");
                for (Object ob : qb.getResultList()) {
                temporyValue = (Long) ob;
                //search Medical_Procedure Table
                  Query qb2 = em.createQuery("SELECT m FROM Medical_Procedure m where m.mpId =  temporyValue");
                   for (Object ob2 : qb2.getResultList()) {
                       mp = (Medical_Procedure) ob;
                       mpList.add(mp);
                   }
              }
             
        } catch (Exception ex) {
            ex.printStackTrace();
        }     
         return mpList;
        
    }*/
  public List<Medical_Procedure> ListMedical_Procedure(Long CIN)  throws ExistException{
       System.out.println("*^^^^^^^^^^^^^^^*");
      try{
          if(CINList.isEmpty()==true)
               System.out.println("no CIN");
          for (Object o : CINList) {
               Long displayedCIN=(Long)o;
                System.out.println("*");
                 System.out.println("displayedCIN");
                   System.out.println("*");
		if (displayedCIN==CIN)
                    i=1;
	}
            System.out.println("***qqqqqqqqqqqqqqqqqq");
       //if (i==0)
            //throw new ExistException("CIN not match to the value in the list");
       System.out.println("***");
       i=0;
       // try{
           Query qb = em.createQuery( "SELECT m FROM mcase m WHERE m.CIN = :CIN");
           qb.setParameter("CIN", CIN);
           mcase = (mCase) qb.getSingleResult();
             System.out.println("*****");
           if (mcase != null)
                System.out.println("test1");
           //patient.getmCases().size(); //manually traversing the relationship
           mpList=mcase.getmProcedures();
            System.out.println("test2");
               if (mpList==null)
                System.out.println("*******");
       }catch (NoResultException ex){
           throw new ExistException("No Records Found, No Medical Procedure yet");
       }
          System.out.println("*********");
        return mpList;
    }
   
    
        public List<Diagnosis> ListDiagnosis(Long CIN) throws ExistException{
         //if (testCase(CIN)==0)
          //  throw new ExistException("CIN not match to the value in the list");
        temporyList=new ArrayList<Long>();
        try {
            //search joint table
            //Query qb = em.createQuery("SELECT DIAGNOSIS_DIAGNOSISID FROM MCASE_DIAGNOSIS m where m.MCASE_CIN = CIN");
            Query qb = em.createQuery("SELECT Diagnosis_diagnosisId FROM MCASE_MEDICAL_DIAGNOSIS m where m.mcase_CIN = CIN");
                for (Object ob : qb.getResultList()) {
                temporyValue = (Long) ob;
                //search Medical_Procedure Table
                  Query qb2 = em.createQuery("SELECT m FROM Diagnosis m where m.diagnosisId =  temporyValue");
                   for (Object ob2 : qb2.getResultList()) {
                       d = (Diagnosis) ob;
                       dList.add(d);
                   }
              }
             
        } catch (Exception ex) {
            ex.printStackTrace();
        }     
         return dList;
        
    }
        
        public List<Medication> ListMedication(Long CIN)throws ExistException {
        //if (testCase(CIN)==0)
           // throw new ExistException("CIN not match to the value in the list");
        temporyList=new ArrayList<Long>();
        try {
             //search joint table
            //Query qb = em.createQuery("SELECT MEDICATION_MEDID FROM MCASE_MEDICATION m where m.MCASE_CIN = CIN");
            Query qb = em.createQuery("SELECT Medication_medId FROM MCASE_MEDICAL_DIAGNOSIS m where m.mcase_CIN = CIN");
                for (Object ob : qb.getResultList()) {
                temporyValue = (Long) ob;
                //search Medical_Procedure Table
                  Query qb2 = em.createQuery("SELECT m FROM Medication m where m.medId =  temporyValue");
                   for (Object ob2 : qb2.getResultList()) {
                       m = (Medication) ob2;
                       mList.add(m);
                   }
              }
             
        } catch (Exception ex) {
            ex.printStackTrace();
        }     
         return mList;
        
    }
    
      public List<LabRadProcedure> ListLabRadProcedure(Long CIN) throws ExistException{
          // if (testCase(CIN)==0)
            //throw new ExistException("CIN not match to the value in the list");
        temporyList=new ArrayList<Long>();
        try {
             //search joint table
            //Query qb = em.createQuery("SELECT LABRADPROCEDURE_PROCID FROM MCASE_LABRADPROCEDURE m where m.MCASE_CIN = CIN");
            Query qb = em.createQuery("SELECT LabRadProcedure_procId FROM MCASE_MEDICAL_DIAGNOSIS m where m.mcase_CIN = CIN");
                for (Object ob : qb.getResultList()) {
                temporyValue = (Long) ob;
                //search Medical_Procedure Table
                  Query qb2 = em.createQuery("SELECT m FROM LabRadProcedure m where m.procId =  temporyValue");
                   for (Object ob2 : qb2.getResultList()) {
                       lrp = (LabRadProcedure) ob2;
                       lrpList.add(lrp);
                   }
              }
             
        } catch (Exception ex) {
            ex.printStackTrace();
        }     
         return lrpList;
        
    }
    

}
