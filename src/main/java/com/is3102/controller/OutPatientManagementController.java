/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.controller;


import java.io.Serializable;
import com.is3102.EntityClass.Patient;
import com.is3102.entity.Employee;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.EJB;
import com.is3102.service.OutPatientManagementRemote;
import org.primefaces.event.RowEditEvent;
import com.is3102.EntityClass.OutpatientAppointment;
import java.util.Calendar;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;



/**
 *
 * @author Ben
 */
@ManagedBean
@ViewScoped
public class OutPatientManagementController implements Serializable  {
     @EJB
    public OutPatientManagementRemote opm;
    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    String NRIC_PIN1;
    String NRIC_PIN2;
    String NRIC_PIN3;
    String NRIC_PIN4;
    String NRIC_PIN5;
    String name;
    String name2;
    Date birthday;
    Date birthday2;
    String address;
    String addres2;
    String nationality;
    String contact;
    String contact2;
    String height;
    String weight;
    String weight2;
    String gender;
    String bloodgroup;
    Date startDate;
    Date endDate;
    String type;
    String appID;
    long CIN;
    Patient patient;
    List<OutpatientAppointment> appointments = new ArrayList<OutpatientAppointment>();
  

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public long getCIN() {
        return CIN;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<OutpatientAppointment> getAppointments() {
        return appointments;
    }


    public String getNRIC_PIN1() {
        return NRIC_PIN1;
    }

    public void setNRIC_PIN1(String NRIC_PIN1) {
        this.NRIC_PIN1 = NRIC_PIN1;
    }

    public String getNRIC_PIN2() {
        return NRIC_PIN2;
    }

    public void setNRIC_PIN2(String NRIC_PIN2) {
        this.NRIC_PIN2 = NRIC_PIN2;
    }

    public String getNRIC_PIN3() {
        return NRIC_PIN3;
    }

    public void setNRIC_PIN3(String NRIC_PIN3) {
        this.NRIC_PIN3 = NRIC_PIN3;
    }

    public String getNRIC_PIN4() {
        return NRIC_PIN4;
    }

    public void setNRIC_PIN4(String NRIC_PIN4) {
        this.NRIC_PIN4 = NRIC_PIN4;
    }

    public String getNRIC_PIN5() {
        return NRIC_PIN5;
    }

    public void setNRIC_PIN5(String NRIC_PIN5) {
        this.NRIC_PIN5 = NRIC_PIN5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWeight2() {
        return weight2;
    }

    public void setWeight2(String weight2) {
        this.weight2 = weight2;
    }

    public Date getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(Date birthday2) {
        this.birthday2 = birthday2;
    }

    public String getAddres2() {
        return addres2;
    }

    public void setAddres2(String addres2) {
        this.addres2 = addres2;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public OutPatientManagementController() { eventModel = new DefaultScheduleModel();
        eventModel.addEvent(new DefaultScheduleEvent("Example Appointment One", previousDay8Pm(), previousDay11Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Example Appointment Two", today1Pm(), today6Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Example Appointment Three", nextDay9Am(), nextDay11Am()));
        eventModel.addEvent(new DefaultScheduleEvent("Example Appointment Four", theDayAfter3Pm(), theDayAfter4Pm()));
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Please do not make an appointment which is already occupied, otherwise " +
                "your appointment will not be accepted"));
         context.addMessage(null, new FacesMessage("Please note each appointment should be exactly for an hour, otherwise " + 
                "your appointment will not be accepted"));
       
        lazyEventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {
                clear();

                Date random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));

                random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
            }
        };
    }

    public void doAddPatient(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String pin = opm.addPatient(NRIC_PIN1, name, format.format(birthday), address, contact, nationality, height, weight, gender, bloodgroup);
            context.addMessage(null, new FacesMessage("Patient Record " + name + " with PIN " + NRIC_PIN1 + " successfully created!"));
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record could not be Created!", null));
        }
    }

   

    public void doCreateCase(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            //appointments = am.getPatientAppointments(NRIC_PIN3);
          
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCreated = new Date();
          
            context.addMessage(null, new FacesMessage("Case " + CIN + " for " + NRIC_PIN3 + " on date " + format.format(dateCreated) + " successfully created!"));
        } catch (Exception ex) {
            ex.printStackTrace();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Case could not be created!", null));
        }
    }

 
    public void doUpdatePatient(ActionEvent actionEvent) {
        patient = opm.getPatient(NRIC_PIN5);
    }

    public void showAppointments(ActionEvent event) {
        Patient patient = opm.getPatient(NRIC_PIN4);
        if (patient != null) {
            appointments = opm.getOutPatientAppointments(NRIC_PIN4);
        } else {
            appointments = null;
        }
    }

    public void onEdit(RowEditEvent event) {
        try {
            String newName = (String) ((Patient) event.getObject()).getName();
            String newAddress = (String) ((Patient) event.getObject()).getAddress();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String newBirthday = format.format(((Patient) event.getObject()).getBirthday());
            String newNumber = (String) ((Patient) event.getObject()).getcNumber();
            String newWeight = (String) ((Patient) event.getObject()).getWeight();
            opm.update(patient.getNRIC_PIN(), newName, newAddress, newBirthday, newNumber, newWeight);
            FacesMessage msg = new FacesMessage("Patient Record Edited", ((Patient) event.getObject()).getNRIC_PIN());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record could not be Edited!", null));
        }
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Patient Record Not Edited", ((Patient) event.getObject()).getNRIC_PIN());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }
    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }
    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);

        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 2);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 10);

        return t.getTime();
    }

    private Date theDayAfter4Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 4);

        return t.getTime();
    }

    public ScheduleEvent getEvent() {
        System.out.println("Getting event: " + event.getId() + " " + event.getTitle());
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        System.out.println("Setting event");
        this.event = event;
    }
 

    public void addEvent(ActionEvent actionEvent) {
        System.out.println("Adding event");
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);
        FacesContext context = FacesContext.getCurrentInstance();
         System.out.println("test 1");
        try {
             System.out.println("test 2");
             
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
           // String id = opm.makeOutpatientAppointment(NRIC_PIN2,format.format(event.startDate)),format.format(event.endDate));
           // String id = opm.makeOutpatientAppointment(NRIC_PIN2,format.format(event.getStartDate()),format.format(event.getEndDate()));
             String id = opm.makeOutpatientAppointment(NRIC_PIN2,event.getStartDate(),event.getEndDate());
            System.out.println(event.getStartDate());
             System.out.println(event.getTitle());
             System.out.println("test 123456");
            context.addMessage(null, new FacesMessage("Appointment " + id + " for " + NRIC_PIN2  + " successfully made!"));
        } catch (Exception ex) {
             System.out.println("test 3");
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Appointment could not be made!", null));
        }
      
        event = new DefaultScheduleEvent();
        System.out.println("Appointment created");
    }
public void addEventTest(ActionEvent actionEvent) {
     //add patient
     FacesContext context = FacesContext.getCurrentInstance();
        try {
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String pin = opm.addPatient(NRIC_PIN1, name, format.format(birthday), address, contact, nationality, height, weight, gender, bloodgroup);
            context.addMessage(null, new FacesMessage("Patient Record " + name + " with PIN " + NRIC_PIN1 + " successfully created!"));
            //add appointment  
        System.out.println("Adding event");
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);
      
         System.out.println("test 1");
        try {
              System.out.println("test 2");
              System.out.println(event.getStartDate() + "0");
              System.out.println(event.getTitle() + "0");
              //here need to test if the compulsory fields of patient information and appointment time is filled!!!
              //if event.getEndDate()-event.getStartDate()=30
              
            //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            // String id = opm.makeOutpatientAppointment(NRIC_PIN2,format.format(event.startDate)),format.format(event.endDate));
            // String id = opm.makeOutpatientAppointment(NRIC_PIN1,format.format(event.getStartDate()),format.format(event.getEndDate()));
             String id = opm.makeOutpatientAppointment(NRIC_PIN1,event.getStartDate(),event.getEndDate());
             System.out.println(event.getStartDate());
             System.out.println(event.getTitle());
             context.addMessage(null, new FacesMessage("Appointment " + id + " for " + NRIC_PIN1  + " successfully made!"));
             Long CIN =opm.createCase(id, event.getStartDate(),"outpatient");
            
        } catch (Exception ex) {
           System.out.println(event.getStartDate() + "1");
              System.out.println(event.getTitle() +  "1");
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Appointment could not be made!", null));
        }
      
        event = new DefaultScheduleEvent();
        System.out.println("Appointment created");
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Patient Record could not be Created!", null));
        }
        
        
     
        
    }
    
    
    public void onEventSelect(SelectEvent selectEvent) {

        System.out.println("test event");
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Please do not make an appointment which is already occupied, otherwise" +
               "your appointment will not be accepted"));

        System.out.println("test date");
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        System.out.println("event move");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        System.out.println("event resize");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
    

