/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Ben
 */
@Entity
public class Nursing_Procedure implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long npId;
    @ManyToOne(cascade={CascadeType.PERSIST})
    private  mCase mcase;
    @OneToMany(cascade={CascadeType.ALL})
    private List<ExecutionLog> Executionlogs = new ArrayList<ExecutionLog>();    
    private String comments;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private ICNP_Code procedureCode; 
     
    public Nursing_Procedure(){}
    
    public void create(ICNP_Code code, String comments){
        this.setProcedureCode(code);
        this.setComments(comments);
        Date tmp = new Date();
        this.setDate(tmp);
    }
   
    /**
     * @return the npId
     */
    public Long getNpId() {
        return npId;
    }

    /**
     * @param npId the npId to set
     */
    public void setNpId(Long npId) {
        this.npId = npId;
    }
      
    /**
     * @return the mcase
     */
    public mCase getMcase() {
        return mcase;
    }

    /**
     * @param mcase the mcase to set
     */
    public void setMcase(mCase mcase) {
        this.mcase = mcase;
    }

    /**
     * @return the Executionlogs
     */
    public List<ExecutionLog> getExecutionlogs() {
        return Executionlogs;
    }

    /**
     * @param Executionlogs the Executionlogs to set
     */
    public void setExecutionlogs(List<ExecutionLog> Executionlogs) {
        this.Executionlogs = Executionlogs;
    }
    
    public void addExecutionLog(ExecutionLog log){
        this.getExecutionlogs().add(log);
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
      public ICNP_Code getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(ICNP_Code procedureCode) {
        this.procedureCode = procedureCode;
    }
    
}
