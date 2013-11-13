/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;


import com.is3102.EntityClass.LabRadProcedure;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.Report;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Ben
 */
@Stateful
public class ReportBean implements ReportRemote {
    
     @PersistenceContext()
     EntityManager em;
     
     Patient patient;
     Report report;
     mCase mcase;
     List<mCase> caseList;
     List<LabRadProcedure> lrpList;
     
     public ReportBean(){}
     
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
                 throw new ExistException("No Records Found, CIN Not Exists");
              }
       }
    
     
     public Long addReport(Long labRadProcedureId, String name, String reportContents, String requestingPhysician, Date reportDate ) throws ExistException{
          try{  
         report = new Report();
         LabRadProcedure lrprocedure=em.find(LabRadProcedure.class, new Long(labRadProcedureId));
         if (lrprocedure==null)
             return null;
         if (lrprocedure.getReport()==null){
             report.create(name,reportContents,requestingPhysician,reportDate);
             lrprocedure.setReport(report);
             em.persist(report);
             return report.getReportId();}
         else
             return null;
          }catch (NoResultException ex){
           throw new ExistException("Report Not Created, No Procedure Found!");
       }
    }
    
     public List<Report> checkReport(Long CIN) throws ExistException{
         try{
           List<Report> reportList= new ArrayList<Report>();
           LabRadProcedure lrprocedure;
           Query qb = em.createQuery( "SELECT m FROM mcase m WHERE m.CIN = :CIN");
           System.out.println("CIN exists");
           qb.setParameter("CIN", CIN);
           mCase mcase = (mCase) qb.getSingleResult();
           List<LabRadProcedure> list= mcase.getLabRadProcedure();
           for(int i=0; i<list.size(); i++){
               lrprocedure=list.get(i);
               reportList.add(lrprocedure.getReport());    
           }
           return reportList;
            }catch (NoResultException ex){
           throw new ExistException("No Procedure Found!");
       }
    }
     
     public List<Report> listReport(){
         
         List<Report> reportList= new ArrayList<Report>();
        try {
            Query qb = em.createQuery("SELECT r FROM Report r");
            for (Object ob : qb.getResultList()) {
                report = (Report) ob;
                reportList.add(report);
                 return reportList;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return reportList;
    }
         

     
}
