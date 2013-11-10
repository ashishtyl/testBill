/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;


import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import com.is3102.entity.Employee;
import com.is3102.util.HandleDates;
import java.text.ParseException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import com.is3102.EntityClass.OutpatientAppointment;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import java.util.Date;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Ben
 */
@Stateful
public class OutPatientManagementBean implements OutPatientManagementRemote{
    @PersistenceContext
    EntityManager em;
    private Patient patient;
    private OutpatientAppointment appointment;
    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    String rememberPIN;

    public OutPatientManagementBean() {
       
    }

    @Override
    public String addPatient(String NRIC_PIN, String name, String birthday, String address, String cNumber, String nationality, String height, String weight, String gender, String bloodgroup) throws ExistException, ParseException, Exception {
        Date bDate = HandleDates.getDateFromString(birthday);
        System.out.println(bloodgroup);
        patient = em.find(Patient.class, NRIC_PIN);
        if (patient != null) // Patient Exists
        {
            throw new ExistException("PATIENT ALREADY EXISTS");
        }
        patient = new Patient();
        patient.create(NRIC_PIN, name, bDate, address, cNumber, nationality, height, weight, gender, bloodgroup);
        em.persist(patient);
        rememberPIN=NRIC_PIN;
        return patient.getNRIC_PIN();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    
    public String makeOutpatientAppointment(String NRIC_PIN, Date startDate, Date endDate) throws ExistException, ParseException {
     //public String makeOutpatientAppointment(String NRIC_PIN, String startDate, String endDate) throws ExistException, ParseException {    
        //Date aDate = HandleDates.getDateFromString(startDate);
       // Date bDate = HandleDates.getDateFromString(endDate);
        //Doctor doctor = new Doctor();
        //Employee doctor = em.find(Employee.class, id);
       // if (doctor == null) {
           // em.clear();
           // throw new ExistException("DOCTOR DOES NOT EXIST");
       // } else {
            patient = em.find(Patient.class, NRIC_PIN);
            if (patient == null) {
                em.clear();
                throw new ExistException("PATIENT DOES NOT EXIST");
            } else {
                appointment = new OutpatientAppointment();
                mCase mcase = new mCase();
                System.out.println("Test");
               // appointment.create(aDate, bDate);
                appointment.create(endDate, endDate);
                System.out.println("Appointment ID: " + appointment.getAppId());
                patient.getAppointment2().add(appointment);
                appointment.setPatient(patient);
                appointment.setmCase(mcase);
                em.persist(patient);
                em.persist(appointment);
                em.flush();
            }
   
        return (appointment.getAppId()).toString();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public long createCase(String appId, Date ScheduleAdmittedDate, String type) throws ExistException {
        mCase mcase;
        OutpatientAppointment appointment = em.find(OutpatientAppointment.class, new Long(appId));
        if (appointment == null) {
            throw new ExistException("CASE DOES NOT EXIST");
        }
        mcase = appointment.getmCase();
        mcase.create(ScheduleAdmittedDate, type);
        mcase.setType(type);
        appointment.setmCase(mcase);
        mcase.setPatient(appointment.getPatient());
            //em.persist(appointment);
        em.persist(mcase);
        em.flush();
        return mcase.getCIN().longValue();
    }

   

    @Override
    public mCase getmCase(String CIN) {
        mCase mcase = em.find(mCase.class, CIN);
        return mcase;
    }

    public List<OutpatientAppointment> getOutPatientAppointments(String NRIC_PIN) {
        Patient patient = em.find(Patient.class, NRIC_PIN);
        List appointmentList = (List) patient.getAppointment2();
        appointmentList.size();
        return appointmentList;

    }

   
    @Override
    public Employee getEmployee(int id) {
        return em.find(Employee.class, id);
    }

    @Override
    public Patient getPatient(String NRIC_PIN) {
        return em.find(Patient.class, NRIC_PIN);
    }

    @Override
    public void update(String NRIC_PIN, String newName, String newAddress, String newBirthday, String newNumber, String newWeight) throws ExistException, ParseException, Exception {
        Date bDate = HandleDates.getDateFromString(newBirthday);
        Patient patient = em.find(Patient.class, NRIC_PIN);
        patient.setName(newName);
        patient.setAddress(newAddress);
        patient.setBirthday(bDate);
        patient.setcNumber(newNumber);
        patient.setWeight(newWeight);
        em.merge(patient);
    }

 
}





