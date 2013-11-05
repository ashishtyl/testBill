/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Appointment;
import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.ExistException;
import com.is3102.entity.Employee;
import com.is3102.util.HandleDates;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Swarit
 */
@Stateless
public class AdministrativeAdmissionBean implements AdministrativeAdmissionRemote {

    @PersistenceContext
    EntityManager em;
    private Patient patient;
    private Appointment appointment;

    public AdministrativeAdmissionBean() {
    }

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
        return patient.getNRIC_PIN();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String makeAppointment(String NRIC_PIN, String appDate, int id) throws ExistException, ParseException {
        Date aDate = HandleDates.getDateFromString(appDate);
        //Doctor doctor = new Doctor();
        Employee doctor = em.find(Employee.class, id);
        if (doctor == null) {
            em.clear();
            throw new ExistException("DOCTOR DOES NOT EXIST");
        } else {
            Patient patient = em.find(Patient.class, NRIC_PIN);
            if (patient == null) {
                em.clear();
                throw new ExistException("PATIENT DOES NOT EXIST");
            } else {
                appointment = new Appointment();
                mCase mcase = new mCase();
                System.out.println("Test");
                appointment.create(aDate);
                doctor.getAppointments().add(appointment);
                System.out.println("Appointment ID: " + appointment.getAppId());
                appointment.setEmployee(doctor);
                patient.getAppointments().add(appointment);
                appointment.setPatient(patient);
                appointment.setmCase(mcase);
                em.persist(doctor);
                em.persist(patient);
                em.persist(appointment);
                em.flush();
            }
        }
        return (appointment.getAppId()).toString();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public long createCase(String bedNo, String appId, String type) throws ExistException {
        mCase mcase;
        Appointment appointment = em.find(Appointment.class, new Long(appId));
        if (appointment == null) {
            throw new ExistException("CASE DOES NOT EXIST");
        }
        mcase = appointment.getmCase();
        Date dateAdmitted = new Date();
        mcase.create(dateAdmitted, type);

        Bed bed = em.find(Bed.class, new Long(bedNo));
        if (bed == null) { // Bed does not exist
            em.clear();
            throw new ExistException("BED DOES NOT EXIST");
        }
        if (mcase.getBed() == null) {
            mcase.setBed(bed);
            appointment.setmCase(mcase);
            mcase.setPatient(appointment.getPatient());
            //em.persist(appointment);
            em.persist(mcase);
            em.flush();
        } else {
            throw new ExistException("CASE ALREADY EXISTS");
        }
        return mcase.getCIN().longValue();
    }

    //Get available beds
    public List<Bed> getAvailBeds() {
        List bedList = new ArrayList();
//        List<Bed> bedList = new ArrayList<Bed>();
        try {
            Query qb = em.createQuery("SELECT b FROM Bed b");
            List<Bed> beds = qb.getResultList();
            Query qc = em.createQuery("SELECT m FROM mcase m");
            List<mCase> mcases = qc.getResultList();

            for (Bed bed : beds) {
                Boolean available = true;

                for (mCase mc : mcases) {
                    if (mc.getDateDischarged() == null) {
                        if (mc.getBed() != null) {
                            if (mc.getBed().getBedNo().equals(bed.getBedNo())) {
                                available = false;
                                break;
                            }
                        }
                    }
                }
                if (available) {
                    bedList.add(bed);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bedList.size();
        return bedList;
    }

    public mCase getmCase(String CIN) {
        mCase mcase = em.find(mCase.class, CIN);
        return mcase;
    }

    public List<Appointment> getPatientAppointments(String NRIC_PIN) {
        Patient patient = em.find(Patient.class, NRIC_PIN);
        List appointmentList = (List) patient.getAppointments();
        appointmentList.size();
        return appointmentList;

    }

    /* public void UpdatePatientInfo() throws Exception {
     } */
    public Employee getEmployee(int id) {
        return em.find(Employee.class, id);
    }

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