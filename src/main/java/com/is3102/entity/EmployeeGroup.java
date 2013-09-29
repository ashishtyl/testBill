package com.is3102.entity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="employee_group")
@NamedQueries({
        @NamedQuery(name = EmployeeGroup.ALL, query = "SELECT u FROM EmployeeGroup u "),
        @NamedQuery(name = EmployeeGroup.TOTAL, query = "SELECT COUNT(u) FROM EmployeeGroup u")})
public class EmployeeGroup extends BaseEntity implements Serializable{

    public final static String ALL = "EmployeeGroup.populateGroups";
    public final static String TOTAL = "EmployeeGroup.countEmployeeGroupsTotal";

    public String group_name;

    public String groupdesc;

    @ManyToMany(mappedBy = "employeeGroups")
    private List<Employee> employees;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroupdesc() {
        return groupdesc;
    }

    public void setGroupdesc(String groupdesc) {
        this.groupdesc = groupdesc;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
