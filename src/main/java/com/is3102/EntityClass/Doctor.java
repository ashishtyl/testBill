/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;
import java.util.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Ashish
 */
@Entity(name="doctor")
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long docId;
    private String username;
    private String Name;
    private String dateOfBirth;

/*    @OneToMany(cascade={CascadeType.ALL}, mappedBy="doctor")
    private Collection<Appointment> appointment = new ArrayList<Appointment>();*/

    @ManyToMany(mappedBy = "doctors")
    private List<Schedule> schedules;

    @OneToMany(cascade={CascadeType.ALL})
    private List<Medical_Procedure> procedures = new ArrayList<Medical_Procedure>();


    public void create (String name, String username, String dob){
        this.Name = name;
        this.dateOfBirth = dob;
        this.username = username;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

/*    public Collection<Appointment> getAppointments() {
        return appointment;
    }

    public void setAppointments(Collection<Appointment> appointment) {
        this.appointment = appointment;
    }*/

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the schedules
     */
    public List<Schedule> getSchedules() {
        return schedules;
    }

    /**
     * @param schedules the schedules to set
     */
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(Schedule schedule){
        this.schedules.add(schedule);
    }
}
