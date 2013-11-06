/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.DrugCatalog;
import com.is3102.EntityClass.Medication;
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
public class OrderEntrySessionBean implements OrderEntryRemote{

    @PersistenceContext
    EntityManager em;
    private Medication medication;

    public OrderEntrySessionBean() {
    }
    
    public void prescribeMedication(String name, String dosage, String details) {
        medication = new Medication();
        medication.create(name, dosage, details);
        em.persist(medication);
    }
    
    public List<DrugCatalog> displayDrugCatalog() {
        return null;
        
    }
}
