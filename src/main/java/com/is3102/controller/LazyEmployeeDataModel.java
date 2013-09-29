package com.is3102.controller;


import com.is3102.entity.Employee;
import com.is3102.service.EmployeeService;
import com.is3102.util.LazySorter;
import com.is3102.util.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LazyEmployeeDataModel extends LazyDataModel<Employee> implements Serializable{

    // Data Source for binding data to the DataTable
    private List<Employee> datasource;
    // Selected Page size in the DataTable
    private int pageSize;
    // Current row index number
    private int rowIndex;
    // Total row number
    private int rowCount;
    // Data Access Service for create read update delete operations
    private EmployeeService crudService;

    Logger logger = new Logger();

    public LazyEmployeeDataModel(EmployeeService crudService) {
        logger.log("LazyEmployeeDataModel");
        this.crudService = crudService;
        logger.log(crudService == null ? "null" : "notnull");
    }

    @Override
    public List<Employee> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        logger.log("first " + first);
        logger.log("pageSize " + pageSize);
        logger.log("sortField " + sortField);
        logger.log("sortOrder " + sortOrder);

        datasource = crudService.findWithNamedQuery(Employee.ALL, first, first + pageSize);
        logger.log(datasource.size() + " size");
        // if sort field is not null then we sort the field according to sortfield and sortOrder parameter
        if(sortField != null) {
            Collections.sort(datasource, new LazySorter(sortField, sortOrder));
        }
        setRowCount(crudService.countTotalRecord(Employee.TOTAL));
        logger.log(datasource.size() + " size datasource");
        logger.log(Employee.TOTAL + "  " + crudService.countTotalRecord("Employee.countEmployeesTotal"));
        return datasource;
    }

    @Override
    public boolean isRowAvailable() {
        if(datasource == null)
            return false;
        int index = rowIndex % pageSize ;
        return index >= 0 && index < datasource.size();
    }

    @Override
    public Object getRowKey(Employee employee) {
        return employee.getId().toString();
    }

    @Override
    public Employee getRowData() {
        if(datasource == null)
            return null;
        int index =  rowIndex % pageSize;
        if(index > datasource.size()){
            return null;
        }
        return datasource.get(index);
    }

    @Override
    public Employee getRowData(String rowKey) {
        if(datasource == null)
            return null;
       for(Employee employee : datasource) {
           if(employee.getId().toString().equals(rowKey))
           return employee;
       }
       return null;
    }


    /*
     * ===== Getters and Setters
     */

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public int getRowCount() {
        return this.rowCount;
    }

    @Override
    public void setWrappedData(Object list) {
        this.datasource = (List<Employee>) list;
    }

    @Override
    public Object getWrappedData() {
        return datasource;
    }

    public List<Employee> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<Employee> datasource) {
        this.datasource = datasource;
    }

    public EmployeeService getCrudService() {
        return crudService;
    }

    public void setCrudService(EmployeeService crudService) {
        this.crudService = crudService;
    }
}

