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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Swarit
 */
@Entity(name = "AppointmentProcedure")
public class AppointmentProcedure implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @OneToOne(mappedBy = "aProcedure")
    private POEOrder order;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date appDate;

    public void create(Date appDate){
        this.setAppDate(appDate);
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public POEOrder getOrder() {
        return order;
    }

    public void setOrder(POEOrder order) {
        this.order = order;
    }

    /**
     * @return the appDate
     */
    public Date getAppDate() {
        return appDate;
    }

    /**
     * @param appDate the appDate to set
     */
    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }
}
