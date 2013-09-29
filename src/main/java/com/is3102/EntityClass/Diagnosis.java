/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Ben
 */
@Entity
public class Diagnosis implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long diagnosisId;
   
    private String description;
   @OneToOne(cascade={CascadeType.PERSIST})
    private ICD10_Code code;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    public Diagnosis(){}
    
    public void create (ICD10_Code code, String description){
        Date date = new Date();
        this.setDate(date);
        this.setDescription(description);
        this.setCode(code);
        
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Long getId() {
        return diagnosisId;
    }
    
    public void setId(Long id) {
        this.diagnosisId = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public ICD10_Code getCode(){
        return code;
    }
    
    public void setCode(ICD10_Code code){
        this.code=code;
    }
    
}
