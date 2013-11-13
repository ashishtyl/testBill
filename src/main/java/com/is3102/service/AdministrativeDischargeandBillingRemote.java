/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Transactions;
import com.is3102.Exception.ExistException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ashish
 */
@Remote
public interface AdministrativeDischargeandBillingRemote {
    
    public List<Transactions> CalculateBill(Long CIN) throws ExistException;
    public void setDischargeDate(Long cin)throws ExistException, ParseException;
    public Date getDischargeDate(Long cin);
    
}
