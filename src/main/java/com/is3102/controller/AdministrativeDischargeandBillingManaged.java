/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import com.is3102.EntityClass.Transactions;
import com.is3102.Exception.ExistException;
import com.is3102.service.AdministrativeDischargeandBillingRemote;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

/**
 *
 * @author Bryan Arnold
 */
@ManagedBean
@SessionScoped
public class AdministrativeDischargeandBillingManaged implements Serializable{
    
    
    
    
    @EJB
    public AdministrativeDischargeandBillingRemote adb;
    List<Transactions> billItems = new ArrayList<Transactions>();

    
    
    public AdministrativeDischargeandBillingManaged(){}
    
    
    long CIN;
    Date dischargeDate;
    public void doGenereatePatientBill(ActionEvent actionEvent){
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            billItems=adb.CalculateBill(CIN);
        }catch(ExistException ex){
            System.out.println("Case not found!");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bill could not be genereated!", null));
        }
    }
    
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String logo = servletContext.getRealPath("") + File.separator + "images" + File.separator + "merlion.png";
        pdf.add(Image.getInstance(logo));
    }

            
    public void doSetDischargeDate(ActionEvent actionEvent){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try {
                adb.setDischargeDate(getCIN());
            } catch (ParseException ex) {
            System.out.println("PARSE EXCEPTION CAUGHT");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Discharge Date could not be Set", null));
            }
        context.addMessage(null, new FacesMessage("Discharge date successfuly updated for mCase CIN: " + getCIN() ));
        } catch (ExistException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Discharge Date could not be Set", null));
        }
    
    }

    /**
     * @return the CIN
     */
    public long getCIN() {
        return CIN;
    }

    /**
     * @param CIN the CIN to set
     */
    public void setCIN(long CIN) {
        this.CIN = CIN;
    }

    /**
     * @return the dischargeDate
     */
    public Date getDischargeDate() {
        return dischargeDate;
    }

    /**
     * @param dischargeDate the dischargeDate to set
     */
    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
    public List<Transactions> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<Transactions> billItems) {
        this.billItems = billItems;
    }
    
    
}
