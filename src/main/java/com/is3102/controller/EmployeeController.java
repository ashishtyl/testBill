package com.is3102.controller;

import com.is3102.entity.Employee;
import com.is3102.entity.EmployeeGroup;
import com.is3102.service.EmployeeService;
import com.is3102.util.Logger;
import com.is3102.util.SHA;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@Named
@SessionScoped
public class EmployeeController implements Serializable  {

    private @Inject EmployeeService das;
    // Selected employees that will be removed
    private Employee[] selectedEmployees;
    // Lazy loading employee list
    private LazyDataModel<Employee> lazyModel;
    // Creating new employee
    private Employee newEmployee = new Employee();
    // Selected employee that will be updated
    private Employee selectedEmployee = new Employee();

    private Employee current = new Employee();

    private Employee find = new Employee();

    // Available groups list
    private List<EmployeeGroup> groupList;

    private Logger logger = new Logger();

    /**
     * Default constructor
     */
    public EmployeeController() {
        logger.log("Employee Controller Initiation");
    }

    /**
     * Initializing Data Access Service for LazyEmployeeDataModel class
     * role list for EmployeeContoller class
     */
    @PostConstruct
    public void init(){
        logger.log("postConstruct " +  (das == null ? "null" : "notnull"));
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String username = request.getUserPrincipal().toString();
        if (username == null) return;
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);

        List<Employee> users = das.findWithNamedQuery(Employee.USERNAME, params);
        current = users.get(0);

        lazyModel = new LazyEmployeeDataModel(das);
        groupList = das.findWithNamedQuery(EmployeeGroup.ALL);
        logger.log("grouplistsize" + groupList.size());
        logger.log("group name" + groupList.get(0).getGroup_name());
    }

    /**
     * Create, Update and Delete operations
     */
    public void doCreateEmployee() {
        logger.log("doCreateEmployee");
        Map<String, String> params = this.validate(this.newEmployee);

        if (params != null){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error!", params.get("message")));
            return;
        }
        das.create(newEmployee);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doUpdateEmployee(ActionEvent actionEvent){
        logger.log("doUpdateEmployee");
        SHA sha = new SHA();
        this.selectedEmployee.setPassword(sha.convert(this.selectedEmployee.getPassword()));
        das.update(selectedEmployee);
    }

    /**
     *
     * @param actionEvent
     */
    public void doUpdateProfile(ActionEvent actionEvent){
        logger.log("doUpdateProfile");
        SHA sha = new SHA();
        this.current.setPassword(sha.convert(this.current.getPassword()));
        das.update(current);
    }

    public void doUpdateFind(ActionEvent actionEvent){
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params2 = (Map<String, String>) context.getExternalContext().getRequestParameterMap();
        String username = params2.get("form:username_input");

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);

        List<Employee> users = das.findWithNamedQuery(Employee.USERNAME, params);
        find = users.get(0);
    }
        
    /**
     *
     * @param actionEvent
     */
    public void doDeleteEmployees(ActionEvent actionEvent){
            das.deleteItems(selectedEmployees);
    }     
        
    /**
     * Getters, Setters
     * @return 
     */

    public Employee getSelectedEmployee() {

        return selectedEmployee;  
    }  

    /**
     *
     * @param selectedEmployee
     */
    public void setSelectedEmployee(Employee selectedEmployee) {  
            this.selectedEmployee = selectedEmployee;  
    } 
        
    /**
     *
     * @return
     */
    public Employee[] getSelectedEmployees() {
        System.out.println("password = " + this.selectedEmployee.getPassword());
            return selectedEmployees;  
    }  
        
    /**
     *
     * @param selectedEmployees
     */
    public void setSelectedEmployees(Employee[] selectedEmployees) {  
            this.selectedEmployees = selectedEmployees;  
    }

    /**
     *
     * @return
     */
    public Employee getNewEmployee() {
            return newEmployee;
    }

    /**
     *
     * @param newEmployee
     */
    public void setNewEmployee(Employee newEmployee) {
            this.newEmployee = newEmployee; 
    }
       
    /**
     *
     * @return LazyDataModel
     */

    public Map<String, String> validate(Employee e){
        return das.validate(e);
    }

    public LazyDataModel<Employee> getLazyModel() {
        return lazyModel;
    }

    public EmployeeService getDas() {
        return das;
    }

    public void setDas(EmployeeService das) {
        this.das = das;
    }

    public void setLazyModel(LazyDataModel<Employee> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public List<EmployeeGroup> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<EmployeeGroup> groupList) {
        this.groupList = groupList;
    }

    public List<String> complete(String query){
        System.out.println("in complete");
         return das.getCompleteEmployee(query);
    }

    public Employee getCurrent() {
        return current;
    }

    public void setCurrent(Employee current) {
        this.current = current;
    }

    public Employee getFind() {
        return find;
    }

    public void setFind(Employee find) {
        this.find = find;
    }
}
                    