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
@Entity(name = "DrugCatalog")
public class DrugCatalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type; //Anaesthetics; Analgesics and Anti-pyretics; Anti-allergics; Antidotes; Anti-migrain; 
    private String method; // tablet; syrup; suppository
    private Long recommendedDosage; // 500 (mg)
    private String sideEffect;
    private Long price;
    private String category;

    public DrugCatalog() {
    }

    public void create(String name, String type) {
        this.setName(name);
        this.setType(type);
        this.setMethod(method);
        this.setRecommendedDosage(recommendedDosage);
        this.setSideEffect(sideEffect);
        this.setPrice(price);
        this.setCategory(category);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getRecommendedDosage() {
        return recommendedDosage;
    }

    public void setRecommendedDosage(Long recommendedDosage) {
        this.recommendedDosage = recommendedDosage;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}