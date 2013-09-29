package com.is3102.service;

import com.is3102.entity.Employee;
import com.is3102.util.DateUtility;
import com.is3102.util.JavaMail;
import com.is3102.util.SHA;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

@Stateless
public class EmployeeService extends DataAccessService<Employee> implements Serializable{

    @PersistenceContext
    private EntityManager em;

    private String message;

    public EmployeeService(){
        super(Employee.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Employee create(Employee t) {
        t.setRegisterDate(DateUtility.getCurrentDateTime());
        Random random = new Random();
        SHA sha = new SHA();
        String validation = Integer.valueOf(random.nextInt(100000) + 1000).toString();
        validation = sha.convert(validation);
        t.setActivation(validation);
        System.out.println(this.em == null ? "null" : "notnull");

        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        JavaMail mail = new JavaMail();
        mail.setFromAddress("medicalsystem6@gmail.com");
        mail.setToAddress(t.getEmail());
        mail.setUsername("medicalsystem6@gmail.com");
        mail.setPassword("testingpurpose");
        mail.setSubject("Account activation");
        String text = "Please use the link below to activate you account\n"
                + "http://localhost:8080/IS3102/validation.xhtml?id=" + t.getId() + "&validation=" + validation + "\n"
                + "If your browser have problem with the link, please copy the link to address bar of your browser\n";
        mail.setText(text);
        mail.sendMessage();
        return t;
    }

    public boolean activate (){
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = (Map<String, String>) context.getExternalContext().getRequestParameterMap();
        System.out.println(params.get("id"));
        Integer id = Integer.valueOf(params.get("id"));
        String  validation = params.get("validation").trim();
        Employee employee = this.find(id);

        if (employee == null) return false;

        String rightValidation = employee.getActivation().trim();

        if (validation.equals(rightValidation)) {
            System.out.println("set activation to ''");
            employee.setActivation("");
            this.em.persist(employee);
            this.em.flush();
            this.em.refresh(employee);
            return true;
        }
        else return false;
    }

    public boolean resetEmail(String email){
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        List<Employee> employees = this.findWithNamedQuery(Employee.EMAIL, params);
        if (employees.size() != 0){
            // Should never be more than 1
            for (int i = 0; i < employees.size(); i++){
                Employee e = employees.get(i);
                Random random = new Random();
                SHA sha = new SHA();
                String reset = Integer.valueOf(random.nextInt(100000) + 1000).toString();
                reset = sha.convert(reset);
                e.setReset(reset);
                this.em.persist(e);
                this.em.flush();
                this.em.refresh(e);
                JavaMail mail = new JavaMail();
                mail.setFromAddress("medicalsystem6@gmail.com");
                mail.setToAddress(e.getEmail());
                mail.setUsername("medicalsystem6@gmail.com");
                mail.setPassword("testingpurpose");
                mail.setSubject("ResetPassword");
                mail.setText("http://localhost:8080/IS3102/reset.xhtml?id=" + e.getId() + "&reset=" + e.getReset());
                mail.sendMessage();
            }
            return true;
        }
        return false;
    }

    public boolean resetPassword(Map<String,String> params){
        Integer id = Integer.valueOf(params.get("form:id"));
        System.out.println(id);

        Employee employee = this.find(id);
        SHA sha = new SHA();
        employee.setPassword(sha.convert(params.get("form:password")));
        employee.setLastResetDate(DateUtility.getCurrentDateTime());
        Integer resetCount = Integer.parseInt(employee.getResetCount()) +1;
        employee.setResetCount(resetCount.toString());
        em.persist(employee);
        em.flush();
        em.refresh(employee);
        return true;
    }

    public boolean reset(){
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = (Map<String, String>) context.getExternalContext().getRequestParameterMap();
        if (params.get("id") == null) return false;
        if (params.get("reset") == null) return false;

        Integer id = Integer.valueOf(params.get("id"));
        String  reset = params.get("reset").trim();
        Employee employee = this.find(id);

        if (employee == null) return false;

        String rightReset = employee.getReset().trim();

        if (reset.equals(rightReset)) {
            System.out.println("set reset to ''");
            employee.setReset("");
            this.em.persist(employee);
            this.em.flush();
            this.em.refresh(employee);
            return true;
        }
        else return false;
    }

    public Map<String, String> validate (Employee t){
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", t.getUsername());

        System.out.println(params.get("email"));
        System.out.println(params.get("username"));

        List<Employee> employees = new ArrayList<Employee>();
        try{
            employees = this.findWithNamedQuery(Employee.USERNAME, params);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

        Map<String, String> commonParams = new HashMap<String, String>();

        if (employees.size() > 0){
            try{
                commonParams.put("message", "Employee with this username exists. Please choose different username");
                return commonParams;
            }
            catch (Exception e){
                System.out.println(e.toString());
            }

        }

        employees = new ArrayList<Employee>();
        params = new HashMap<String, String>();
        params.put("email", t.getEmail());
        employees = this.findWithNamedQuery(Employee.EMAIL, params);
        if (employees.size()>0){
            System.out.println("return email duplicate");
            try{
                commonParams.put("message", "Email is already used. Please choose different email");
                return commonParams;
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
        return null;
    }
}
