package com.is3102.controller;


import com.is3102.entity.Employee;
import com.is3102.entity.Inbox;
import com.is3102.service.InboxService;
import com.is3102.util.LazySorter;
import com.is3102.util.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

public class LazyInboxDataModel extends LazyDataModel<Inbox> implements Serializable{

    // Data Source for binding data to the DataTable
    private List<Inbox> datasource;
    // Selected Page size in the DataTable
    private int pageSize;
    // Current row index number
    private int rowIndex;
    // Total row number
    private int rowCount;
    // Data Access Service for create read update delete operations
    private InboxService crudService;

    private Logger logger = new Logger();

    /**
     *
     * @param crudService
     */
    public LazyInboxDataModel(InboxService crudService) {
        logger.log("LazyInboxDataModel");
        this.crudService = crudService;
        logger.log(crudService == null ? "null" : "notnull");
    }

    /**
     * Lazy loading inbox list with sorting ability
     * @param first
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param filters
     * @return List<Inbox>
     */
    @Override
    public List<Inbox> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        logger.log("first" + first);
        logger.log("pageSize" + pageSize);
        logger.log("sortField" + sortField);
        logger.log("sortOrder" + sortOrder);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String username = request.getUserPrincipal().toString();
        if (username == null) return new ArrayList<Inbox>();
        Map<String, String> params = new HashMap <String, String>();
        params.put("username", username);

        List<Employee> users = crudService.findWithNamedQuery(Employee.USERNAME, params);
        Employee User = users.get(0);
        Integer id = Integer.valueOf(User.getId());
        System.out.println(id);
        Map<String, Object> params2 = new HashMap <String, Object>();
        params2.put("receiverid", id);

        datasource = crudService.findWithNamedQuery(Inbox.RECEIVERID, params2, first, first + pageSize);

        logger.log(datasource.size() + " size");

        // if sort field is not null then we sort the field according to sortfield and sortOrder parameter
        if(sortField != null) {
            Collections.sort(datasource, new LazySorter(sortField, sortOrder));
        }

        setRowCount(crudService.countTotalRecord(Inbox.TOTAL));
        logger.log(datasource.size() + "this is it");
        logger.log(Inbox.TOTAL + "  " + crudService.countTotalRecord("Inbox.countInboxsTotal"));
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
     * Gets the inbox object's primary key
     * @param inbox
     * @return Object
     */
    @Override
    public Object getRowKey(Inbox inbox) {
        return inbox.getId().toString();
    }

    /**
     * Returns the inbox object at the specified position in datasource.
     * @return
     */
    @Override
    public Inbox getRowData() {
        if(datasource == null)
            return null;
        int index =  rowIndex % pageSize;
        if(index > datasource.size()){
            return null;
        }
        return datasource.get(index);
    }

    /**
     * Returns the inbox object that has the row key.
     * @param rowKey
     * @return
     */
    @Override
    public Inbox getRowData(String rowKey) {
        if(datasource == null)
            return null;
       for(Inbox inbox : datasource) {
           if(inbox.getId().toString().equals(rowKey))
           return inbox;
       }
       return null;
    }


    /*
     * ===== Getters and Setters of LazyInboxDataModel fields
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
        this.datasource = (List<Inbox>) list;
    }

    /**
     * Returns wrapped data
     * @return
     */
    @Override
    public Object getWrappedData() {
        return datasource;
    }

    public List<Inbox> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<Inbox> datasource) {
        this.datasource = datasource;
    }

    public InboxService getCrudService() {
        return crudService;
    }

    public void setCrudService(InboxService crudService) {
        this.crudService = crudService;
    }
}

