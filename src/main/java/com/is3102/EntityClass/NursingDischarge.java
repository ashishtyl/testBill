/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author A0078597
 */
@Entity
public class NursingDischarge implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String activityLevel;
    private String diet;
    private String woundcare;
    
    @ManyToOne
    Doctor doctor;
    
    public void NusringDischarge(){}
    
    public void create(String activityLevel, String diet, String woundcare){
        this.setActivityLevel(activityLevel);
        this.setDiet(diet);
        this.setWoundcare(woundcare);
    }  

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getWoundcare() {
        return woundcare;
    }

    public void setWoundcare(String woundcare) {
        this.woundcare = woundcare;
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
        if (!(object instanceof NursingDischarge)) {
            return false;
        }
        NursingDischarge other = (NursingDischarge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.is3102.EntityClass.NursingDischarge[ id=" + id + " ]";
    }
    
}
