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
import javax.persistence.Temporal;

/**
 *
 * @author Ben
 */
@Entity(name = "Medical_Procedure")
public class Medical_Procedure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mpId;
    private String comments;
    private String patientConsent;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private mCase mcase;
    //@OneToOne(cascade = {CascadeType.PERSIST})
    //private Finding finding;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<ExecutionLog> Executionlogs = new ArrayList<ExecutionLog>();
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private ICD10_PCS procedureCode;
    
    private String category;

    public Medical_Procedure() {
    }

    public void create(ICD10_PCS procedureCode, String comments) {
        Date date = new Date();
        this.setDate(date);
        this.setProcedureCode(procedureCode);
        this.setComments(comments);
        this.setPatientConsent("NULL");
    }

    public Long getMpId() {
        return mpId;
    }

    public void setMpId(Long mpId) {
        this.mpId = mpId;
    }

    public ICD10_PCS getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(ICD10_PCS procedureCode) {
        this.procedureCode = procedureCode;
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
        this.Executionlogs.add(log);
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

    public String getPatientConsent() {
        return patientConsent;
    }

    public void setPatientConsent(String patientConsent) {
        this.patientConsent = patientConsent;
    }
}
