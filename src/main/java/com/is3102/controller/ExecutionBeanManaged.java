/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;

import com.is3102.EntityClass.ExecutionLog;
import com.is3102.service.ExecutionRemote;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author AshishLong pId = Long.valueOf(procedure_id);
 */
@ManagedBean
public class ExecutionBeanManaged implements Serializable {

    @EJB
    private ExecutionRemote ex;
    //Id of procedure instance associate with a case
    private String procedure_id;
    //Doctor id
    private String doctor_id;
    //Execution comment documented at the time of completion
    private String exeuction_comment;
    //list of execution log comments associated with one procedure entity
    private List<ExecutionLog> executionLogList;

    public void doAddExecutionLog() {
        
        
        Long pId = Long.valueOf(procedure_id);
        Long dId = Long.valueOf(doctor_id);
        try {
            ex.AddExecutionRecordMedical(pId, dId, exeuction_comment);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*public void AddExecutionRecord(Long procedure_id, Long doctor_id, String exeuction_comment) throws ExistException;

     public List<ExecutionLog> CreateEvaluationReport(Long procedure_id) throws ExistException;*/

    public void DoCreateEvaluationReport() {
        try {
            
            Long pId = Long.valueOf(procedure_id);
            List<ExecutionLog> result = ex.CreateEvaluationReport(pId);
            this.setExecutionLogList(result);
        
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getProcedure_id() {
        return procedure_id;
    }

    public void setProcedure_id(String procedure_id) {
        this.procedure_id = procedure_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getExeuction_comment() {
        return exeuction_comment;
    }

    public void setExeuction_comment(String exeuction_comment) {
        this.exeuction_comment = exeuction_comment;
    }

    public List<ExecutionLog> getExecutionLogList() {
        return executionLogList;
    }

    public void setExecutionLogList(List<ExecutionLog> executionLogList) {
        this.executionLogList = executionLogList;
    }
}
