/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.web;

import edu.iit.sat.itmd4515.sfuseini.domain.Checkout;
import edu.iit.sat.itmd4515.sfuseini.domain.Employee;
import edu.iit.sat.itmd4515.sfuseini.domain.Tool;
import edu.iit.sat.itmd4515.sfuseini.ejb.CheckoutService;
import edu.iit.sat.itmd4515.sfuseini.ejb.EmployeeService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Shameemah
 */
@Named
@RequestScoped
public class LaborerPortalController extends BaseController {

    private static final Logger LOG = Logger.getLogger(LaborerPortalController.class.getName());

    @EJB
    private EmployeeService employeeService;
    @EJB
    private CheckoutService checkoutService;

    private List<Checkout> checkout;
    private Checkout singleCheckout;
    private Tool tool;
    private Date checkInTime;
    private Date checkOutTime;
    private int checkoutId;
    private Employee employee;

    @Inject
    LoginController loginController;

    /**
     *
     */
    @Override
    @PostConstruct
    public void postConstruct() {
        super.postConstruct(); //To change body of generated methods, choose Tools | Templates.
        checkout = checkoutService.findAll();
        singleCheckout = new Checkout();
        checkInTime = new Timestamp(System.currentTimeMillis());
        checkOutTime = new Timestamp(System.currentTimeMillis());
        singleCheckout.setCheckoutDate(checkOutTime);
        employee = employeeService.findByUsername(loginController.getRemoteUser());
    }

    /**
     *
     * @return
     */
    public String doCreateCheckout() {
        LOG.info("calling create method" + singleCheckout.toString());
    
        singleCheckout.setEmployee(employee);
        checkoutService.create(singleCheckout);
        checkout = checkoutService.findAll();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
                "Added Checkout #"
                + singleCheckout.getId()));
        externalContext.getFlash().setKeepMessages(true);
        
        return "/laborer/welcome.xhtml?faces-redirect=true\"";
    }

    /**
     *
     * @param c
     * @return
     */
    public String doCheckIn(Checkout c) {
        this.singleCheckout = c;
        LOG.info("return date value" + singleCheckout.getReturnDate());
        if (singleCheckout.getReturnDate() == null) {
            singleCheckout.setReturnDate(checkInTime);
            checkoutService.update(singleCheckout);
            checkout = checkoutService.findAll();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
                    "Successfully check in Tool ID#"
                    + singleCheckout.getTool().getSerialNumber()));
            externalContext.getFlash().setKeepMessages(true);
        } else if (singleCheckout.getReturnDate() != null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!",
                    "This tool has already been checked in."
            ));
            externalContext.getFlash().setKeepMessages(true);
        }
        return "/laborer/welcome.xhtml?faces-redirect=true\"";
    }

    /**
     *
     * @param checkoutId
     * @return
     */
    public String doFindCheckout(int checkoutId) {
        checkoutId = this.checkoutId;
        try {
            this.singleCheckout = checkoutService.findByCheckoutId(checkoutId);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No checkout entry found!", "Please try again."));
            externalContext.getFlash().setKeepMessages(true);
            return "/laborer/welcome.xhtml?faces-redirect=true\"";
        }
        return "/laborer/viewCheckout.xhtml";
    }

    //Getters and Setters

    /**
     *
     * @return
     */
    public List<Checkout> getCheckout() {
        return checkout;
    }

    /**
     *
     * @param checkout
     */
    public void setCheckout(List<Checkout> checkout) {
        this.checkout = checkout;
    }

    /**
     *
     * @return
     */
    public Checkout getSingleCheckout() {
        return singleCheckout;
    }

    /**
     *
     * @param singleCheckout
     */
    public void setSingleCheckout(Checkout singleCheckout) {
        this.singleCheckout = singleCheckout;
    }

    /**
     *
     * @return
     */
    public Tool getTool() {
        return tool;
    }

    /**
     *
     * @param tool
     */
    public void setTool(Tool tool) {
        this.tool = tool;
    }

    /**
     *
     * @return
     */
    public Date getCheckInTime() {
        return checkInTime;
    }

    /**
     *
     * @param checkInTime
     */
    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    /**
     *
     * @return
     */
    public Date getCheckOutTime() {
        return checkOutTime;
    }

    /**
     *
     * @param checkOutTime
     */
    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    /**
     *
     * @return
     */
    public int getToolId() {
        return checkoutId;
    }

    /**
     *
     * @param toolId
     */
    public void setToolId(int toolId) {
        this.checkoutId = toolId;
    }

    /**
     *
     * @return
     */
    public int getCheckoutId() {
        return checkoutId;
    }

    /**
     *
     * @param checkoutId
     */
    public void setCheckoutId(int checkoutId) {
        this.checkoutId = checkoutId;
    }

    /**
     *
     * @return
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     *
     * @param employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
