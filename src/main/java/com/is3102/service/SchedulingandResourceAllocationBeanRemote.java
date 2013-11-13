/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;


import com.is3102.EntityClass.Schedule;
import com.is3102.Exception.ExistException;
import com.is3102.entity.Employee;

import javax.ejb.Remote;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Ashish
 */
@Remote
public interface SchedulingandResourceAllocationBeanRemote {
    
    public Long getDoctorID(String name) throws ExistException;

    public void createShift(String shiftDate, String shiftCode) throws ExistException, ParseException;

    public void DisplayPatientInfo() throws Exception;

    public List<Schedule> viewShifts() throws ExistException;
    
    public void addEmployee(String name) throws ExistException, ParseException;
    
    public String getEmployeeName(int id) throws ExistException;
    public boolean getShiftsEmployeeBoolean(int doctorID) throws ExistException;
//public int getEmployeeID(String name) throws ExistException;

public List<Employee> getAvailableEmployees(String appointmentDate, String appointmentTime) throws ParseException, ExistException;
public List<Employee> getEmployees() throws ExistException;
public List<Employee> getEmployees(Long shiftID);
public void assignShiftEmployee(int doctorId, String shiftDate, String shiftCode) throws ExistException, ParseException;
public List<Schedule> getShiftsEmployee(int doctorID) throws ExistException;
}

