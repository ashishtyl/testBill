/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Bill;
import com.is3102.EntityClass.LabRadProcedure;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.EntityClass.Medication;
import com.is3102.EntityClass.Nursing_Procedure;
import com.is3102.EntityClass.Transactions;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashish
 */
@Stateless
public class AdministrativeDischargeandBillingBean implements AdministrativeDischargeandBillingRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext()
    EntityManager em;
    mCase mcase;

    public void CalculateBill(Long CIN) throws ExistException {
        mcase = em.find(mCase.class, CIN);
        if(mcase==null){
            throw new ExistException("Case not found!");
        }
        List<Transactions> transactions = new ArrayList<Transactions>();
        System.out.println("Add Medical procedures total");
        transactions.addAll(medicalTotal(mcase));
        System.out.println("Add Nursing procedures total");
        transactions.addAll(nursingTotal(mcase));
        System.out.println("Add LabRad total");
        transactions.addAll(labradTotal(mcase));
        System.out.println("Add drugs total");
        transactions.addAll(drugsTotal(mcase));
        System.out.println("All transactions objects added");
        Bill bill = new Bill();
        System.out.println("Creating bill");
        bill.create(mcase);
        System.out.println("Set transactions");
        bill.setTransctions(transactions);
        System.out.println("calculate total");
        bill.claculateTotal();
        bill.setDiscount(calculateDiscount(mcase));
        bill.setNetTotal();
        System.out.println("persist bill");
        em.persist(bill);
    }

    public void setDischargeDate(Long cin) throws ExistException, ParseException {
        Date dateDis = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = sdf.parse("2013-09-27");

        mcase = em.find(mCase.class, cin);
        if (mcase == null) {
            throw new ExistException("MCASE ID DOES NOT EXIST.");
        }
        mcase.setDateDischarged(dateDis);

    }

    public Date getDischargeDate(Long cin) {

        mcase = em.find(mCase.class, cin);

        return mcase.getDateDischarged();
    }

    private List<Transactions> medicalTotal(mCase mcase) {
        List<Transactions> medicalTransactions = new ArrayList<Transactions>();
        List<Medical_Procedure> mprocedures = mcase.getmProcedures();
        for (Medical_Procedure mp : mprocedures) {
            System.out.println("Adding " + mp.getProcedureCode().getDisplay());
            Transactions t = new Transactions();
            t.create(mcase.getCIN(), mp.getProcedureCode().getName(), (double) mp.getProcedureCode().getPrice());
            em.persist(t);
            medicalTransactions.add(t);

        }
        return medicalTransactions;
    }

    private List<Transactions> nursingTotal(mCase mcase) {
        List<Transactions> nursingTransactions = new ArrayList<Transactions>();
        List<Nursing_Procedure> nprocedures = mcase.getnProcedures();
        for (Nursing_Procedure np : nprocedures) {
            System.out.println("Adding " + np.getProcedureCode().getName());
            Transactions t = new Transactions();
            t.create(mcase.getCIN(), np.getProcedureCode().getName(), (double) np.getProcedureCode().getPrice());
            em.persist(t);
            nursingTransactions.add(t);
        }
        return nursingTransactions;
    }

    private List<Transactions> drugsTotal(mCase mcase) {
        List<Transactions> drugsTransactions = new ArrayList<Transactions>();
        List<Medication> medication = mcase.getMedication();
        for (Medication med : medication) {
            System.out.println("Adding " + med.getName());
            Transactions t = new Transactions();
            t.create(mcase.getCIN(), med.getName(), (double) med.getTotalPrice());
            em.persist(t);
            drugsTransactions.add(t);
        }
        return drugsTransactions;
    }

    private List<Transactions> labradTotal(mCase mcase) {
        List<Transactions> labradTransactions = new ArrayList<Transactions>();
        List<LabRadProcedure> labrad = mcase.getLabRadProcedure();
        for (LabRadProcedure lbr : labrad) {

            Transactions t = new Transactions();
            t.create(mcase.getCIN(), lbr.getName(), (double) lbr.getTotalPrice());
            em.persist(t);
            labradTransactions.add(t);
        }
        return labradTransactions;
    }

    private double calculateDiscount(mCase mcase) {
        double disc = 0;
        String bedNo = mcase.getBed().getRoomNo();

        if ((bedNo.compareTo("1") == 0) || (bedNo.compareTo("2") == 0)) {
            System.out.println("Ward discount offerred");
            disc += 0.20;
        }
        if (mcase.getPatient().getPatientAge() < 18 || mcase.getPatient().getPatientAge() > 65) {
            System.out.println("Age discount offered");
            disc += 0.15;
        }
        System.out.println("total disocunt "+disc);
        return disc;
    }
}
