/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Swarit
 */
@Entity(name = "Medication")
public class Medication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long medId;
    private String name;
    private Long dosage;
    private String details;
    private double totalPrice;
    private int quantity;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private mCase mcase;
    @OneToOne(mappedBy = "medication")
    private POEOrder order;

    public Medication() {
    }

    public void create(String name, Long dosage, int quantity, String details, double totalPrice) {
        this.setName(name);
        this.setDosage(dosage);
        this.setQuantity(quantity);
        this.setDetails(details);
        this.setTotalPrice(totalPrice);
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

    public Long getDosage() {
        return dosage;
    }

    public void setDosage(Long dosage) {
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

    public POEOrder getOrder() {
        return order;
    }

    public void setOrder(POEOrder order) {
        this.order = order;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
