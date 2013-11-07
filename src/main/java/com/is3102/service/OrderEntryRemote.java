/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.DrugCatalog;
import com.is3102.Exception.DrugException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Swarit
 */
@Remote
public interface OrderEntryRemote {
    
    public String prescribeMedication(String CIN, String name, Long dosage, int quantity, String details, double unitPrice)  throws DrugException, Exception;
    
    public List<DrugCatalog> displayDrugCatalog();
}
