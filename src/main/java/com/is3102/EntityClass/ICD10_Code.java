    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Ashish
 */
@Entity
@Table(name = "icd10code")
public class ICD10_Code implements Serializable {

    @Id
    private String code;
    private String Chapter;
    private String Block;
    private String Disease;
    private String name;
    private String display;
    
    public ICD10_Code() {
    }

    public void create(String code, String Chapter, String Block, String Disease, String name) {
        this.setCode(code);
        this.setChapter(Chapter);
        this.setBlock(Block);
        this.setDisease(Disease);
        this.setName(name);
        this.setDisplay(code+" "+name);
        
    }
    
    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
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
