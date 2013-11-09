/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Appointment;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.mCase;
import com.is3102.util.HandleDates;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public boolean checkRecurrence(String name, String passport_NRIC) {
        try {
            Query q = em.createQuery("SELECT p FROM Patient p WHERE p.name = :name AND p.passport_NRIC = :passport_NRIC");
            q.setParameter("name", name);
            q.setParameter("passport_NRIC", passport_NRIC);
            patient = (Patient) q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    public boolean checkAppointment(String name, String passport_NRIC, Date appDate) {
        String aDate;
        String dateApp = HandleDates.convertDateToString(appDate);
        try {
            Query q = em.createQuery("SELECT p FROM Patient p WHERE p.name = :name AND p.passport_NRIC = :passport_NRIC");
            q.setParameter("name", name);
            q.setParameter("passport_NRIC", passport_NRIC);
            patient = (Patient) q.getSingleResult();
            if (patient.getAppointments().isEmpty()) {
                return false;
            } else {
                List<Appointment> appointment = patient.getAppointments(); //get all appointments for a patient
                for (Appointment appt : appointment) {
                    aDate = HandleDates.convertDateToString(appt.getAppDate());
                    if (aDate.equals(dateApp)) {
                        return true;
                    }
                }
                return false;
            }
        } catch (NoResultException ex) {
            return false;
        }
    }

    public Patient getPatientInfo(String name, String passport_NRIC) {
        try {
            Query q = em.createQuery("SELECT p FROM Patient p WHERE p.name = :name AND p.passport_NRIC = :passport_NRIC");
            q.setParameter("name", name);
            q.setParameter("passport_NRIC", passport_NRIC);
            patient = (Patient) q.getSingleResult();
            return patient;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Appointment> getPatientAppointments(String name, String passport_NRIC, String appDate) {
        String aDate;
        Query q = em.createQuery("SELECT p FROM Patient p WHERE p.name = :name AND p.passport_NRIC = :passport_NRIC");
        q.setParameter("name", name);
        q.setParameter("passport_NRIC", passport_NRIC);
        patient = (Patient) q.getSingleResult();
        List<Appointment> appointmentList = (List) patient.getAppointments();
        List apps = new ArrayList();
        for (Appointment app : appointmentList) {
            aDate = HandleDates.convertDateToString(app.getAppDate());
            if (aDate.equals(appDate)) {
                apps.add(app);
            }
        }
        return apps;
    }

    public Patient getPatient(String NRIC_PIN) {
        return em.find(Patient.class, NRIC_PIN);
    }

    public List<mCase> getPatientCases(String name, String passport_NRIC) {
        try {
            Query q = em.createQuery("SELECT p FROM Patient p WHERE p.name = :name AND p.passport_NRIC = :passport_NRIC");
            q.setParameter("name", name);
            q.setParameter("passport_NRIC", passport_NRIC);
            patient = (Patient) q.getSingleResult();
            List mCaseList = (List) patient.getmCases();
            return mCaseList;
        } catch (NoResultException ex) {
            return null;
        }
    }
}