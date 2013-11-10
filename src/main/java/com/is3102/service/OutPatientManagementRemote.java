/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;


import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.OutpatientAppointment;
import com.is3102.EntityClass.mCase;
import javax.ejb.Remote;
import com.is3102.Exception.ExistException;
import com.is3102.entity.Employee;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Ben
 */

@Remote
public interface OutPatientManagementRemote {

    public String addPatient(String NRIC_PIN, String name, String birthday, String address, String cNumber, String nationality, String height, String weight, String gender, String bloodgroup) throws ExistException, ParseException, Exception;
    
   // public String makeOutpatientAppointment(String NRIC_PIN, String startDate, String endDate) throws ExistException, ParseException;
 public String makeOutpatientAppointment(String NRIC_PIN, Date startDate, Date endDate) throws ExistException, ParseException; 
    public long createCase(String appId, Date ScheduleAdmittedDate, String type) throws ExistException;

    public mCase getmCase(String CIN);

    public List<OutpatientAppointment> getOutPatientAppointments(String NRIC_PIN);
    
    public Employee getEmployee(int docId);
    
    public Patient getPatient(String NRIC_PIN);

    public void update(String NRIC_PIN, String newName, String newAddress, String newBirthday, String newNumber, String newWeight) throws ExistException, ParseException, Exception;


}
