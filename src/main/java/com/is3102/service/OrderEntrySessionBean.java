/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Appointment;
import com.is3102.EntityClass.AppointmentProcedure;
import com.is3102.EntityClass.Device;
import com.is3102.EntityClass.DrugCatalog;
import com.is3102.EntityClass.LabRadProcedure;
import com.is3102.EntityClass.Medication;
import com.is3102.EntityClass.POEOrder;
import com.is3102.EntityClass.Patient;
import com.is3102.EntityClass.ServiceCatalog;
import com.is3102.EntityClass.mCase;
import com.is3102.Exception.DeviceException;
import com.is3102.Exception.DrugException;
import com.is3102.Exception.ExistException;
import com.is3102.Exception.ProcedureException;
import com.is3102.util.HandleDates;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
public class OrderEntrySessionBean implements OrderEntryRemote {

    @PersistenceContext
    EntityManager em;
    //private Medication medication;
    //private POEOrder poeOrder;
    List<Medication> medications = new ArrayList<Medication>();

    public OrderEntrySessionBean() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String prescribeMedication(String CIN, String name, Long dosage, int quantity, String details) throws DrugException, Exception {
        mCase mcase = em.find(mCase.class, new Long(CIN));
        if (mcase == null) {
            em.clear();
            throw new ExistException("CASE DOES NOT EXIST");
        } else {
            Medication medication = new Medication();
            Query q = em.createQuery("SELECT dc FROM DrugCatalog dc WHERE dc.name = :name");
            q.setParameter("name", name);
            DrugCatalog drug = (DrugCatalog) q.getSingleResult();
            System.out.println("DRUG NAME: " + drug.getName());
            double unitPrice = drug.getPrice();
            double totalPrice = quantity * unitPrice;
            if (!checkDrugOverDose(name, dosage)) {
                if (checkDrugInteraction(CIN, name)) {
                    throw new DrugException("Patient is already using a medicine which reacts with " + name + "!");
                } else {
                    if (checkDrugAllergy(CIN, name)) {
                        throw new DrugException("Patient is allergic to " + name + "!");
                    } else {
                        POEOrder order = new POEOrder();
                        Date dateOrdered = new Date();
                        System.out.println(dateOrdered);
                        order.create(dateOrdered);
                        medication.create(name, dosage, quantity, details, totalPrice);
                        mcase.getMedication().add(medication);
                        medication.setMcase(mcase);
                        order.setMedication(medication);
                        mcase.getOrders().add(order);
                        order.setMcase(mcase);
                        em.persist(order);
                        em.persist(medication);
                        em.persist(mcase);
                        em.flush();
                    }
                }
            } else {
                throw new DrugException("Drug Overdose!");
            }
            return (medication.getName());
        }
    }

    public boolean checkDrugOverDose(String name, Long dosage) {
        Query q = em.createQuery("SELECT dc FROM DrugCatalog dc WHERE dc.name = :name");
        q.setParameter("name", name);
        DrugCatalog drug = (DrugCatalog) q.getSingleResult();
        if (dosage > drug.getRecommendedDosage()) {
            System.out.println("OVERDOSE!");
            return true;
        }
        return false;
    }

    public boolean checkDrugInteraction(String CIN, String name) {
        Query q = em.createQuery("SELECT dc FROM DrugCatalog dc WHERE dc.name = :name");
        q.setParameter("name", name);
        DrugCatalog drug = (DrugCatalog) q.getSingleResult();
        String category = drug.getCategory();
        mCase mcase = em.find(mCase.class, new Long(CIN));
        List<Medication> medication = mcase.getMedication();
        for (Medication mc : medication) {
            String drugName = mc.getName();
            Query qdc = em.createQuery("SELECT dc FROM DrugCatalog dc WHERE dc.name = :name");
            qdc.setParameter("name", drugName);
            DrugCatalog drugc = (DrugCatalog) qdc.getSingleResult();
            if (category.equals(drugc.getCategory())) {
                if (drug.getName().equalsIgnoreCase(drugc.getName())) {
                    continue;
                } else {
                    System.out.println("DRUG-DRUG INTERACTION!");
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean checkDrugAllergy(String CIN, String name) {
        mCase mcase = em.find(mCase.class, new Long(CIN));
        String allergies = mcase.getMedicalAnamnesis().getAllergies();
        System.out.println(allergies);
        List<String> allergyList = Arrays.asList(allergies.split("[\\s,;]+"));
        System.out.println(allergyList.toString());
        for (String str : allergyList) {
            if (name.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String orderLabRadProcedure(String CIN, String name, int quantity, String details, String appDate) throws ExistException, ProcedureException, ParseException, DeviceException {
        String procedureType = "MRI";
        Date aDate = HandleDates.getDateFromString2(appDate);
        mCase mcase = em.find(mCase.class, new Long(CIN));
        if (mcase == null) {
            em.clear();
            throw new ExistException("CASE DOES NOT EXIST");
        } else {
            LabRadProcedure labradprocedure = new LabRadProcedure();
            Query q = em.createQuery("SELECT sc FROM ServiceCatalog sc WHERE sc.name = :name");
            q.setParameter("name", name);
            ServiceCatalog service = (ServiceCatalog) q.getSingleResult();
            double unitPrice = service.getPrice();
            double totalPrice = quantity * unitPrice;
            if (checkProcedureSafety(CIN, name)) {

                if (checkDeviceAvailability(procedureType, aDate) == null) {

                    throw new DeviceException("No device available at given appointment time");
                }
                Long deviceID = checkDeviceAvailability(procedureType, aDate);
                Device device = em.find(Device.class, new Long(deviceID));

                POEOrder order = new POEOrder();
                Date dateOrdered = new Date();
                order.create(dateOrdered);
                labradprocedure.create(name, quantity, details, totalPrice);
                mcase.getLabRadProcedure().add(labradprocedure);
                labradprocedure.setMcase(mcase);
                AppointmentProcedure ap = new AppointmentProcedure();
                System.out.println(aDate.toString());
                ap.create(aDate);
                device.addProcedure(ap);
                order.setaProcedure(ap);
                order.setLabRadProcedure(labradprocedure);
                mcase.getOrders().add(order);
                order.setMcase(mcase);
                em.persist(ap);
                em.persist(device);
                em.persist(order);
                em.persist(labradprocedure);
                em.persist(mcase);
                em.flush();
            } else {
                throw new ProcedureException("Procedure cannot be ordered due to pregnancy!");
            }
            return (labradprocedure.getName());
        }
    }

    public boolean checkProcedureSafety(String CIN, String name) {
        Query q = em.createQuery("SELECT sc FROM ServiceCatalog sc WHERE sc.name = :name");
        q.setParameter("name", name);
        ServiceCatalog service = (ServiceCatalog) q.getSingleResult();
        Boolean safe = service.isSafeForPregnant();
        System.out.println("Procedure safe? " + safe.toString());
        mCase mcase = em.find(mCase.class, new Long(CIN));
        Boolean pregnant = mcase.getMedicalAnamnesis().isIsPregnant();
        System.out.println(pregnant.toString());
        if (safe) {
            return true;
        } else if (!safe) {
            if (!pregnant) {
                return true;
            }
        }
        return false;
    }

    public Long checkDeviceAvailability(String procedureType, Date appDate) {
        Long deviceID = null;
        long HOUR = 3600 * 1000;
        boolean booked = false;
        Query qdc = em.createQuery("SELECT dc FROM Device dc WHERE dc.deviceType=:procedureType");
        qdc.setParameter("procedureType", procedureType);
        List<Device> devices = qdc.getResultList();
        System.out.println("Device list size: " + devices.size());
        for (Device d : devices) {
            booked = false;
            System.out.println("inside device loop");
            List<AppointmentProcedure> aps = d.getProcedures();
            System.out.println("aps list called");
            if (aps.isEmpty()) {
                return d.getId();
            }
            System.out.println("Appointment list size: " + aps.size());
            for (AppointmentProcedure a : aps) {
                System.out.println("inside appointment list loop");
                if ((a.getAppDate().compareTo(appDate)) == 0) {
                    booked = true;
                    break;
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(a.getAppDate());
                cal.add(Calendar.MINUTE, 20);
                if (appDate.after(a.getAppDate()) && appDate.before(cal.getTime())) {
                    booked = true;
                    break;
                }
            }
            if (booked == false) {
                deviceID = d.getId();
                return deviceID;
            }
        }
        return deviceID;
    }

    public List<DrugCatalog> displayDrugCatalog() {
        Query qdc = em.createQuery("SELECT dc FROM DrugCatalog dc");
        List<DrugCatalog> drugCatalog = qdc.getResultList();
        System.out.println(drugCatalog.size());
        return drugCatalog;
    }

    public List<ServiceCatalog> displayServiceCatalog() {
        Query qdc = em.createQuery("SELECT dc FROM ServiceCatalog dc");
        List<ServiceCatalog> serviceCatalog = qdc.getResultList();
        System.out.println(serviceCatalog.size());
        return serviceCatalog;
    }

    public List<Medication> listMedication(String CIN) {
        mCase mcase = em.find(mCase.class, new Long(CIN));
        if (mcase != null) {
            List<Medication> medication = mcase.getMedication();
            System.out.println(medication.size());
            return medication;
        } else {
            return null;
        }
    }

    public List<LabRadProcedure> listLabRadProcedures(String CIN) {
        mCase mcase = em.find(mCase.class, new Long(CIN));
        if (mcase != null) {
            List<LabRadProcedure> labradProc = mcase.getLabRadProcedure();
            System.out.println(labradProc.size());
            return labradProc;
        } else {
            return null;
        }
    }

    public List<POEOrder> listPatientOrders(String patientId) {
        Patient patient = em.find(Patient.class, new Long(patientId));
        String birthday = HandleDates.convertDateToString(patient.getBirthday());
        System.out.println("Birthday: " + birthday);
        int age = HandleDates.getAge(birthday);
        System.out.println("Age: " + age);
        Appointment appointment;
        if (patient != null) {
            List<Appointment> appointments = patient.getAppointments();
            System.out.println(appointments.size());
            if (!appointments.isEmpty()) {
                appointment = appointments.get(appointments.size() - 1);
            } else {
                return null;
            }
            List<POEOrder> orders = appointment.getmCase().getOrders();
            System.out.println(orders.size());
            return orders;
        } else {
            return null;
        }
    }
}