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
import javax.persistence.OneToOne;

/**
 *
 * @author ashish
 */
@Entity
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = {CascadeType.ALL})
    List<Transactions> transctions = new ArrayList<Transactions>();
    @OneToOne
    mCase mCase = new mCase();
    private double total;
    private double discount;
    private double netTotal;

    public void Bill() {
    }

    public void create(mCase mcase) {
        this.setmCase(mCase);
    }

    public void claculateTotal() {
        for (Transactions t : this.transctions) {
            this.total += t.getPrice();
        }
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Transactions> getTransctions() {
        return transctions;
    }

    public void setTransctions(List<Transactions> transctions) {
        this.transctions = transctions;
    }

    public mCase getmCase() {
        return mCase;
    }

    public void setmCase(mCase mCase) {
        this.mCase = mCase;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        
        this.discount = (discount*total);
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal() {
        this.netTotal = (this.total - this.discount);
    }
}
