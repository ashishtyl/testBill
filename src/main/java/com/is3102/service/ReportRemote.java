/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.LabRadProcedure;
import com.is3102.EntityClass.Report;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ben
 */
@Remote
public interface ReportRemote {
    public List<mCase> ListmCase(String PasswordId) throws ExistException;
    public List<LabRadProcedure> ListLabRadProcedure(Long CIN, String passwordId) throws ExistException;
    public Long addReport(Long labRadProcedureId,String name, String reportContents, String requestingPhysician, Date reportDate ) throws ExistException;
    public List<Report> checkReport(Long CIN) throws ExistException;
    public List<Report> listReport();   
}
