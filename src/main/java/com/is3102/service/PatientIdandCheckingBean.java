/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Appointment;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.mCase;
import com.is3102.util.HandleDates;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PatientIdandCheckingBean implements PatientIdandCheckingRemote {

    @PersistenceContext
    EntityManager em;
    private Patient patient;
    //private AdministrativeAdmissionBean adminadm;

    public PatientIdandCheckingBean() {
    }

    public boolean checkRecurrence(String NRIC_PIN) {

        patient = em.find(Patient.class, NRIC_PIN);
        if (patient == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkAppointment(String NRIC_PIN, Date appDate) {

        String aDate;
        String dateApp = HandleDates.convertDateToString(appDate);
        patient = em.find(Patient.class, NRIC_PIN);
        if (patient.getAppointments().isEmpty()) {
            return false;
        } else {
            Collection<Appointment> appointment = patient.getAppointments(); //get all appointments for a patient
            for (Appointment appt : appointment) {
                aDate = HandleDates.convertDateToString(appt.getAppDate());
                if (aDate.equals(dateApp)) {
                    System.out.println("Yes");
                    return true;
                }
            }
            return false;
        }
    }

    public Patient getPatientInfo(String NRIC_PIN) {

        Patient p = em.find(Patient.class, NRIC_PIN);
        return p;
    }

    public List<Appointment> getPatientAppointments(String NRIC_PIN, String appDate) {
        String aDate;
        System.out.println(appDate);
        Patient patient = em.find(Patient.class, NRIC_PIN);
        List<Appointment> appointmentList = (List) patient.getAppointments();
        List apps = new ArrayList();
        for (Appointment app : appointmentList) {
            aDate = HandleDates.convertDateToString(app.getAppDate());
            if (aDate.equals(appDate)) {
                System.out.println(aDate);
                apps.add(app);
            }
        }
        return apps;
    }

    public Patient getPatient(String NRIC_PIN) {
        return em.find(Patient.class, NRIC_PIN);
    }

    public List<mCase> getPatientCases(String NRIC_PIN) {
        Patient patient = em.find(Patient.class, NRIC_PIN);
        List mCaseList = (List) patient.getmCases();
        return mCaseList;
    }
}