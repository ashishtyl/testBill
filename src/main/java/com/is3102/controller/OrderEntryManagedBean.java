/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import java.io.Serializable;
import com.is3102.EntityClass.DrugCatalog;
import com.is3102.EntityClass.POEOrder;
import com.is3102.EntityClass.Patient;
import com.is3102.service.OrderEntryRemote;
import com.is3102.EntityClass.ServiceCatalog;
import com.is3102.service.AdministrativeAdmissionRemote;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Swarit
 */
@ManagedBean
@ViewScoped
public class OrderEntryManagedBean implements Serializable {

    @EJB
    public OrderEntryRemote oem;
    @EJB
    public AdministrativeAdmissionRemote am;
    List<DrugCatalog> drugsCatalog = new ArrayList<DrugCatalog>();
    //private DrugCatalog selectedDrug;
    //private DrugCatalog[] selectedDrugs;
    private List<ServiceCatalog> serviceCatalog = new ArrayList<ServiceCatalog>();
    private String PIN;
    private String CIN;
    private String name;
    private long dosage;
    private int quantity;
    private String details;
    private double price;
    private String category;
    private String lrpCIN;
    private String lrpName;
    private int lrpQuantity;
    private String lrpDetails;
    private double totalPrice;
    private Date appDate;
    private List<Patient> patients = new ArrayList<Patient>();
    private List<POEOrder> orders = new ArrayList<POEOrder>();

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public List<POEOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<POEOrder> orders) {
        this.orders = orders;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDosage() {
        return dosage;
    }

    public void setDosage(long dosage) {
        this.dosage = dosage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<DrugCatalog> getDrugsCatalog() {
        return drugsCatalog;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public OrderEntryManagedBean() {
    }

    public void doListPatients(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        patients = am.getCurrentPatients();
        if (patients.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Patient Record Not Found!", null));
        }
    }

    public void doListPatientOrders(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        Patient pp = am.checkPatient(PIN);
        if (pp != null) {
            orders = oem.listPatientOrders(PIN);
            if (orders.isEmpty()) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Orders Not Found!", null));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Please check Patient PIN!", null));
        }
    }

    public void doDisplayDrugsCatalog(ActionEvent actionEvent) {
        drugsCatalog = oem.displayDrugCatalog();
    }

    public void doDisplayServiceCatalog(ActionEvent actionEvent) {
        setServiceCatalog(oem.displayServiceCatalog());
    }
        
        public void doDisplayServiceCatalog2(ActionEvent actionEvent) {
        setServiceCatalog(oem.displayServiceCatalog2());
        /*for (DrugCatalog dc : drugsCatalog) {
         drugTypes[count++] = dc.getType();
         }*/
    }

    public void doPrescribeMedication(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            String medName = oem.prescribeMedication(CIN, name, dosage, quantity, details);
            context.addMessage(null, new FacesMessage("Medication " + medName + " successfully ordered!"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Order again!", null));
        }
    }

    public void doOrderLabRadProcedure(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String sevName = oem.orderLabRadProcedure(getLrpCIN(), getLrpName(), getLrpQuantity(), getLrpDetails(), format.format(getAppDate()));
            context.addMessage(null, new FacesMessage("Procedure " + sevName + " successfully ordered!"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Order again!", null));
        }
    }
        
                public void doOrderLabRadProcedure2(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String sevName = oem.orderLabRadProcedure(getLrpCIN(), getLrpName(), getLrpQuantity(), getLrpDetails(), "2012-01-01 00:00");
            context.addMessage(null, new FacesMessage("Procedure " + sevName + " successfully ordered!"));
        } catch (Exception ex) {
            //FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null);
            //FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Order again!", null));
        }
    }
        
        
    
    /*private SelectItem[] createFilterOptions(String[] drugTypes) {
     SelectItem[] options = new SelectItem[drugTypes.length + 1];

    public List<ServiceCatalog> getServiceCatalog() {
        return serviceCatalog;
    }

    /**
     * @param serviceCatalog the serviceCatalog to set
     */
    public void setServiceCatalog(List<ServiceCatalog> serviceCatalog) {
        this.serviceCatalog = serviceCatalog;
    }

    /**
     * @return the lrpCIN
     */
    public String getLrpCIN() {
        return lrpCIN;
    }

    /**
     * @param lrpCIN the lrpCIN to set
     */
    public void setLrpCIN(String lrpCIN) {
        this.lrpCIN = lrpCIN;
    }

    /**
     * @return the lrpName
     */
    public String getLrpName() {
        return lrpName;
    }

    /**
     * @param lrpName the lrpName to set
     */
    public void setLrpName(String lrpName) {
        this.lrpName = lrpName;
    }

    /**
     * @return the lrpQuantity
     */
    public int getLrpQuantity() {
        return lrpQuantity;
    }

    /**
     * @param lrpQuantity the lrpQuantity to set
     */
    public void setLrpQuantity(int lrpQuantity) {
        this.lrpQuantity = lrpQuantity;
    }

    /**
     * @return the lrpDetails
     */
    public String getLrpDetails() {
        return lrpDetails;
    }

    /**
     * @param lrpDetails the lrpDetails to set
     */
    public void setLrpDetails(String lrpDetails) {
        this.lrpDetails = lrpDetails;
    }

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the appDate
     */
    public Date getAppDate() {
        return appDate;
    }

    /**
     * @param appDate the appDate to set
     */
    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }
}
