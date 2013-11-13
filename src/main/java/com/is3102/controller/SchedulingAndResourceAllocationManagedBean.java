/**
 *
 * @author Bryan
 */
package com.is3102.controller;

import com.is3102.EntityClass.Schedule;
import com.is3102.Exception.ExistException;
import com.is3102.entity.Employee;
import com.is3102.service.SchedulingandResourceAllocationBeanLocal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@ViewScoped
public class SchedulingAndResourceAllocationManagedBean implements Serializable {

    @EJB
    private SchedulingandResourceAllocationBeanLocal sra;

    public SchedulingAndResourceAllocationManagedBean() {
    }
    //list of codes returned for a disease name searched.
    //caseId to search a particular case.
    String doctorName;
    //ICD10 code entitiy for creating a new case.
    //diagnosis description entered by doctor when adding a new diagnosis for a case.
    List<Schedule> allShifts;
    //disease description entered by doctor used to search ICD10 code
    List<Schedule> doctorShifts;
    //collectin of all Diagnosis objects for a particular case.
    String doctorUsername;
    String doctorDOB;
    Long doctorID;
    String appointmentTime;
    String appointmentDate;
    Long shiftID;
    List<Schedule> shiftsByDoctor;
    @ManagedProperty(value = "#{shiftCode}")
    String shiftCode;
    @ManagedProperty(value = "#{shiftDate}")
    String shiftDate;
    List<Employee> allEmployees;
    List<Employee> availableEmployees;
    List<Employee> employeeByShift;
    List<Schedule> shiftsByEmployee;
    int employeeID;

    public void DoGetAllEmployees(ActionEvent actionEvent) throws ExistException {
        try {
            setAllEmployees(getSra().getEmployees());
        } catch (ParseException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PostConstruct
    public void init() {
        // this.doViewShift();
    }

    public void DoAddDoctor(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try {
                getSra().addDoctor(doctorName, doctorUsername, doctorDOB);
                context.addMessage(null, new FacesMessage("Doctor created successfully with user name: " + doctorUsername));
            } catch (ParseException ex) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doctor could not be created!", null));
            }

        } catch (ExistException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doctor could not be created!", null));
        }

    }

    public void DoGetDoctorName() {
        try {
            this.setDoctorName(getSra().getDoctorName(getDoctorID()));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doGetAvailableEmployees(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try {
                //         this.setAvailableDoctors(sra.getAvailableDoctors(getAppointmentDate(), getAppointmentTime()));
                setAvailableEmployees(getSra().getAvailableEmployees(appointmentDate, appointmentTime));
                if (getAvailableEmployees().isEmpty()) {
                    setAvailableEmployees(null);
                }



                context.addMessage(null, new FacesMessage("Available doctors fetched successfully"));
            } catch (ExistException ex) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doctors could not be fetched!", null));
            }
        } catch (ParseException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doctors could not be fetched!", null));

        }
    }

    public void doGetDoctorName() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            doctorName = getSra().getDoctorName(doctorID);
            //   context.addMessage(null, new FacesMessage("Shift assigned successfully to doctor ID: " + doctorID ));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doGetDoctorID() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            doctorID = getSra().getDoctorID(doctorName);
            context.addMessage(null, new FacesMessage("ID of " + doctorName + " is: " + doctorID));
        } catch (ExistException ex) {
            Logger.getLogger(SchedulingAndResourceAllocationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doGetShiftsByDoctor() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.setShiftsByDoctor(getSra().getShifts(this.doctorID));
            context.addMessage(null, new FacesMessage("ID of " + doctorName + " is: " + doctorID));
        } catch (ExistException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shifts could not be fetched", null));
        }

    }

    public void doGetShiftsByEmployee() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.setShiftsByEmployee(getSra().getShiftsEmployee(employeeID));
            context.addMessage(null, new FacesMessage("Shifts fetched!"));
        } catch (ExistException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shifts could not be fetched", null));
        }

    }

    public void doAssignShift() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try {
                getSra().assignShift(doctorID, shiftDate, shiftCode);
                context.addMessage(null, new FacesMessage("Shift assigned successfully to doctor ID: " + doctorID));
            } catch (ParseException ex) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shift could not be assigned!", null));
            }

        } catch (ExistException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shift could not be assigned!", null));
        }
    }

    public void doAssignShiftEmployee() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try {
                getSra().assignShiftEmployee(employeeID, shiftDate, shiftCode);
                context.addMessage(null, new FacesMessage("Shift assigned successfully to doctor ID: " + employeeID));
            } catch (ParseException ex) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shift could not be assigned!", null));
            }

        } catch (ExistException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shift could not be assigned!", null));
        }
    }

    public void doCreateShift(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            try {
                // declare and initialize testDate variable, this is what will hold
                // our converted string

                getSra().createShift(shiftDate, shiftCode);
                context.addMessage(null, new FacesMessage("Shift created successfully for the following date: " + shiftDate));
            } catch (ParseException ex) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shift could not be created!", null));

            }

        } catch (ExistException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Shift could not be created!", null));
        }
    }

    public void doViewShift() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            allShifts = getSra().viewShifts();

            context.addMessage(null, new FacesMessage("Shift fetched successfully"));

        } catch (ExistException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ex.getMessage(), null));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No shifts can be displayed!", null));
        }
    }

    /**
     * @return the allDoctors
     */
    /**
     * @return the doctorName
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * @param doctorName the doctorName to set
     */
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     * @return the allShifts
     */
    public List<Schedule> getAllShifts() {
        return allShifts;
    }

    /**
     * @param allShifts the allShifts to set
     */
    public void setAllShifts(List<Schedule> allShifts) {
        this.allShifts = allShifts;
    }

    /**
     * @return the doctorShifts
     */
    public List<Schedule> getDoctorShifts() {
        return doctorShifts;
    }

    /**
     * @param doctorShifts the doctorShifts to set
     */
    public void setDoctorShifts(List<Schedule> doctorShifts) {
        this.doctorShifts = doctorShifts;
    }

    /**
     * @return the doctorUsername
     */
    public String getDoctorUsername() {
        return doctorUsername;
    }

    /**
     * @param doctorUsername the doctorUsername to set
     */
    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    /**
     * @return the doctorDOB
     */
    public String getDoctorDOB() {
        return doctorDOB;
    }

    /**
     * @param doctorDOB the doctorDOB to set
     */
    public void setDoctorDOB(String doctorDOB) {
        this.doctorDOB = doctorDOB;
    }

    /**
     * @return the doctorID
     */
    public Long getDoctorID() {
        return doctorID;
    }

    /**
     * @param doctorID the doctorID to set
     */
    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    /**
     * @return the appointmentTime
     */
    public String getAppointmentTime() {
        return appointmentTime;
    }

    /**
     * @param appointmentTime the appointmentTime to set
     */
    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    /**
     * @return the appointmentDate
     */
    public String getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * @param appointmentDate the appointmentDate to set
     */
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * @return the shiftID
     */
    public Long getShiftID() {
        return shiftID;
    }

    /**
     * @param shiftID the shiftID to set
     */
    public void setShiftID(Long shiftID) {
        this.shiftID = shiftID;
    }

    /**
     * @return the doctorsByShift
     */
    /**
     * @return the shiftsByDoctor
     */
    public List<Schedule> getShiftsByDoctor() {
        return shiftsByDoctor;
    }

    /**
     * @param shiftsByDoctor the shiftsByDoctor to set
     */
    public void setShiftsByDoctor(List<Schedule> shiftsByDoctor) {
        this.shiftsByDoctor = shiftsByDoctor;
    }

    /**
     * @return the shiftCode
     */
    public String getShiftCode() {
        return shiftCode;
    }

    /**
     * @param shiftCode the shiftCode to set
     */
    public void setShiftCode(String shiftCode) {
        this.shiftCode = shiftCode;
    }

    /**
     * @return the shiftDate
     */
    public String getShiftDate() {
        return shiftDate;
    }

    /**
     * @param shiftDate the shiftDate to set
     */
    public void setShiftDate(String shiftDate) {
        this.shiftDate = shiftDate;
    }

    // date validation using SimpleDateFormat
// it will take a string and make sure it's in the proper 
// format as defined by you, and it will also make sure that
// it's a legal date
    public boolean isValidDate(String date) {
        // set date format, this can be changed to whatever format
        // you want, MM-dd-yyyy, MM.dd.yyyy, dd.MM.yyyy etc.
        // you can read more about it here:
        // http://java.sun.com/j2se/1.4.2/docs/api/index.html

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        // declare and initialize testDate variable, this is what will hold
        // our converted string

        Date testDate = null;

        // we will now try to parse the string into date form
        try {
            testDate = sdf.parse(date);
        } // if the format of the string provided doesn't match the format we 
        // declared in SimpleDateFormat() we will get an exception
        catch (ParseException e) {
            System.out.println("the date you provided is in an invalid date"
                    + " format.");
            return false;
        }


        if (!sdf.format(testDate).equals(date)) {
            System.out.println("The date that you provided is invalid.");
            return false;
        }

        // if we make it to here without getting an error it is assumed that
        // the date was a valid one and that it's in the proper format

        return true;

    } // end isValidDate

    /**
     * @return the sra
     */
    public SchedulingandResourceAllocationBeanLocal getSra() {
        return sra;
    }

    /**
     * @param sra the sra to set
     */
    public void setSra(SchedulingandResourceAllocationBeanLocal sra) {
        this.sra = sra;
    }

    /**
     * @return the allEmployees
     */
    public List<Employee> getAllEmployees() {
        return allEmployees;
    }

    /**
     * @param allEmployees the allEmployees to set
     */
    public void setAllEmployees(List<Employee> allEmployees) {
        this.allEmployees = allEmployees;
    }

    /**
     * @return the availableEmployees
     */
    public List<Employee> getAvailableEmployees() {
        return availableEmployees;
    }

    /**
     * @param availableEmployees the availableEmployees to set
     */
    public void setAvailableEmployees(List<Employee> availableEmployees) {
        this.availableEmployees = availableEmployees;
    }

    /**
     * @return the employeeByShift
     */
    public List<Employee> getEmployeeByShift() {
        return employeeByShift;
    }

    /**
     * @param employeeByShift the employeeByShift to set
     */
    public void setEmployeeByShift(List<Employee> employeeByShift) {
        this.employeeByShift = employeeByShift;
    }

    /**
     * @return the shiftsByEmployee
     */
    public List<Schedule> getShiftsByEmployee() {
        return shiftsByEmployee;
    }

    /**
     * @param shiftsByEmployee the shiftsByEmployee to set
     */
    public void setShiftsByEmployee(List<Schedule> shiftsByEmployee) {
        this.shiftsByEmployee = shiftsByEmployee;
    }

    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}