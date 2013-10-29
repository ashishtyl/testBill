/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.Appointment;
import com.is3102.EntityClass.mCase;
import javax.ejb.Remote;
import com.is3102.Exception.ExistException;
import com.is3102.entity.Employee;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Swarit
 */
@Remote
public interface AdministrativeAdmissionRemote {

    public String addPatient(String NRIC_PIN, String name, String birthday, String address, String cNumber, String height, String weight, String gender, String bloodgroup) throws ExistException, ParseException, Exception;
    
    public String makeAppointment(String NRIC_PIN, String appDate, String place, int docId) throws ExistException, ParseException;

    public long createCase(String bedNo, String appId) throws ExistException;

    public List<Bed> getAvailBeds();

    public mCase getmCase(String CIN);

    public List<Appointment> getPatientAppointments(String NRIC_PIN);
    
    public Employee getEmployee(int docId);
    
    public Patient getPatient(String NRIC_PIN);

    public void update(String NRIC_PIN, String newName, String newAddress, String newBirthday, String newNumber, String newWeight) throws ExistException, ParseException, Exception;
}