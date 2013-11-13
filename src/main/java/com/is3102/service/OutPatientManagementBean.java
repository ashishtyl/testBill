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
import java.util.Calendar;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import java.util.Date;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author Ben
 */
@Stateless
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

   /* @Override
    public String addPatient(String NRIC_PIN, String name, String birthday, String address, String cNumber, String nationality, String height, String weight, String gender, String bloodgroup) throws ExistException, ParseException, Exception {
        Date bDate = HandleDates.getDateFromString(birthday);
      try {
            Query q = em.createQuery("SELECT p FROM Patient p WHERE p.passport_NRIC = :NRIC_PIN");
            q.setParameter("NRIC_PIN", NRIC_PIN);
            patient = (Patient) q.getSingleResult();
            if (patient != null) // Patient Exists
            {
                throw new ExistException("PATIENT ALREADY EXISTS, You may already booked an appointment");
                //return "existed";
            }
        } catch (NoResultException ex) {
            System.out.println("PATIENT DOES NOT EXIST");
            patient = new Patient();
            patient.create(NRIC_PIN, name, bDate, address, cNumber, nationality, height, weight, gender, bloodgroup);
            em.persist(patient);
            return patient.getPassport_NRIC();
        }
        return null;
    }*/

     @Override
    public String addPatient(String NRIC_PIN, String name, String birthday, String address, String cNumber, String nationality, String height, String weight, String gender, String bloodgroup) throws ExistException, ParseException, Exception {
            Date bDate = HandleDates.getDateFromString(birthday);
     try {
            Query q = em.createQuery("SELECT p FROM Patient p WHERE p.passport_NRIC = :NRIC_PIN");
            q.setParameter("NRIC_PIN", NRIC_PIN);
            patient = (Patient) q.getSingleResult();
            if (patient != null){ // Patient Exists
               //throw new ExistException("PATIENT ALREADY EXISTS, You may already booked an appointment");
               return "existed";
            }
     }catch (NoResultException ex) {
           
               System.out.println("PATIENT DOES NOT EXIST");
               patient = new Patient();
               patient.create(NRIC_PIN, name, bDate, address, cNumber, nationality, height, weight, gender, bloodgroup);
               em.persist(patient);
               return patient.getPassport_NRIC();
        }
       return null;
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
           // patient = em.find(Patient.class, NRIC_PIN);
              Calendar calA = Calendar.getInstance();
              System.out.println("****\n");
              Calendar calB = Calendar.getInstance();
              calA.setTime(startDate);// all done
              calB.setTime(endDate);// all done
              calA.add(Calendar.HOUR, 1);
              int compareResult = calB.compareTo(calA);
              System.out.println(compareResult);
            
        Query q = em.createQuery("SELECT p FROM Patient p WHERE p.passport_NRIC = :NRIC_PIN");
                q.setParameter("NRIC_PIN", NRIC_PIN);
                patient = (Patient) q.getSingleResult();
            if ( startDate == null || endDate == null ||compareResult!=0) {
                em.clear();
                throw new ExistException("Date does not been identified or appointment duration is not an hour");
                
            } else {
                appointment = new OutpatientAppointment();
                mCase mcase = new mCase();
                System.out.println("Test");
               // appointment.create(aDate, bDate);
                appointment.create(startDate, endDate);
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
        appointment.getPatient().getMcase().add(mcase);
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
      
        Query q = em.createQuery("SELECT p FROM Patient p WHERE p.passport_NRIC = :NRIC_PIN");
        q.setParameter("NRIC_PIN", NRIC_PIN);
        patient = (Patient) q.getSingleResult();
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





