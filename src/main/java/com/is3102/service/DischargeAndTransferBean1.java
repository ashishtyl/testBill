/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.DischargeSummary;
import com.is3102.EntityClass.Medical_Anamnesis;
import com.is3102.EntityClass.Transfer;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.CaseException;
import com.is3102.Exception.ExistException;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ben
 */
@Stateful
public class DischargeAndTransferBean1 implements DischargeAndTransferBean1Remote {

   @PersistenceContext()
    EntityManager em;
    DischargeSummary dischargeSummary;
    Transfer transfer;
     mCase mcase;

    //list testing
    List<DischargeSummary> dischargeList;
    List<Transfer> transferList;
    //list testing
    
    public DischargeAndTransferBean1(){}
     


    private mCase getCaseInfo(Long caseId)throws CaseException{
          mcase =em.find(mCase.class, caseId);
        if (mcase==null){
            throw new CaseException("Case does not exsit");
        }
        return mcase;
    }
 
   @Override
    public void generateDischargeSummary(Long caseId, String diagnosis, String findings, 
              String recommendation, String patientState, String medicalProcedure) throws CaseException {
       System.out.println("test discharge generating function 2");
       mcase=getCaseInfo(caseId);
        dischargeSummary= new DischargeSummary();

        dischargeSummary.create(diagnosis, findings, recommendation, patientState,
             medicalProcedure);
       System.out.println("test discharge generating function 3");
       mcase.setDischargeSummary(dischargeSummary);
      em.persist(mcase);
       em.flush();
       System.out.println("test discharge generating function 4");
    }
    
   @Override
    public DischargeSummary getDischargeSummary(Long dischargeId) throws ExistException{
       dischargeSummary = em.find(DischargeSummary.class, dischargeId);
       if (dischargeSummary == null) {
           throw new ExistException("DischargeSummary does not exsit");
       }
          return em.find(DischargeSummary.class, dischargeId);
    }
   
   @Override
    public void deleteDischargeSummary(Long dischargeId) throws ExistException{
       dischargeSummary = em.find(DischargeSummary.class, dischargeId);
       if (dischargeSummary == null) {
           throw new ExistException("DischargeSummary does not exsit");
       }
           em.remove(dischargeSummary);
    }
    //list testing
    public List<DischargeSummary> ListDischargeSummary() {
        dischargeList = new ArrayList<DischargeSummary>();
        try {
            Query q = em.createQuery("SELECT d FROM DischargeSummary d");

            for (Object ob: q.getResultList()) {
                dischargeSummary = (DischargeSummary)ob;
                dischargeList.add(dischargeSummary);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return dischargeList;
    }
    //list testing



    //lack of query sentence to database?
   @Override
    public void recodeTransfer(Long caseId, String referDoctor, String reason) throws CaseException{
       mcase = getCaseInfo(caseId);
        transfer=new Transfer();

        transfer.create(referDoctor,reason);

        mcase.setTransfer(transfer);
        em.persist(mcase);
       em.flush();
    }

   @Override
    public Transfer getTransfer(Long transferId) throws ExistException{
       transfer = em.find(Transfer.class, transferId);
       if (transfer == null) {
           throw new ExistException("Transfer does not exsit");
       }
           return em.find(Transfer.class,transferId);
    }
   
   @Override
    public void deleteTransfer(Long transferId)  throws ExistException{
       transfer = em.find(Transfer.class, transferId);
       if (transfer == null) {
           throw new ExistException("Transfer does not exsit");
       }
           em.remove(transfer);
    }

    //list testing
    public List<Transfer> ListTransfer() {
        transferList = new ArrayList<Transfer>();
        try {
            Query q = em.createQuery("SELECT t FROM Transfer t");

            for (Object ob: q.getResultList()) {
                transfer = (Transfer)ob;
                transferList.add(transfer);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return transferList;
    }
    //list testing
   

}
