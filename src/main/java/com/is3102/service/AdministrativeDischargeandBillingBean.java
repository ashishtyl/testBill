/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;


import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Ashish
 */

@Stateless
public class AdministrativeDischargeandBillingBean implements AdministrativeDischargeandBillingRemote  {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext()
    EntityManager em;
    mCase mcase;
    
    
      public void CalculateBill() throws Exception {  
         
     }
      
      public void setDischargeDate(Long cin)throws ExistException, ParseException {
          Date dateDis = new Date();
          
          
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          Date testDate = sdf.parse("2013-09-27");
          
          mcase = em.find(mCase.class, cin);
           if (mcase == null)
            throw new ExistException("MCASE ID DOES NOT EXIST.");
          mcase.setDateDischarged(dateDis);
                 
          
      }
      
      public Date getDischargeDate(Long cin){
          
          mcase = em.find(mCase.class, cin);
          
          return mcase.getDateDischarged();
      }

}
