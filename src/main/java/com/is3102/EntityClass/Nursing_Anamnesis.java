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

/**
 *
 * @author Ben
 */
@Entity(name = "Nursing_Anamnesis")
public class Nursing_Anamnesis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String currentdiagnosis;
    String orientation;
    String communication_ability;
    String contactsInfo;
    String nutrition;
    String mobility;
    String personalHygiene;
    String vitalSigns;

    public Nursing_Anamnesis(){}
    
    public void create (String currentdiagnosis, String orientation, String communication_ability, String contactsInfo,
    String nutrition, String mobility, String personalHygiene, String vitalSigns){
        this.setCurrentdiagnosis(currentdiagnosis);
        this.setMobility(mobility);
        this.setNutrition(nutrition);
        this.setOrientation(orientation);
        this.setCommunication_ability(communication_ability);
        this.setcontactsInfo(contactsInfo);
        this.setPersonalHygiene(personalHygiene);
        this.setVitalSigns(vitalSigns);

    }

    
    public String getCurrentdiagnosis() {
        return currentdiagnosis;
    }

    public void setCurrentdiagnosis(String currentdiagnosis) {
        this.currentdiagnosis = currentdiagnosis;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getCommunication_ability() {
        return communication_ability;
    }

    public void setCommunication_ability(String communication_ability) {
        this.communication_ability = communication_ability;
    }

    public String getcontactsInfo() {
        return contactsInfo;
    }

    public void setcontactsInfo(String contactsInfo) {
        this.contactsInfo = contactsInfo;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public String getMobility() {
        return mobility;
    }

    public void setMobility(String mobility) {
        this.mobility = mobility;
    }

    public String getPersonalHygiene() {
        return personalHygiene;
    }

    public void setPersonalHygiene(String personalHygiene) {
        this.personalHygiene = personalHygiene;
    }

    public String getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(String vitalSigns) {
        this.vitalSigns = vitalSigns;
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
        if (!(object instanceof Nursing_Anamnesis)) {
            return false;
        }
        Nursing_Anamnesis other = (Nursing_Anamnesis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClass.Nursing_Anamnesis[ id=" + id + " ]";
    }
}