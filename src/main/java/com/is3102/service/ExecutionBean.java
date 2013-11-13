/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.ExecutionLog;
import com.is3102.EntityClass.Medical_Procedure;
import com.is3102.EntityClass.Nursing_Procedure;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.Vitals;
import com.is3102.Exception.ConsentException;
import com.is3102.Exception.ExistException;
import com.is3102.entity.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ashish
 */
@Stateless
public class ExecutionBean implements ExecutionRemote {

    @PersistenceContext()
    EntityManager em;

    public void AddExecutionRecordMedical(Long procedure_id, Long doctor_id, String exeuction_comment) throws ExistException, ConsentException {
        
        System.out.println("In ExcB: addMedicalExecution");
        Employee doctor = em.find(Employee.class, doctor_id);
        
        if (doctor == null) {
            em.clear();
            System.out.println("Doctor not found");
            throw new ExistException("Doctor not found!");
        } else {

            Medical_Procedure procedure = em.find(Medical_Procedure.class, procedure_id);
            if (procedure == null) {
                em.clear();
                throw new ExistException("No such procedure created");
            } else {
                System.out.println("In ExcB: procedure found");

                if (procedure.getPatientConsent().equalsIgnoreCase("NULL")) {
                    System.out.println("In ExcB: consent not received");
                    throw new ConsentException("Patient Consent not received!");
                } else {
                    System.out.println("Consent check passed!");

                    ExecutionLog eLog = new ExecutionLog();
                    eLog.create(doctor, exeuction_comment);
                    System.out.println("Log created");
                    procedure.addExecutionLog(eLog);
                    System.out.println("Log appended");
                }
            }
        }

        Medical_Procedure procedure = em.find(Medical_Procedure.class, procedure_id);
        
        if (procedure == null) {
            em.clear();
            throw new ExistException("No such procedure created");
        }
        
        System.out.println("In ExcB: medical procedure found");

        if (procedure.getPatientConsent().equalsIgnoreCase("NULL")) {
            System.out.println("In ExcB: consent not received");
            throw new ConsentException("Patient Consent not received!");
        }
    
        System.out.println("Consent check passed!");

        ExecutionLog eLog = new ExecutionLog();
        eLog.create(doctor, exeuction_comment);
        System.out.println("Log created");

        procedure.addExecutionLog(eLog);
        System.out.println("Log appended");

    }

    public void AddExecutionRecordNursing(Long procedure_id, Long employee_id, String exeuction_comment) throws ExistException {
        System.out.println("In ExcB: addNursingExecution");
        Employee doctor = em.find(Employee.class, employee_id);
        if (doctor
                == null) {
            em.clear();
            System.out.println("Doctor not found");
            throw new ExistException("Doctor not found!");
        } else {

            Nursing_Procedure procedure = em.find(Nursing_Procedure.class, procedure_id);
            if (procedure == null) {
                em.clear();
                System.out.println("Nursing procedure not found");
                throw new ExistException("No such procedure created");
            } else {

                ExecutionLog eLog = new ExecutionLog();
                eLog.create(doctor, exeuction_comment);
                System.out.println("Log created");
                procedure.addExecutionLog(eLog);
                System.out.println("Log appended");
            }
        }
    }

    public List<ExecutionLog> CreateEvaluationReport(Long procedure_id) throws ExistException {
        System.out.println("In ExcB: createEvaluationReport");
        Medical_Procedure procedure = em.find(Medical_Procedure.class, procedure_id);

        if (procedure
                == null) {
            em.flush();
            System.out.println("no preocdure found");
            throw new ExistException("No such procedure created!");
        } else {
            System.out.println("Procedure found");
            List<ExecutionLog> logs = procedure.getExecutionlogs();
            return logs;
        }
    }

    public void recordVitals(Long patientId, String bp, String tmp, String HR, String spO2, String glucose, String rRate) throws ExistException {
        System.out.println("In ExcB: record Vitals");
        Vitals vitals = new Vitals();
        Patient patient = em.find(Patient.class, patientId);
        if (patient == null) {
            throw new ExistException("Patient does not Exist!");
        }
        vitals.create(bp, tmp, HR, spO2, glucose, rRate);
        patient.getVitals().add(vitals);
        vitals.setPatient(patient);
        em.persist(vitals);
        em.persist(patient);
        System.out.println("Vitals persisted");
    }

    public List<Vitals> listVitals(Long id) {
        Patient patient = em.find(Patient.class, id);
        if (patient != null) {
            List<Vitals> vitals = patient.getVitals();
            System.out.println(vitals.size());
            return vitals;
        } else {
            return null;
        }
    }
}
