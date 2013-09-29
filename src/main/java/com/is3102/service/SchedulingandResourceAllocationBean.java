/*
/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.service;

import com.is3102.EntityClass.Doctor;
import com.is3102.EntityClass.Schedule;
import com.is3102.Exception.ExistException;
import com.is3102.entity.Employee;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ashish
 */
@Stateless
public class SchedulingandResourceAllocationBean implements SchedulingandResourceAllocationBeanLocal, SchedulingandResourceAllocationBeanRemote {



    @PersistenceContext()
    EntityManager em;
    Schedule schedule;
    Schedule existingS;
    Doctor doctor;
    Employee employee;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addDoctor(String name, String username, String dob) throws ExistException, ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             sdf.setLenient(false);   
                // we will now try to parse the string into date form
                
                Date testDate = sdf.parse(dob);
        
        Query q = em.createQuery("SELECT d FROM doctor d where d.Name=:name");
        q.setParameter("name", name);
        List result;
        result = q.getResultList();
        if (!(result.isEmpty()))
            throw new ExistException("DOCTOR WITH SAME NAME ALREADY EXISTS");
        doctor =  new Doctor();
        doctor.create(name, username, dob);
        em.persist(doctor);
        System.out.println("Doctor " + name + " successfully added.");
    }

    public String getDoctorName(Long id) throws ExistException {
        doctor = em.find(Doctor.class, id);
        if (doctor == null)
            throw new ExistException("DOCTOR ID DOES NOT EXIST.");

        return doctor.getName();
    }
    
    public Long getDoctorID(String name) throws ExistException{
        Long doctorID=null;
        Query q=em.createQuery("SELECT d.docId from doctor d where d.Name=:name");
        q.setParameter("name", name);
        List result;
        result = q.getResultList();
        if ((result.isEmpty()))
            throw new ExistException("DOCTOR WITH NAME " +name+ " DOES NOT EXIST");
        for (Object o: result){
            doctorID = (Long)o;
        }
        return doctorID;
        
    }

    public List<Doctor> getAvailableDoctors(String appointmentDate, String appointmentTime) throws ExistException, ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
                // we will now try to parse the string into date form
                sdf.setLenient(false);
                Date testDate = sdf.parse(appointmentDate);
        String pattern = "HH:mm";


        SimpleDateFormat sdf2 = new SimpleDateFormat(pattern);

        String shiftCode=null;

        Date sA = sdf2.parse("00:00");
        Date eA= sdf2.parse("07:59");
        Date sB= sdf2.parse("08:00");
        Date eB= sdf2.parse("15:59");
        Date sC = sdf2.parse("16:00");
        Date eC= sdf2.parse("23:59");

        Date appTime = sdf2.parse(appointmentTime);


        if((appTime.compareTo(eA)<= 0) && (appTime.compareTo(sA)>= 0)){
            shiftCode="A";
        }
        else if((appTime.compareTo(eB)<= 0) && (appTime.compareTo(sB)>= 0)){
            shiftCode="B";
        }
        else if((appTime.compareTo(eC)<= 0) && (appTime.compareTo(sC)>= 0)){
            shiftCode="C";
        }




        Query q = em.createQuery
                ("SELECT s FROM Schedule s WHERE s.shiftDate = :appointmentDate AND s.shiftCode=:shiftCode");
        q.setParameter("appointmentDate", appointmentDate);
        q.setParameter("shiftCode", shiftCode);
        List result;
        result = q.getResultList();
        if ((result.isEmpty()==true)){
            em.clear();

            throw new ExistException( "THERE ARE NO SHIFTS CREATED FOR THE GIVEN TIME AND DATE");
        }
        for (Object o: result){
            schedule = (Schedule)o;
        }
        
        Long shiftId = schedule.getId();
        
        List<Doctor> docList = this.getDoctors(shiftId);
        if(docList.isEmpty()){
            throw new ExistException("NO DOCTORS HAVE BEEN ASSIGNED TO GIVEN SHIFT");
        }
        
        return this.getDoctors(shiftId);


    }

    public List<Doctor> getDoctors() throws ExistException {

        Query q = em.createQuery("SELECT c FROM doctor c");
       /* List docList = new ArrayList();
        for (Object o: q.getResultList()) {
            Doctor doc = (Doctor)o;
            docList.add(doc);
        }*/
        
        List docList = (List)q.getResultList();
        docList.size();
        
        if (docList.isEmpty()==true){
            throw new ExistException("THERE ARE NO DOCTORS IN THE DATABASE");
        }



        return docList;
    }

    public List<Doctor> getDoctors(Long shiftID){
        Query q = em.createQuery("SELECT d from doctor d left join d.schedules as s where s.id = :shiftID");
        q.setParameter("shiftID", shiftID);
        List scheduleList = q.getResultList();
        return scheduleList;
    }

    public List<Schedule> getShifts(Long doctorID) throws ExistException{
        
        doctor = em.find(Doctor.class, doctorID);
        if (doctor == null)
            throw new ExistException("DOCTOR ID DOES NOT EXIST.");
        
        Query q = em.createQuery("SELECT s from Schedule s left join s.doctors as d where d.docId = :doctorID");
        q.setParameter("doctorID", doctorID);
        List scheduleList = q.getResultList();
        //   List scheduleList = new ArrayList();
     /*   for (Object o: q.getResultList()) {
            Schedule sch = (Schedule)o;
            scheduleList.add(sch);
        }*/
        if (scheduleList.isEmpty()==true){
            throw new ExistException("THERE ARE NO SHIFTS ASSIGNED TO THIS DOCTOR");
        }
      
        return scheduleList;

    }

    public void assignShift (Long doctorId, String shiftDate, String shiftCode) throws ExistException, ParseException {

        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
                // we will now try to parse the string into date form
                sdf.setLenient(false);
                Date testDate = sdf.parse(shiftDate);
        
        
        Query q = em.createQuery
                ("SELECT s FROM Schedule s WHERE s.shiftDate = :shiftDate AND s.shiftCode =:shiftCode");
        q.setParameter("shiftDate", shiftDate);
        q.setParameter("shiftCode", shiftCode);
        List result;
        result = q.getResultList();
        if ((result.isEmpty())){
            em.clear();
            throw new ExistException("SHIFT DOES NOT EXIST");
        }

        for (Object o: result){
            schedule = (Schedule)o;
        }

        doctor = em.find(Doctor.class, doctorId);
        if (doctor == null){
            throw new ExistException("DOCTOR DOES NOT EXIST");
        }
        List existingSch = new ArrayList();
        try{
        existingSch = this.getShifts(doctorId);
        }catch (ExistException ex){
            
        }
        
        if(existingSch.isEmpty()==true){
            doctor.addSchedule(schedule);
            schedule.addDoctor(doctor);
            em.persist(schedule);
            em.persist(doctor);
        }
        else{
            for(Object p: existingSch){
                existingS = (Schedule)p;
                if(schedule.getShiftDate().compareTo(existingS.getShiftDate())==0  && schedule.getShiftCode().compareTo(existingS.getShiftCode())==0){
                    throw new ExistException("SHIFT ALREADY ASSIGNED TO DOCTOR");
                }
            }
            doctor.addSchedule(schedule);
            schedule.addDoctor(doctor);
            em.persist(schedule);
            em.persist(doctor);
        }


    }

    public void createShift(String shiftDate, String shiftCode) throws ExistException,ParseException{
        
        
        
        if(shiftCode.compareTo("A")==0){
            System.out.println("Valid shift A code entered.");
        }else if(shiftCode.compareTo("B")==0){
            System.out.println("Valid shift B code entered.");
        }else if (shiftCode.compareTo("C")==0){
            System.out.println("Valid shift B code entered.");
        }else throw new ExistException("INVALID SHIFT CODE: Please Enter A, B, or C");
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
                // we will now try to parse the string into date form
                sdf.setLenient(false);
                Date testDate = sdf.parse(shiftDate);
                
                System.out.println(" Date " + testDate.toString());
        
                
                
        Query q = em.createQuery("SELECT c FROM Schedule c where c.shiftDate=:shiftDate AND c.shiftCode=:shiftCode");
        q.setParameter("shiftDate", shiftDate);
        q.setParameter("shiftCode", shiftCode);
        List result;
        result = q.getResultList();
        if (!(result.isEmpty()))
            throw new ExistException("SHIFT ALREADY CREATED.");

        schedule = new Schedule();
        schedule.create(shiftDate, shiftCode);
        em.persist(schedule);
    }

    public List<Schedule> viewShifts() throws ExistException {
        Query q = em.createQuery("SELECT c FROM Schedule c");
        List <Schedule> scheduleList = new ArrayList<Schedule>();
        for (Object o: q.getResultList()) {
            Schedule sch = (Schedule)o;
            scheduleList.add(sch);
        }
        //System.out.println("schedule size is " + scheduleList.size());
        if (scheduleList.isEmpty()){
            throw new ExistException("THERE ARE NO SCHEDULES IN THE DATABASE");
        }
        System.out.println("schedule size is " + scheduleList.size());
      /*  else{
            Schedule e = (Schedule)scheduleList.get(0);
            System.out.println(e.getClass());
        }
        try{
            return scheduleList;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }*/
        return scheduleList;
    }

    public void DisplayPatientInfo() throws Exception {

    }
    
    public void addEmployee(String name) throws ExistException, ParseException {
        
         
        
    }

    public String getEmployeeName(int id) throws ExistException {
        employee = em.find(Employee.class, id);
        if (employee == null)
            throw new ExistException("EMPLOYEE ID DOES NOT EXIST.");

        return employee.getUsername();
    }
    /*
    public int getEmployeeID(String name) throws ExistException{
        int doctorID;
        Query q=em.createQuery("SELECT e.id from Employee e where e.username=:name");
        q.setParameter("name", name);
        List result;
        result = q.getResultList();
        if ((result.isEmpty()))
            throw new ExistException("DOCTOR WITH NAME " +name+ " DOES NOT EXIST");
        for (Object o: result){
            doctorID = (int)o;
        }
        return doctorID;
        
    }*/

    public List<Employee> getAvailableEmployees(String appointmentDate, String appointmentTime) throws ExistException, ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
                // we will now try to parse the string into date form
                sdf.setLenient(false);
                Date testDate = sdf.parse(appointmentDate);
        String pattern = "HH:mm";


        SimpleDateFormat sdf2 = new SimpleDateFormat(pattern);

        String shiftCode=null;

        Date sA = sdf2.parse("00:00");
        Date eA= sdf2.parse("07:59");
        Date sB= sdf2.parse("08:00");
        Date eB= sdf2.parse("15:59");
        Date sC = sdf2.parse("16:00");
        Date eC= sdf2.parse("23:59");

        Date appTime = sdf2.parse(appointmentTime);


        if((appTime.compareTo(eA)<= 0) && (appTime.compareTo(sA)>= 0)){
            shiftCode="A";
        }
        else if((appTime.compareTo(eB)<= 0) && (appTime.compareTo(sB)>= 0)){
            shiftCode="B";
        }
        else if((appTime.compareTo(eC)<= 0) && (appTime.compareTo(sC)>= 0)){
            shiftCode="C";
        }




        Query q = em.createQuery
                ("SELECT s FROM Schedule s WHERE s.shiftDate = :appointmentDate AND s.shiftCode=:shiftCode");
        q.setParameter("appointmentDate", appointmentDate);
        q.setParameter("shiftCode", shiftCode);
        List result;
        result = q.getResultList();
        if ((result.isEmpty()==true)){
            em.clear();

            throw new ExistException( "THERE ARE NO SHIFTS CREATED FOR THE GIVEN TIME AND DATE");
        }
        for (Object o: result){
            schedule = (Schedule)o;
        }
        
        Long shiftId = schedule.getId();
        
        List<Employee> empList = this.getEmployees(shiftId);
        if(empList.isEmpty()){
            throw new ExistException("NO DOCTORS HAVE BEEN ASSIGNED TO GIVEN SHIFT");
        }
        
        return this.getEmployees(shiftId);


    }

    public List<Employee> getEmployees() throws ExistException {

        Query q = em.createQuery("SELECT e FROM Employee e");
       /* List docList = new ArrayList();
        for (Object o: q.getResultList()) {
            Doctor doc = (Doctor)o;
            docList.add(doc);
        }*/
        
        List docList = (List)q.getResultList();
        docList.size();
        
        if (docList.isEmpty()==true){
            throw new ExistException("THERE ARE NO DOCTORS IN THE DATABASE");
        }



        return docList;
    }

    public List<Employee> getEmployees(Long shiftID){
        Query q = em.createQuery("SELECT e from Employee e left join e.schedules as s where s.id = :shiftID");
        q.setParameter("shiftID", shiftID);
        List scheduleList = q.getResultList();
        return scheduleList;
    }
    
    public void assignShiftEmployee (int doctorId, String shiftDate, String shiftCode) throws ExistException, ParseException {

        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
                // we will now try to parse the string into date form
                sdf.setLenient(false);
                Date testDate = sdf.parse(shiftDate);
        
        
        Query q = em.createQuery
                ("SELECT s FROM Schedule s WHERE s.shiftDate = :shiftDate AND s.shiftCode =:shiftCode");
        q.setParameter("shiftDate", shiftDate);
        q.setParameter("shiftCode", shiftCode);
        List result;
        result = q.getResultList();
        if ((result.isEmpty())){
            em.clear();
            throw new ExistException("SHIFT DOES NOT EXIST");
        }

        for (Object o: result){
            schedule = (Schedule)o;
        }

        employee = em.find(Employee.class, doctorId);
        if (employee == null){
            throw new ExistException("DOCTOR DOES NOT EXIST");
        }
        System.out.println("the doctor id is " + doctorId);
        
        if(!(getShiftsEmployeeBoolean(doctorId))){
            employee.addSchedule(schedule);
            schedule.addEmployee(employee);
            em.persist(schedule);
            em.persist(employee);
        }
        else{
            List<Schedule> existingSch = this.getShiftsEmployee(doctorId);
            for(Object p: existingSch){
                existingS = (Schedule)p;
                if(schedule.getShiftDate().compareTo(existingS.getShiftDate())==0  && schedule.getShiftCode().compareTo(existingS.getShiftCode())==0){
                    throw new ExistException("SHIFT ALREADY ASSIGNED TO DOCTOR");
                }
            }
            employee.addSchedule(schedule);
            schedule.addEmployee(employee);
            em.persist(schedule);
            em.persist(employee);
        }


    }
    
     public boolean getShiftsEmployeeBoolean(int doctorID) throws ExistException{
        System.out.println("the doctor id is " + doctorID);
        employee = em.find(Employee.class, doctorID);
        if (employee == null)
            throw new ExistException("EMPLOYEE ID DOES NOT EXIST.");
        
        Query q = em.createQuery("SELECT s from Schedule s left join s.employees as d where d.id = :doctorID");
        q.setParameter("doctorID", doctorID);
        List scheduleList = q.getResultList();
        //   List scheduleList = new ArrayList();
     /*   for (Object o: q.getResultList()) {
            Schedule sch = (Schedule)o;
            scheduleList.add(sch);
        }*/
        if (scheduleList.isEmpty()==true){
            return false;
    }else return true;
        
     }
    public List<Schedule> getShiftsEmployee(int doctorID) throws ExistException{
        System.out.println("the doctor id is " + doctorID);
        employee = em.find(Employee.class, doctorID);
        
        if (employee == null)
            throw new ExistException("EMPLOYEE ID DOES NOT EXIST.");
        
        Query q = em.createQuery("SELECT s from Schedule s left join s.employees as d where d.id = :doctorID");
        q.setParameter("doctorID", doctorID);
        List scheduleList = q.getResultList();
        //   List scheduleList = new ArrayList();
     /*   for (Object o: q.getResultList()) {
            Schedule sch = (Schedule)o;
            scheduleList.add(sch);
        }*/
        if (scheduleList.isEmpty()==true){
            throw new ExistException("THERE ARE NO SHIFTS ASSIGNED TO THIS DOCTOR");
        }
      
        return scheduleList;

    }
    
}
