/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;
import com.is3102.EntityClass.Diagnosis;
import com.is3102.EntityClass.LabRadProcedure;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.EntityClass.Medication;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author Ben
 */

@Remote
public interface OutpatientViewRemote {
    
    public List<mCase> ListmCase(String PasswordId)throws ExistException;
    public List<Medical_Procedure> ListMedical_Procedure(Long CIN, String passwordId)throws ExistException;
    public List<Diagnosis> ListDiagnosis(Long CIN, String passwordId)throws ExistException;
    public List<Medication> ListMedication(Long CIN, String passwordId)throws ExistException;
    public List<LabRadProcedure> ListLabRadProcedure(Long CIN, String passwordId)throws ExistException;
    
}
