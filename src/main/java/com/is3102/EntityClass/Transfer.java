/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Ben
 */
@Entity
public class Transfer implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Transferid;
    private String referDoctor;
    private String reason;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    //need to add more attributes?
    //how to auto generate date?
    
    public Transfer(){}
    
    public void create(String referDoctor, String reason){
        this.setreferDoctor(referDoctor);
        this.setReason(reason);
    }

    public Long getId() {
        return Transferid;
    }

    public void setId(Long id) {
        this.Transferid = id;
    }
    
    public String getreferDoctor() {
        return referDoctor;
    }

    public void setreferDoctor(String referDoctor) {
        this.referDoctor = referDoctor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    
}
