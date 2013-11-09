/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import com.is3102.EntityClass.DrugCatalog;
import com.is3102.EntityClass.ServiceCatalog;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import com.is3102.service.OrderEntryRemote;
import java.util.ArrayList;
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
    List<DrugCatalog> drugsCatalog = new ArrayList<DrugCatalog>();
    //private DrugCatalog selectedDrug;
    //private DrugCatalog[] selectedDrugs;
    List<ServiceCatalog> serviceCatalog = new ArrayList<ServiceCatalog>();
    
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
    
    //private SelectItem[] drugTypeOptions;
    //private String[] drugTypes = new String[100];
    //private int count = 0;
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

    public OrderEntryManagedBean() {
    }

    public void doDisplayDrugsCatalog(ActionEvent actionEvent) {
        drugsCatalog = oem.displayDrugCatalog();
        /*for (DrugCatalog dc : drugsCatalog) {
         drugTypes[count++] = dc.getType();
         }*/
    }
    
        public void doDisplayServiceCatalog(ActionEvent actionEvent) {
        serviceCatalog = oem.displayServiceCatalog();
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
            //FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null);
            //FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Order again!", null));
        }
    }
    
        public void doOrderLabRadProcedure(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            String sevName = oem.orderLabRadProcedure(lrpCIN, lrpName, lrpQuantity, lrpDetails);
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

     options[0] = new SelectItem("", "Select");
     for (int i = 0; i < drugTypes.length; i++) {
     options[i + 1] = new SelectItem(drugTypes[i], drugTypes[i]);
     }

     return options;
     }*/
    /*public SelectItem[] getDrugTypeOptions() {
     return drugTypeOptions;
     }*/
}
