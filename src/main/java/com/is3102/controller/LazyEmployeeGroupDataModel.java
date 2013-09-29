package com.is3102.controller;


import com.is3102.entity.EmployeeGroup;
import com.is3102.service.EmployeeGroupService;
import com.is3102.util.LazySorter;
import com.is3102.util.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LazyEmployeeGroupDataModel extends LazyDataModel<EmployeeGroup> implements Serializable{

    // Data Source for binding data to the DataTable
    private List<EmployeeGroup> datasource;
    // Selected Page size in the DataTable
    private int pageSize;
    // Current row index number
    private int rowIndex;
    // Total row number
    private int rowCount;
    // Data Access Service for create read update delete operations
    private EmployeeGroupService crudService;

    private Logger logger = new Logger();

    /**
     *
     * @param crudService
     */
    public LazyEmployeeGroupDataModel(EmployeeGroupService crudService) {
        logger.log("LazyEmployeeGroupDataModel");
        this.crudService = crudService;
        logger.log(crudService == null ? "null" : "notnull");
    }

    /**
     * Lazy loading employeeGroup list with sorting ability
     * @param first
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param filters
     * @return List<EmployeeGroup>
     */
    @Override
    public List<EmployeeGroup> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        logger.log("first" + first);
        logger.log("pageSize" + pageSize);
        logger.log("sortField" + sortField);
        logger.log("sortOrder" + sortOrder);

        datasource = crudService.findWithNamedQuery(EmployeeGroup.ALL, first, first + pageSize);

        logger.log(datasource.size() + " size");

        // if sort field is not null then we sort the field according to sortfield and sortOrder parameter
        if(sortField != null) {
            Collections.sort(datasource, new LazySorter(sortField, sortOrder));
        }

        setRowCount(crudService.countTotalRecord(EmployeeGroup.TOTAL));
        logger.log(datasource.size() + "this is it");
        logger.log(EmployeeGroup.TOTAL + "  " + crudService.countTotalRecord("EmployeeGroup.countEmployeeGroupsTotal"));
        return datasource;
    }

    /**
     * Checks if the row is available
     * @return boolean
     */
    @Override
    public boolean isRowAvailable() {
        if(datasource == null)
            return false;
        int index = rowIndex % pageSize ;
        return index >= 0 && index < datasource.size();
    }

    /**
     * Gets the employeeGroup object's primary key
     * @param employeeGroup
     * @return Object
     */
    @Override
    public Object getRowKey(EmployeeGroup employeeGroup) {
        return employeeGroup.getId().toString();
    }

    /**
     * Returns the employeeGroup object at the specified position in datasource.
     * @return
     */
    @Override
    public EmployeeGroup getRowData() {
        if(datasource == null)
            return null;
        int index =  rowIndex % pageSize;
        if(index > datasource.size()){
            return null;
        }
        return datasource.get(index);
    }

    /**
     * Returns the employeeGroup object that has the row key.
     * @param rowKey
     * @return
     */
    @Override
    public EmployeeGroup getRowData(String rowKey) {
        if(datasource == null)
            return null;
       for(EmployeeGroup employeeGroup : datasource) {
           if(employeeGroup.getId().toString().equals(rowKey))
           return employeeGroup;
       }
       return null;
    }


    /*
     * ===== Getters and Setters of LazyEmployeeGroupDataModel fields
     */


    /**
     *
     * @param pageSize
     */
    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Returns page size
     * @return int
     */
    @Override
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Returns current row index
     * @return int
     */
    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }

    /**
     * Sets row index
     * @param rowIndex
     */
    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * Sets row count
     * @param rowCount
     */
    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * Returns row count
     * @return int
     */
    @Override
    public int getRowCount() {
        return this.rowCount;
    }

    /**
     * Sets wrapped data
     * @param list
     */
    @Override
    public void setWrappedData(Object list) {
        this.datasource = (List<EmployeeGroup>) list;
    }

    /**
     * Returns wrapped data
     * @return
     */
    @Override
    public Object getWrappedData() {
        return datasource;
    }

    public List<EmployeeGroup> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<EmployeeGroup> datasource) {
        this.datasource = datasource;
    }

    public EmployeeGroupService getCrudService() {
        return crudService;
    }

    public void setCrudService(EmployeeGroupService crudService) {
        this.crudService = crudService;
    }
}

