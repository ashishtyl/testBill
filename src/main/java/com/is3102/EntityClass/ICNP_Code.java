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
 * @author ashish
 */
@Entity
public class ICNP_Code implements Serializable {

    @Id
    private String code;
    private String name;
    private String category;
    private Long price;
    private String dislay;

    public void ICNP_Code() {}

    public void ICNP_Code(String code, String name, String category) {

        this.setCategory(category);
        this.setCode(code);
        this.setName(name);
        this.setDislay(code + " " + name);

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDislay() {
        return dislay;
    }

    public void setDislay(String dislay) {
        this.dislay = dislay;
    }
}
