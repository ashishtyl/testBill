package com.is3102.entity;
import com.is3102.EntityClass.Appointment;
import com.is3102.EntityClass.POEOrder;
import com.is3102.EntityClass.Schedule;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="employee")
@NamedQueries({
        @NamedQuery(name = Employee.ALL, query = "SELECT u FROM Employee u "),
        @NamedQuery(name = Employee.TOTAL, query = "SELECT COUNT(u) FROM Employee u"),
        @NamedQuery(name = Employee.EMAIL, query = "SELECT u FROM Employee u where  u.email = :email"),
        @NamedQuery(name = Employee.USERNAME, query = "SELECT u FROM Employee u where  u.username = :username"),
        @NamedQuery(name = Employee.COMPLETE, query = "SELECT u FROM Employee u where  u.username like :username")})
public class        Employee extends BaseEntity implements Serializable{

    public final static String ALL = "Employee.populateEmployees";
    public final static String TOTAL = "Employee.countEmployeesTotal";
    public final static String EMAIL = "Employee.getUsingEmail";
    public final static String USERNAME = "Employee.getUsingUsername";
    public final static String COMPLETE = "Employee.completeUsername";

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    private String email;

    private String identification;

    private String sendEmail;

    private String registerDate;

    private String activation;

    private String lastResetDate;

    private String resetCount;

    private String access;

    private String reset;

    private int activate;

    @OneToMany(cascade={CascadeType.PERSIST}, mappedBy="employee")
    //@OneToMany(cascade={CascadeType.ALL}, mappedBy="employee")
    private Collection<Appointment> appointment = new ArrayList<Appointment>();
    
    @OneToMany(cascade={CascadeType.PERSIST}, mappedBy="employee")
    private Collection<POEOrder> order = new ArrayList<POEOrder>();

    @OneToMany(mappedBy = "employee", cascade=CascadeType.ALL)
    private List<Event> events;

    @OneToMany(mappedBy = "sender", cascade=CascadeType.ALL)
    private List<Message> messageSents;

    @OneToMany(mappedBy = "receiver", cascade=CascadeType.ALL)
    private List<Message> messageReceiveds;

    @ManyToMany
    @JoinTable(name = "employee_group_map", joinColumns = {
            @JoinColumn(table="employee", name = "employeeid", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(table="employee_group", name = "groupid", referencedColumnName = "id")})
    private List<EmployeeGroup> employeeGroups;

    @ManyToMany(mappedBy = "employees")
    private List<Schedule> schedules;

    public Employee(){
        employeeGroups = new ArrayList<EmployeeGroup>();
        System.out.println("in Employee");
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(String sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getLastResetDate() {
        return lastResetDate;
    }

    public void setLastResetDate(String lastResetDate) {
        this.lastResetDate = lastResetDate;
    }

    public String getResetCount() {
        return resetCount;
    }

    public void setResetCount(String resetCount) {
        this.resetCount = resetCount;
    }

    public List<EmployeeGroup> getEmployeeGroups() {
        System.out.println("employee group size" + employeeGroups.size());
        if (employeeGroups.size()>0) System.out.println("employee group" + employeeGroups.get(0).getGroup_name());
        return employeeGroups;

    }

    public void setEmployeeGroups(List<EmployeeGroup> employeeGroups) {
        this.employeeGroups = employeeGroups;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }

    public Collection<Appointment> getAppointments() {
        return appointment;
    }

    public void setAppointments(Collection<Appointment> appointment) {
        this.appointment = appointment;
    }

    public Collection<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(Collection<Appointment> appointment) {
        this.appointment = appointment;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Message> getMessageSents() {
        return messageSents;
    }

    public void setMessageSents(List<Message> messageSents) {
        this.messageSents = messageSents;
    }

    public List<Message> getMessageReceiveds() {
        return messageReceiveds;
    }

    public void setMessageReceiveds(List<Message> messageReceiveds) {
        this.messageReceiveds = messageReceiveds;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(Schedule schedule){
        this.schedules.add(schedule);
    }

    public boolean getActivate() {
        return activate == 1;
    }

    public void setActivate(boolean act) {
        this.activate = act ? 1 : 0;
    }

    public Collection<POEOrder> getOrder() {
        return order;
    }

    public void setOrder(Collection<POEOrder> order) {
        this.order = order;
    }    
}
