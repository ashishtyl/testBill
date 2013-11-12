/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.DrugCatalog;
import com.is3102.EntityClass.LabRadProcedure;
import com.is3102.EntityClass.Medication;
import com.is3102.EntityClass.ServiceCatalog;
import com.is3102.Exception.DeviceException;
import com.is3102.Exception.DrugException;
import com.is3102.Exception.ExistException;
import com.is3102.Exception.ProcedureException;
import java.text.ParseException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Swarit
 */
@Remote
public interface OrderEntryRemote {
    
    public String prescribeMedication(String CIN, String name, Long dosage, int quantity, String details) throws DrugException, Exception;
    public boolean checkDrugOverDose(String name, Long dosage);
    public List<DrugCatalog> displayDrugCatalog();
    public List<ServiceCatalog> displayServiceCatalog();
    public boolean checkProcedureSafety(String CIN, String name);
    public String orderLabRadProcedure(String CIN, String name, int quantity, String details, String appDate) throws ExistException, ProcedureException, ParseException, DeviceException;
    public List<Medication> listMedication(String CIN);
    public List<LabRadProcedure> listLabRadProcedures(String CIN);
    public List<ServiceCatalog> displayServiceCatalog2();
}
