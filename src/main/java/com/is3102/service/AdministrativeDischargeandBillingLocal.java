/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.Exception.ExistException;
import java.text.ParseException;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Ashish
 */
@Local
public interface AdministrativeDischargeandBillingLocal
{
    
    public void setDischargeDate(Long cin)throws ExistException, ParseException;
    public Date getDischargeDate(Long cin);
    
}
