/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.DrugCatalog;
import com.is3102.EntityClass.Medication;
import com.is3102.EntityClass.POEOrder;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.DrugException;
import com.is3102.Exception.ExistException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Swarit
 */
@Stateless
public class OrderEntrySessionBean implements OrderEntryRemote {

    @PersistenceContext
    EntityManager em;
    //private Medication medication;
    //private POEOrder poeOrder;
    List<Medication> medications = new ArrayList<Medication>();

    public OrderEntrySessionBean() {
    }

    public String prescribeMedication(String CIN, String name, Long dosage, int quantity, String details, double unitPrice) throws DrugException, Exception {
        mCase mcase = em.find(mCase.class, new Long(CIN));
        if (mcase == null) {
            em.clear();
            throw new ExistException("CASE DOES NOT EXIST");
        } else {
            Medication medication = new Medication();
            double totalPrice = quantity * unitPrice;
            if (!checkDrugOverDose(name, dosage)) {
                POEOrder order = new POEOrder();
                medication.create(name, dosage, quantity, details, totalPrice);
                mcase.getMedication().add(medication);
                //medications.add(medication);
                //mcase.setMedication(medications);
                order.setMedication(medication);
                em.persist(order);
                em.persist(medication);
                em.persist(mcase);
            } else {
                throw new DrugException("Drug Overdose!");
            }
            return (medication.getName());
        }
    }

    public boolean checkDrugOverDose(String name, Long dosage) {
        Query q = em.createQuery("SELECT dc FROM DrugCatalog dc WHERE dc.name = :name");
        q.setParameter("name", name);
        DrugCatalog drugCatalog = (DrugCatalog) q.getSingleResult();
        if (dosage > drugCatalog.getRecommendedDosage()) {
            return true;
        }
        return false;
    }

    public List<DrugCatalog> displayDrugCatalog() {
        Query qb = em.createQuery("SELECT dc FROM DrugCatalog dc");
        List<DrugCatalog> drugCatalog = qb.getResultList();
        System.out.println(drugCatalog.size());
        return drugCatalog;
    }
}
