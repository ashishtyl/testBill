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
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ashish
 */
@Entity
public class ICD10_PCS implements Serializable {
   
    @Id
    private String code;
    private String name;
    private String display;
    private Long price;
    
    
    public void ICD10_PCS(){}
    
    public void create(String code, String name, Long price){
        
        this.setCode(code);
        this.setName(name);
        this.setPrice(price);
        this.setDisplay(name+" "+code);
        
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }       
}
