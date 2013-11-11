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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long npId;
    private ICNP_Code procedureCode;
    private String comments;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private mCase mcase;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<ExecutionLog> Executionlogs = new ArrayList<ExecutionLog>();

    public Nursing_Procedure() {
    }

    /*public void create(String name, String code, String comments) {
        this.setCode(code);
        this.setName(name);
    }

    public void create(ICNP_Code code, String comments) {
        this.setProcedureCode(code);
         >>> >>> > branch 'master'
        of https
        ://github.com/Swarit3102/IS3102Final.git
        this.setComments(comments);
        Date tmp = new Date();
        this.setDate(tmp);
        this.setPatientConsent("NULL");
    }*/

    public Long getNpId() {
        return npId;
    }

    public void setNpId(Long npId) {
        this.npId = npId;
    }

    public mCase getMcase() {
        return mcase;
    }

    public void setMcase(mCase mcase) {
        this.mcase = mcase;
    }

    public List<ExecutionLog> getExecutionlogs() {
        return Executionlogs;
    }

    public void setExecutionlogs(List<ExecutionLog> Executionlogs) {
        this.Executionlogs = Executionlogs;
    }

    public void addExecutionLog(ExecutionLog log) {
        this.getExecutionlogs().add(log);
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDate() {
        return date;
    }

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
