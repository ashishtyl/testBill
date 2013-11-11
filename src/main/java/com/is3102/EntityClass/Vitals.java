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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ashish
 */
@Entity
public class Vitals implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private mCase mCase;
    private String bloodPressure;
    private String temperature;
    private String heartRate;
    private String spO2;
    private String glucoseLevel;
    private String respiratoryRate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date t_stamp;  
    
    public Vitals(){}
    
    public void create(mCase mcase, String bp, String tmp, String HR, String spO2, String glucose, String rRate){
        this.setmCase(mCase);
        this.setBloodPressure(bp);
        this.setTemperature(tmp);
        this.setHeartRate(HR);
        this.setSpO2(spO2);
        this.setGlucoseLevel(glucose);
        this.setHeartRate(rRate);
        Date time = new Date();
        this.setT_stamp(time);
    }

    public mCase getmCase() {
        return mCase;
    }

    public void setmCase(mCase mCase) {
        this.mCase = mCase;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getSpO2() {
        return spO2;
    }

    public void setSpO2(String spO2) {
        this.spO2 = spO2;
    }

    public String getGlucoseLevel() {
        return glucoseLevel;
    }

    public void setGlucoseLevel(String glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
    }

    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(String respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
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
        if (!(object instanceof Vitals)) {
            return false;
        }
        Vitals other = (Vitals) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.is3102.EntityClass.Vitals[ id=" + id + " ]";
    }
    
    
    public Date getT_stamp() {
        return t_stamp;
    }

    public void setT_stamp(Date t_stamp) {
        this.t_stamp = t_stamp;
    }
    
}
