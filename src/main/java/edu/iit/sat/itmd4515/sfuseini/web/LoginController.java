/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Named
@RequestScoped
public class LoginController extends BaseController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
    
    @NotNull(message="Username cannot blank")
    private String username;
    @NotNull(message="Password cannot be blank")
    private String password;

    /**
     *
     */
    public LoginController() {
    }

    /**
     *
     * @return
     */
    public String getRemoteUser() {
        return context.getExternalContext().getRemoteUser();
    }

    /**
     *
     * @return
     */
    public boolean isManager() {
        return context.getExternalContext().isUserInRole("MGR_ROLE");
    }

    /**
     *
     * @return
     */
    public boolean isLaborer() {
        return context.getExternalContext().isUserInRole("LBR_ROLE");
    }

    /**
     *
     * @return
     */
    public boolean isClerk() {
        return context.getExternalContext().isUserInRole("CLRK_ROLE");
    }

    /**
     *
     * @return
     */
    public String doLogin() {
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(username, password);
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect username or password", "Invalid username or password"));
            return "/login.xhtml";
        }
        return "/welcome.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public String doLogout() {
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed Logout", "Unable to logout"));
            return "/login.xhtml";
        }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logged out successfully", "Log out successful!"));
        return "/login.xhtml";
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
