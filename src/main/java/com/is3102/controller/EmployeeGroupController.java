package com.is3102.controller;

import com.is3102.entity.EmployeeGroup;
import com.is3102.service.EmployeeGroupService;
import com.is3102.util.Logger;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

@Named
@SessionScoped
public class EmployeeGroupController implements Serializable  {

    private @Inject EmployeeGroupService das;
    // Selected employeeGroups that will be removed
    private EmployeeGroup[] selectedEmployeeGroups;
    // Lazy loading employeeGroup list
    private LazyDataModel<EmployeeGroup> lazyModel;
    // Creating new employeeGroup
    private EmployeeGroup newEmployeeGroup = new EmployeeGroup();
    // Selected employeeGroup that will be updated
    private EmployeeGroup selectedEmployeeGroup = new EmployeeGroup();

    private Logger logger = new Logger();

    /**
     * Default constructor
     */
    public EmployeeGroupController() {
        logger.log("EmployeeGroup Controller Constructor");
    }

    /**
     * init
     */
    @PostConstruct
    public void init(){
        System.out.println("postConstruct " +  (das == null ? "null" : "notnull"));
        lazyModel = new LazyEmployeeGroupDataModel(das);
    }

    /**
     * Create, Update and Delete operations
     */
    public void doCreateEmployeeGroup() {
        System.out.println("doCreateEmployeeGroup");
        das.create(newEmployeeGroup);
    }

    /**
     *
     * @param actionEvent
     */
    public void doUpdateEmployeeGroup(ActionEvent actionEvent){
        System.out.println("doUpdateEmployeeGroup");

        das.update(selectedEmployeeGroup);
    }

    /**
     *
     * @param actionEvent
     */
    public void doDeleteEmployeeGroups(ActionEvent actionEvent){
        das.deleteItems(selectedEmployeeGroups);
    }

    /**
     * Getters, Setters
     * @return
     */

    public EmployeeGroup getSelectedEmployeeGroup() {
        return selectedEmployeeGroup;
    }

    public void setSelectedEmployeeGroup(EmployeeGroup selectedEmployeeGroup) {
        this.selectedEmployeeGroup = selectedEmployeeGroup;
    }

    public EmployeeGroup[] getSelectedEmployeeGroups() {
        return selectedEmployeeGroups;
    }

    public void setSelectedEmployeeGroups(EmployeeGroup[] selectedEmployeeGroups) {
        this.selectedEmployeeGroups = selectedEmployeeGroups;
    }

    public EmployeeGroup getNewEmployeeGroup() {
        return newEmployeeGroup;
    }

    public void setNewEmployeeGroup(EmployeeGroup newEmployeeGroup) {
        this.newEmployeeGroup = newEmployeeGroup;
    }

    public LazyDataModel<EmployeeGroup> getLazyModel() {
        return lazyModel;
    }

    public EmployeeGroupService getDas() {
        return das;
    }

    public void setDas(EmployeeGroupService das) {
        this.das = das;
    }

    public void setLazyModel(LazyDataModel<EmployeeGroup> lazyModel) {
        this.lazyModel = lazyModel;
    }
}
                    