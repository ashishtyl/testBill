/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
 * @author Swarit
 */
@Entity(name = "Medication")
public class Medication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long medId;
    String name;
    String dosage;
    String details;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private mCase mcase;

    public Medication() {
    }

    public void create(String name, String dosage, String details) {
        this.setName(name);
        this.setDosage(dosage);
        this.setDetails(details);
    }

    public Long getMedId() {
        return medId;
    }

    public void setMedId(Long medId) {
        this.medId = medId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public mCase getMcase() {
        return mcase;
    }

    public void setMcase(mCase mcase) {
        this.mcase = mcase;
    }
}
