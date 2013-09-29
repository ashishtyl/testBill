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
 * @author Ashish
 */
@Entity(name="ExecutionLog")
public class ExecutionLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @OneToOne
    private Doctor doctor;
    private String execution_comment;
    
    public ExecutionLog(){}
    
    public void create(Doctor doctor, String comment){
        this.setExecution_comment(comment);
        this.setDoctor(doctor);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExecutionLog)) {
            return false;
        }
        ExecutionLog other = (ExecutionLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClass.ExecutionLog[ id=" + id + " ]";
    }

    /**
     * @return the execution_comment
     */
    public String getExecution_comment() {
        return execution_comment;
    }

    /**
     * @param execution_comment the execution_comment to set
     */
    public void setExecution_comment(String execution_comment) {
        this.execution_comment = execution_comment;
    }

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
}
