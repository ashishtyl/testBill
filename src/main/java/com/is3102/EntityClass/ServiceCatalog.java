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
 * @author Swarit
 */
@Entity(name = "ServiceCatalog")
public class ServiceCatalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name; // blood test
    private String details; // blood test for cancer; blood test for dengue
    private Long price;
    private String procedureType;
    private boolean safeForPregnant;
    private String deviceType;
    
    public ServiceCatalog() {
    }

    public void create(String name, Long price, String details) {
        this.setName(name);
        this.setPrice(price);
        this.setDetails(details);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * @return the safeForPregnant
     */
    public boolean isSafeForPregnant() {
        return safeForPregnant;
    }

    /**
     * @param safeForPregnant the safeForPregnant to set
     */
    public void setSafeForPregnant(boolean safeForPregnant) {
        this.safeForPregnant = safeForPregnant;
    }

    /**
     * @return the procedureType
     */
    public String getProcedureType() {
        return procedureType;
    }

    /**
     * @param procedureType the procedureType to set
     */
    public void setProcedureType(String procedureType) {
        this.procedureType = procedureType;
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
}