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
@Entity(name = "LabRadProcedure")
public class LabRadProcedure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long procId;
    private String name; // blood test (get from ServiceCatalog)
    private String details; // blood test for cancer; blood test for dengue (get from ServiceCatalog)
    private double totalPrice;
    private int quantity;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private mCase mcase;
    @OneToOne(mappedBy = "labRadProcedure")
    private POEOrder order;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Report report;

    public LabRadProcedure() {
    }

    public Long getProcId() {
        return procId;
    }

    public void setProcId(Long procId) {
        this.procId = procId;
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

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}