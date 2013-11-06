/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.DrugCatalog;
import com.is3102.EntityClass.Medication;
import com.is3102.EntityClass.POEOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Swarit
 */
@Stateless
public class OrderEntrySessionBean implements OrderEntryRemote{

    @PersistenceContext
    EntityManager em;
    private Medication medication;
    private POEOrder poeOrder;
    private DrugCatalog drugCatalog;

    public OrderEntrySessionBean() {
    }
    
    public void prescribeMedication(String name, Long dosage, int quantity, String details, Long totalPrice) {
        medication = new Medication();
        medication.create(name, dosage, details, totalPrice);
        em.persist(medication);
    }
    
    public List<DrugCatalog> displayDrugCatalog() {
        return null;
    }
    
    public void dummyy() {
        
    }
}
