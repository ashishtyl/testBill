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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String prescribeMedication(String CIN, String name, Long dosage, int quantity, String details) throws DrugException, Exception {
        mCase mcase = em.find(mCase.class, new Long(CIN));
        if (mcase == null) {
            em.clear();
            throw new ExistException("CASE DOES NOT EXIST");
        } else {
            Medication medication = new Medication();
            Query q = em.createQuery("SELECT dc FROM DrugCatalog dc WHERE dc.name = :name");
            q.setParameter("name", name);
            DrugCatalog drug = (DrugCatalog) q.getSingleResult();
            double unitPrice = drug.getPrice();
            double totalPrice = quantity * unitPrice;
            if (!checkDrugOverDose(name, dosage)) {
                if (checkDrugInteraction(CIN, name)) {
                    throw new DrugException("Patient is already using a medicine which reacts with " + name + "!");
                } else {
                    //if (!checkDrugOverDose(name, dosage)) {
                    POEOrder order = new POEOrder();
                    medication.create(name, dosage, quantity, details, totalPrice);
                    mcase.getMedication().add(medication);
                    medication.setMcase(mcase);
                    order.setMedication(medication);
                    em.persist(order);
                    em.persist(medication);
                    em.persist(mcase);
                    em.flush();
                }
            } else {
                throw new DrugException("Drug Overdose!");
            }
            return (medication.getName());
        }
    }

    public boolean checkDrugOverDose(String name, Long dosage) {
        Query q = em.createQuery("SELECT dc FROM DrugCatalog dc WHERE dc.name = :name");
        q.setParameter("name", name);
        DrugCatalog drug = (DrugCatalog) q.getSingleResult();
        if (dosage > drug.getRecommendedDosage()) {
            System.out.println("OVERDOSE!");
            return true;
        }
        return false;
    }

    public boolean checkDrugInteraction(String CIN, String name) {
        Query q = em.createQuery("SELECT dc FROM DrugCatalog dc WHERE dc.name = :name");
        q.setParameter("name", name);
        DrugCatalog drug = (DrugCatalog) q.getSingleResult();
        String category = drug.getCategory();
        mCase mcase = em.find(mCase.class, new Long(CIN));
        List<Medication> medication = mcase.getMedication();
        for (Medication mc : medication) {
            String drugName = mc.getName();
            Query qdc = em.createQuery("SELECT dc FROM DrugCatalog dc WHERE dc.name = :name");
            qdc.setParameter("name", drugName);
            DrugCatalog drugc = (DrugCatalog) qdc.getSingleResult();
            if (category.equals(drugc.getCategory())) {
                System.out.println("DRUG-DRUG INTERACTION!");
                return true;
            }
        }
        return false;
    }

    public boolean checkDrugAllergy() {
        return true;
    }

    public List<DrugCatalog> displayDrugCatalog() {
        Query qdc = em.createQuery("SELECT dc FROM DrugCatalog dc");
        List<DrugCatalog> drugCatalog = qdc.getResultList();
        System.out.println(drugCatalog.size());
        return drugCatalog;
    }
}
