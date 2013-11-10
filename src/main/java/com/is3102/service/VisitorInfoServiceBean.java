/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Bed;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.mCase;
import javax.ejb.Stateless;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.is3102.util.HandleDates;
import javax.persistence.NoResultException;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author Swarit
 */
@Stateless
public class VisitorInfoServiceBean implements VisitorInfoServiceRemote {

    @PersistenceContext
    EntityManager em;
    private Patient patient;
    private mCase mcase;

    public Bed retrievePatientInfo(Long patientId, String dateAdmitted) {
        //throws ExistException {

        String aDate;
        patient = em.find(Patient.class, patientId);
        //if(patient!=null) {
        List<mCase> mcases = (List<mCase>) patient.getmCases();
        for (mCase mcase : mcases) {
            aDate = HandleDates.convertDateToString(mcase.getDateAdmitted());
            if (aDate.equals(dateAdmitted)) {
                return mcase.getBed();
            } else {
                continue;
            }
        }
        return null;
        //throw new ExistException("PATIENT NOT FOUND!");
    }

    /* Count the number of patients admitted today */
    public int getTodaysAdmissions() {
        String aDate;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<mCase> mcases = getmCases();
        int i = 0;
        String s_date = "";
        StringBuilder dateR = new StringBuilder(dateFormat.format(date));
        s_date = dateR.toString();
        for (mCase mcase : mcases) {
            if (mcase.getDateAdmitted() != null) {
                aDate = HandleDates.convertDateToString(mcase.getDateAdmitted());
                if (aDate.equals(s_date)) {
                    i++;
                }
            }
        }
        return i;
    }
    /* Count the number of patients admitted this month */

    public int getCurrentPatients() throws ParseException {
        String date_begin = HandleDates.GetFirstDayOfMonth();
        Date dateBegin = HandleDates.getDateFromString(date_begin);
        String date_end = HandleDates.GetLastDayOfMonth();
        Date dateEnd = HandleDates.getDateFromString(date_end);
        String date_admitted;
        Date dateAdmitted;

        //Date date = new Date();
        List<mCase> mcases = getmCases();
        int i = 0;
        for (mCase mc : mcases) {
            if (mc.getDateAdmitted() != null) {
                date_admitted = HandleDates.convertDateToString(mc.getDateAdmitted());
                dateAdmitted = HandleDates.getDateFromString(date_admitted);
                if (dateAdmitted.compareTo(dateBegin) >= 0 && dateAdmitted.compareTo(dateEnd) < 0) {
                    i++;
                }
            }
        }
        return i;
    }

    /* Count the average duration of stay */
    public int getStayDuration() {
        List<mCase> mcases = getmCases();
        int counter = 0;
        int days = 0;
        int total_days = 0;
        System.out.println("Test1");
        for (mCase mc : mcases) {
            Date dateAdmitted = mc.getDateAdmitted();
            Date dateDischarged = mc.getDateDischarged();
            if (mc.getDateAdmitted() != null) {
                if (dateDischarged != null) {
                    counter++;
                    days = Days.daysBetween(new DateTime(dateAdmitted), new DateTime(dateDischarged)).getDays();
                    total_days += days;
                }
            }
        }
        if (counter != 0) {
            return (total_days / counter);
        } else {
            return 0;
        }
        //}
    }

    public Patient getPatient(String name, String passport_NRIC) {
        try {
            Query q = null;
            if (passport_NRIC.length() == 0) {
                q = em.createQuery("SELECT p FROM Patient p WHERE p.name = :name");
                q.setParameter("name", name);
            } else {
                q = em.createQuery("SELECT p FROM Patient p WHERE p.name = :name AND p.passport_NRIC = :passport_NRIC");
                q.setParameter("name", name);
                q.setParameter("passport_NRIC", passport_NRIC);
            }
            patient = (Patient) q.getSingleResult();
            return patient;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<mCase> getmCases() {
        Query q = em.createQuery("SELECT m FROM mcase m");
        List mCaseList = new ArrayList();
        for (Object o : q.getResultList()) {
            mCase mcase = (mCase) o;
            mCaseList.add(mcase);

        }
        mCaseList.size();
        return mCaseList;
    }
}