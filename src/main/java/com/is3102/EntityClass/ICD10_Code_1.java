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
 * @author Ashish
 */
@Entity
public class ICD10_Code_1 implements Serializable {
       
   
    private String Chapter;
    private String Block;
    private String Disease;
    @Id
    private String code;
    private String name;
    private String display;
   
    public ICD10_Code_1(){}
    
    public void create(String code, String Chapter, String block, String Disease, String name ){
        this.setCode(code);
        this.setChapter(Chapter);
        this.setBlock(Block);
        this.setDisease(Disease);
        this.setName(name);
        this.setDisplay(code+" "+Disease+" "+name);
        
    }
    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
   

    
    @Override
    public String toString() {
        return "EntityClass.ICD10_Code[ id=" + getCode() + " ]";
    }
  

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the Chapter
     */
    public String getChapter() {
        return Chapter;
    }

    /**
     * @param Chapter the Chapter to set
     */
    public void setChapter(String Chapter) {
        this.Chapter = Chapter;
    }

    /**
     * @return the Block
     */
    public String getBlock() {
        return Block;
    }

    /**
     * @param Block the Block to set
     */
    public void setBlock(String Block) {
        this.Block = Block;
    }

    /**
     * @return the Disease
     */
    public String getDisease() {
        return Disease;
    }

    /**
     * @param Disease the Disease to set
     */
    public void setDisease(String Disease) {
        this.Disease = Disease;
    }
    
}
