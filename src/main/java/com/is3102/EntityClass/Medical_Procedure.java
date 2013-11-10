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
@Entity(name = "Medical_Procedure")
public class Medical_Procedure implements Serializable {

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
    private Long mpId;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Consent consent;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private mCase mcase;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Finding finding;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<ExecutionLog> Executionlogs = new ArrayList<ExecutionLog>();
    private String comments;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private ICD10_PCS procedureCode;

    public Medical_Procedure() {
    }

    public void create(ICD10_PCS procedureCode, String description, String findingDescription, String comments) {
        Date date = new Date();
        this.setDate(date);
        Finding finding = new Finding();
        finding.setDescription(description);
        this.setFinding(finding);
        this.setProcedureCode(procedureCode);
        this.setComments(comments);

    }

    public ICD10_PCS getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(ICD10_PCS procedureCode) {
        this.procedureCode = procedureCode;
    }

    public Long getId() {
        return getMpId();
    }

    public void setId(Long id) {
        this.setMpId(id);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getMpId() != null ? getMpId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medical_Procedure)) {
            return false;
        }

        Medical_Procedure other = (Medical_Procedure) object;
        if ((this.getMpId() == null && other.getMpId() != null) || (this.getMpId() != null && !this.mpId.equals(other.mpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClass.Medical_Procedure[ id=" + getMpId() + " ]";
    }

    /**
     * @return the mpId
     */
    public Long getMpId() {
        return mpId;
    }

    /**
     * @param mpId the mpId to set
     */
    public void setMpId(Long mpId) {
        this.mpId = mpId;
    }

    /**
     * @return the consent
     */
    public Consent getConsent() {
        return consent;
    }

    /**
     * @param consent the consent to set
     */
    public void setConsent(Consent consent) {
        this.consent = consent;
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
     * @return the finding
     */
    public Finding getFinding() {
        return finding;
    }

    /**
     * @param finding the finding to set
     */
    public void setFinding(Finding finding) {
        this.finding = finding;
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

    public void addExecutionLog(ExecutionLog log) {
        this.Executionlogs.add(log);
    }

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
}
