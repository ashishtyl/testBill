package com.is3102.controller;


import com.is3102.entity.Employee;
import com.is3102.entity.Event;
import com.is3102.service.EventService;
import com.is3102.util.LazySorter;
import com.is3102.util.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

public class LazyEventDataModel extends LazyDataModel<Event> implements Serializable{

    // Data Source for binding data to the DataTable
    private List<Event> datasource;
    // Selected Page size in the DataTable
    private int pageSize;
    // Current row index number
    private int rowIndex;
    // Total row number
    private int rowCount;
    // Data Access Service for create read update delete operations
    private EventService crudService;

    private Logger logger = new Logger();

    /**
     *
     * @param crudService
     */
    public LazyEventDataModel(EventService crudService) {
        logger.log("LazyEventDataModel");
        this.crudService = crudService;
        logger.log(crudService == null ? "null" : "notnull");
    }

    /**
     * Lazy loading event list with sorting ability
     * @param first
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param filters
     * @return List<Event>
     */
    @Override
    public List<Event> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        logger.log("first" + first);
        logger.log("pageSize" + pageSize);
        logger.log("sortField" + sortField);
        logger.log("sortOrder" + sortOrder);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String username = request.getUserPrincipal().toString();
        if (username == null) return new ArrayList<Event>();
        Map<String, String> params = new HashMap <String, String>();
        params.put("username", username);

        List<Employee> users = crudService.findWithNamedQuery(Employee.USERNAME, params);
        Employee User = users.get(0);
        Integer id = Integer.valueOf(User.getId());
        System.out.println(id);
        Map<String, Object> params2 = new HashMap <String, Object>();
        params2.put("userid", id);

        datasource = crudService.findWithNamedQuery(Event.USERID, params2, first, first + pageSize);

        logger.log(datasource.size() + " size");

        // if sort field is not null then we sort the field according to sortfield and sortOrder parameter
        if(sortField != null) {
            Collections.sort(datasource, new LazySorter(sortField, sortOrder));
        }

        setRowCount(crudService.countTotalRecord(Event.TOTAL));
        logger.log(datasource.size() + "this is it");
        logger.log(Event.TOTAL + "  " + crudService.countTotalRecord("Event.countEventsTotal"));
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
     * Gets the event object's primary key
     * @param event
     * @return Object
     */
    @Override
    public Object getRowKey(Event event) {
        return event.getId().toString();
    }

    /**
     * Returns the event object at the specified position in datasource.
     * @return
     */
    @Override
    public Event getRowData() {
        if(datasource == null)
            return null;
        int index =  rowIndex % pageSize;
        if(index > datasource.size()){
            return null;
        }
        return datasource.get(index);
    }

    /**
     * Returns the event object that has the row key.
     * @param rowKey
     * @return
     */
    @Override
    public Event getRowData(String rowKey) {
        if(datasource == null)
            return null;
       for(Event event : datasource) {
           if(event.getId().toString().equals(rowKey))
           return event;
       }
       return null;
    }


    /*
     * ===== Getters and Setters of LazyEventDataModel fields
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
        this.datasource = (List<Event>) list;
    }

    /**
     * Returns wrapped data
     * @return
     */
    @Override
    public Object getWrappedData() {
        return datasource;
    }

    public List<Event> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<Event> datasource) {
        this.datasource = datasource;
    }

    public EventService getCrudService() {
        return crudService;
    }

    public void setCrudService(EventService crudService) {
        this.crudService = crudService;
    }
}

