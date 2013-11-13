/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;



import com.is3102.EntityClass.LabRadProcedure;
import com.is3102.EntityClass.Report;
import com.is3102.EntityClass.mCase;
import com.is3102.service.ReportRemote;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Ben
 */

@ManagedBean
@ViewScoped
public class ReportManaged implements Serializable{
    
      @EJB
      public ReportRemote rr;
    
      String name;
      String reportContents;
      String requestingPhysician;
      Date reportDate; 
      List<Report> reportList = new ArrayList<Report>();
      List<Report> reportList2 = new ArrayList<Report>();
    
      Long lrpId;
      Long CIN;
      private String passwordId;
      List<mCase> caseList;
      List<LabRadProcedure> lrpList;
      
      
      public void DoListmCase (ActionEvent event){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            System.out.println("In DoListmCase");
            caseList = rr.ListmCase(passwordId);
            System.out.println("Set case list");
            if (caseList.isEmpty() == true) {
                System.out.println("Error occured");
            }
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No records found, Please check your NRIC/PASSWORD Id!", null));
        }
    }
      
       public List<LabRadProcedure> DoListLabRadProcedure(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            System.out.println("Hello");
            lrpList = rr.ListLabRadProcedure(CIN, passwordId);
            if(lrpList == null)
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Records Yet!", null));
            System.out.println("HelloHello");
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot List, Please Check CIN!", null));
        }
        return lrpList;
    }
      
      public void DoAddReport(ActionEvent actionEvent) {
             FacesContext context = FacesContext.getCurrentInstance();
        try {
            //System.out.println(anamnesisId);
            Long testId =rr.addReport(lrpId, name, reportContents, requestingPhysician, reportDate);
            if (testId!=null)
               context.addMessage(null, new FacesMessage("Report added successfully"));
            else
               context.addMessage(null, new FacesMessage ("The Specfied Procedure Does not Exsit or It Already Has a Report!"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "The Specfied Procedure Does not Exsit or It Already Has a Report!", null));
        }
    }
          
     
      public void DoCheckReport(ActionEvent actionEvent) {
           FacesContext context = FacesContext.getCurrentInstance();
        try {
            reportList=rr.checkReport(CIN);
                if (reportList!=null)
                 context.addMessage(null, new FacesMessage("Below is the list of reports of the specfied case"));
               else
                 context.addMessage(null, new FacesMessage("No Reports Record Found")); 
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Reports!", null));
        }     
      }
     
      public void DoListReport(ActionEvent actionEvent) {
         FacesContext context = FacesContext.getCurrentInstance();
        try {
              reportList2=rr.listReport();
              if (reportList2!=null)
                 context.addMessage(null, new FacesMessage("Below is the list of all reports"));
              else
                 context.addMessage(null, new FacesMessage("No Reports Record Found")); 
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Reports!", null));
        }       
     }

    public ReportRemote getRr() {
        return rr;
    }

    public void setRr(ReportRemote rr) {
        this.rr = rr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReportContents() {
        return reportContents;
    }

    public void setReportContents(String reportContents) {
        this.reportContents = reportContents;
    }

    public String getRequestingPhysician() {
        return requestingPhysician;
    }

    public void setRequestingPhysician(String requestingPhysician) {
        this.requestingPhysician = requestingPhysician;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public List<Report> getReportList2() {
        return reportList2;
    }

    public void setReportList2(List<Report> reportList2) {
        this.reportList2 = reportList2;
    }

    public Long getLrpId() {
        return lrpId;
    }

    public void setLrpId(Long lrpId) {
        this.lrpId = lrpId;
    }

    public Long getCIN() {
        return CIN;
    }

    public void setCIN(Long CIN) {
        this.CIN = CIN;
    }

    public String getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(String passwordId) {
        this.passwordId = passwordId;
    }

    public List<mCase> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<mCase> caseList) {
        this.caseList = caseList;
    }

    public List<LabRadProcedure> getLrpList() {
        return lrpList;
    }

    public void setLrpList(List<LabRadProcedure> lrpList) {
        this.lrpList = lrpList;
    }
       
      
}
