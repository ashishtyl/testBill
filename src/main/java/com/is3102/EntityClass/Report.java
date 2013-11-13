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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author user
 */
@Entity(name = "Report")
public class Report implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportId;
    private String name;
    private String reportContents;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date reportDate;
    private String requestingPhysician;
  
    
    public Report(){}
    
    public void create(String name, String reportContents, String requestingPhysician, Date reportDate){
        this.setName(name);
        this.setReportContents(reportContents);
        this.setRequestingPhysician(requestingPhysician);
        this.setReportDate(reportDate);
    }

    public String getReportContents() {
        return reportContents;
    }

    public void setReportContents(String reportContents) {
        this.reportContents = reportContents;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getRequestingPhysician() {
        return requestingPhysician;
    }

    public void setRequestingPhysician(String requestingPhysician) {
        this.requestingPhysician = requestingPhysician;
    }
   

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }
      public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
}