/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Bryan Arnold
 */
@Entity
public class Device implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String deviceType;
    
    @OneToMany(cascade={CascadeType.ALL})
    private List<AppointmentProcedure> procedures = new ArrayList<AppointmentProcedure>();
    
    public void Device(){}
    
    public void create(String deviceType){
        this.setDeviceType(deviceType);
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
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.is3102.EntityClass.Device[ id=" + id + " ]";
    }

    /**
     * @return the deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType the deviceType to set
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return the procedures
     */
    public List<AppointmentProcedure> getProcedures() {
        return procedures;
    }

    /**
     * @param procedures the procedures to set
     */
    public void setProcedures(List<AppointmentProcedure> procedures) {
        this.procedures = procedures;
    }
    
    public void addProcedure(AppointmentProcedure procedure){
        this.procedures.add(procedure);
    }
    
}
