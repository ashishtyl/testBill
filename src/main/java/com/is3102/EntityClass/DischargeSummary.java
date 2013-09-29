/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Ben
 */
@Entity
public class DischargeSummary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private String diagnosis;
    private String findings;
    private String recommendation;
    private String patientState;
    private String medicalProcedure;
   
    //super class discharge-medical/nursing/administrative
   
    public void DischargeSummary(){}
    public void create(String diagnosis, String findings, String recommendation,
              String patientState, String medicalProcedure){
        this.setDiagnosis(diagnosis);
        this.setFindings(findings);
        this.setRecommendation(recommendation);
        this.setPatientState(patientState);
        this.setMedicalProcedure(medicalProcedure);
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    //how to auto generate date

   public String getDiagnosis(){
       return diagnosis;
   }
   public void setDiagnosis(String diagnosis){
       this.diagnosis=diagnosis;
   }
   
    public String getFindings(){
       return findings;
   }
   public void setFindings(String findings){
       this.findings=findings;
   }
   
   public String getRecommendation(){
       return recommendation;
   }
   public void setRecommendation(String recommendation){
       this.recommendation=recommendation;
   }
   
   public String getPatientState(){
       return patientState;
   }
   public void setPatientState(String patientState){
       this.patientState=patientState;
   }
   
   public String getMedicalProcedure(){
       return medicalProcedure;
   }
   public void setMedicalProcedure(String medicalProcedure){
       this.medicalProcedure=medicalProcedure;
   }
    
}
