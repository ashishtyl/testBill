package com.is3102.controller;

import com.is3102.entity.Employee;
import com.is3102.service.EmployeeService;
import com.is3102.util.DateUtility;
import com.is3102.util.Logger;
import com.is3102.util.SHA;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private @Inject  EmployeeService das;

    private String id;

    private String username;

    private String password;

    private Logger logger = new Logger();


    /**
     * Default constructor
     */
    public LoginController(){
        logger.log("Login Controller Constructor");
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Manage login
     *
     *
     * */
    public void login(ActionEvent actionEvent) {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String navigateString = "";
        try {
            // Checks if username and password are valid if not throws a ServletException
            logger.log(username);
            //logger.log(password);
            request.login(username, password);

            logger.log(username + " logged in " + DateUtility.getCurrentDateTime());

            Map<String, String> params = new HashMap<String, String>();
            params.put("username", username);
            List<Employee> employees = das.findWithNamedQuery(Employee.USERNAME, params);
            if (employees.size()>0){
                // There should be only one here.
                for (int i = 0; i < employees.size(); i++){
                    Employee e = employees.get(i);
                    String activation = e.getActivation();
                    boolean isLocked = !e.getActivate();
                    System.out.println(isLocked ? "locked" : "nolocked");
                    if (isLocked){
                        context.addMessage(null, new FacesMessage("Account is locked", "Your account is locked")) ;
                        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                        session.invalidate();
/*                        navigateString = "/index.xhtml";
                        try{
                            context.getExternalContext().redirect(request.getContextPath() + navigateString);
                        }
                        catch (Exception et){
                            logger.log(et.toString()) ;
                        }*/
                        return;
                    }
                    if (activation.equals("")){
                        Principal principal = request.getUserPrincipal();

                        // gets the user principle and navigates to the appropriate page
                        if (request.isUserInRole("Administrator")) {
                            logger.log(username + " has group as Administrator");
                            navigateString = "/admin/index.xhtml";
                        } else if (request.isUserInRole("Doctor")) {
                            logger.log(username + " has group as Doctor");
                            navigateString = "/doctor/index.xhtml";
                        } else if (request.isUserInRole("Special Staff")) {
                            logger.log(username + " has group as Special Staff");
                            navigateString = "/ss/index.xhtml";
                        } else if (request.isUserInRole("Financial Staff")) {
                            logger.log(username + " has group as Financial Staff");
                            navigateString = "/fs/index.xhtml";
                        }
                    }  else {
                        request.logout();
                        navigateString = "/error.xhtml";
                    }
                }
            }

            try {
                context.getExternalContext().redirect(request.getContextPath() + navigateString);
            } catch (IOException ex) {
                context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
            }
        } catch (ServletException e) {
            logger.log("The username or password you provided does not match our records.");
            System.out.println(e.toString());
            context.addMessage(null, new FacesMessage("Wrong username or password", "Please enter your user and password again")) ;
        }
        System.out.println(navigateString);
    }

    public void logout(ActionEvent actionEvent){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        Logger logger = new Logger();

        if (session != null) {
            session.invalidate();
            logger.log("User logged out " + DateUtility.getCurrentDateTime());
        }
        else{
            try {
                context.addMessage(null, new FacesMessage("Error!", "You have not logged in yet."));
                logger.log("User tried to log out without logging before" + DateUtility.getCurrentDateTime());
            } catch (Exception ex) {
                context.addMessage(null, new FacesMessage("Error!", ex.toString()));
            }
        }

        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml?faces-redirect=true");
        try {
            context.getExternalContext().redirect(request.getContextPath() + "/index.xhtml");
            logger.log("Context path " + request.getContextPath() + "/index.xhtml");
        } catch (IOException ex) {
            context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
        }
    }
}

