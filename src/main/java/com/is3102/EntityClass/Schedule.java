/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is3102.EntityClass;

import com.is3102.entity.Employee;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Bryan Arnold
 */
@Entity
public class Schedule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

  //  @Temporal(javax.persistence.TemporalType.DATE)
    private String shiftDate;
    private String shiftCode;
  
    @ManyToMany
    private List<Employee> employees;
    
    @ManyToMany
    private List<Doctor> doctors;
    
   public Schedule(){
    } 
       public void create(String shiftDate, String shiftCode) {
        this.setShiftDate(shiftDate);
        this.setShiftCode(shiftCode);
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClass.Schedule[ id=" + id + " ]";
    }

    



 
    public void addEmployee(Employee doctor){
        this.employees.add(doctor);
    }
    
    
     public void addDoctor(Doctor doctor){
        this.getDoctors().add(doctor);
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
     * @return the doctors
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * @param doctors the doctors to set
     */
    public void setEmployees(List<Employee> doctors) {
        this.employees = doctors;
    }

    /**
     * @return the doctors
     */
    public List<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * @param doctors the doctors to set
     */
    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
    
    
    
}
