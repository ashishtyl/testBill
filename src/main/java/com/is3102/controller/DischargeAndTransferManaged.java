/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import com.is3102.EntityClass.DischargeSummary;
import com.is3102.EntityClass.Transfer;
import com.is3102.service.DischargeAndTransferBean1Remote;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ben
 */
@ManagedBean
public class DischargeAndTransferManaged {
  @EJB
  private DischargeAndTransferBean1Remote dt;
  
  //case id to search case
  private Long caseId;
  //Id for discharge entity
  private Long dischargeId;
  //final diagnosis to enter in the discharge summary
  private String diagnosis;
  //final findings
  private String findings;
  //Doctors recommendation at the time of discharge
  private String recommendation;
  //Patient's condition at the time of discharge
  private String patientState;
  //medical procedure at the time of discharge
  private String medicalProcedure;
  //doctor referred to for transfer patient
  private String referDoctor;
  //reason for transfer
  private String reason;
  //Transfer entity to retrieve transfer case
  private Transfer transfer;
  //
  private DischargeSummary dischargeSummary;
  //
  private Long transferid;

   //list testing
  private List<DischargeSummary> listds;
  private List<Transfer> listt;
   //list testing

    
 
  public void DogenerateDischargeSummary(ActionEvent actionEvent){
      FacesContext context = FacesContext.getCurrentInstance();
      try{
          System.out.println("test discharge generating function 1");
          dt.generateDischargeSummary(caseId, diagnosis, findings, recommendation, patientState, medicalProcedure);
          context.addMessage(null, new FacesMessage("Discharge summary generated successfully"));
      }catch(Exception ex){
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "DischargeSummary cannot be generated!", null));

      }
  }

    public DischargeAndTransferBean1Remote getDt() {
        return dt;
    }

    public void setDt(DischargeAndTransferBean1Remote dt) {
        this.dt = dt;
    }

    public Long getTransferid() {
        return transferid;
    }

    public void setTransferid(Long transferid) {
        this.transferid = transferid;
    }

    //list testing
    public List<DischargeSummary> getListds() {
        //System.out.println("Test two");
       listds= dt.ListDischargeSummary();
        //for (DischargeSummary ds: listds) {
           //System.out.println(ds.getDiagnosis());
       // }
        return listds;
    }
    //list testing

    public void setListds(List<DischargeSummary> listds) {
        this.listds = listds;
    }
    //list testing
    public List<Transfer> getListt() {
        //System.out.println("xxxxxxxxxxxxxx");
       listt= dt.ListTransfer();
        //if(listt==null)
            //System.out.println("test null");
        return listt;
    }
    //list testing

    public void setListt(List<Transfer> listt) {
        this.listt = listt;
    }
    //list testing

    public void DogetDischargeSummary(ActionEvent actionEvent){
        FacesContext context = FacesContext.getCurrentInstance();
      try{
         this.setDischargeSummary(dt.getDischargeSummary(dischargeId));
      }catch(Exception ex){
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "DischargeSummary cannot be found!", null));
      }
  }
  
  public void DoDeleteSummary(ActionEvent actionEvent){

      FacesContext context = FacesContext.getCurrentInstance();
      try {
          dt.deleteDischargeSummary(dischargeId);
          context.addMessage(null, new FacesMessage("Discharge summary deleted successfully"));
      } catch (Exception ex) {
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "DischargeSummary cannot be found!", null));
      }
  }

  
  public void DoRecordTransfer(ActionEvent actionEvent){
      FacesContext context = FacesContext.getCurrentInstance();
      try{
      dt.recodeTransfer(caseId, referDoctor, reason);
          context.addMessage(null, new FacesMessage("Transfer recoded successfully"));
      }catch(Exception ex){
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transfer cannot be recorded!", null));
      }
  }
  
  public void DoGetTransfer(ActionEvent actionEvent){
      FacesContext context = FacesContext.getCurrentInstance();

      try{
          this.setTransfer(dt.getTransfer(transferid));
      }catch(Exception ex){
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
          context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transfer cannot be found!", null));
      }
  }
  
     public void DoDeleteTransfer(ActionEvent actionEvent){
         FacesContext context = FacesContext.getCurrentInstance();

         try{
             dt.deleteTransfer(transferid);
             context.addMessage(null, new FacesMessage("Transfer deleted successfully"));
         }catch(Exception ex){
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transfer cannot be found!", null));
         }
  }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getMedicalProcedure() {
        return medicalProcedure;
    }

    public void setMedicalProcedure(String medicalProcedure) {
        this.medicalProcedure = medicalProcedure;
    }

    public String getReferDoctor() {
        return referDoctor;
    }

    public void setReferDoctor(String referDoctor) {
        this.referDoctor = referDoctor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public DischargeSummary getDischargeSummary() {
        return dischargeSummary;
    }

    public void setDischargeSummary(DischargeSummary dischargeSummary) {
        this.dischargeSummary = dischargeSummary;
    }
    
    public Long getDischargeId() {
        return dischargeId;
    }

    public void setDischargeId(Long dischargeId) {
        this.dischargeId = dischargeId;
    }

}