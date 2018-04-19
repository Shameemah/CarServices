/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.web;

import edu.iit.sat.itmd4515.sfuseini.domain.Department;
import edu.iit.sat.itmd4515.sfuseini.domain.Employee;
import edu.iit.sat.itmd4515.sfuseini.domain.security.User;
import edu.iit.sat.itmd4515.sfuseini.ejb.DepartmentService;
import edu.iit.sat.itmd4515.sfuseini.ejb.EmployeeService;
import edu.iit.sat.itmd4515.sfuseini.ejb.UserService;
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
 * @author shameemahfuseini-codjoe
 */
@Named
@RequestScoped
public class ManagerPortalController extends BaseController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @EJB
    private DepartmentService departmentService;
    @EJB
    private EmployeeService employeeService;
    @EJB
    private UserService userService;
    @Inject
    LoginController loginController;

    private List<Employee> employee;
    private List<Department> department;
    private List<User> user;
    private Employee singleEmployee;
    private Department singleDepartment;
    private int employeeId;
    private String departmentId;

    /**
     *
     */
    public ManagerPortalController() {
    }

    /**
     *
     */
    @Override
    @PostConstruct
    public void postConstruct() {
        super.postConstruct(); //To change body of generated methods, choose Tools | Templates.
        employee = employeeService.findAll();
        department = departmentService.findAll();
        user = userService.findAll();
        this.singleEmployee = new Employee();
        this.singleDepartment = new Department();
        singleEmployee.setDepartment(singleDepartment);
    }

    //Navigation and Action Methods(Employees)

    /**
     *
     * @return
     */
    public String doCreateEmployee() {
        this.singleEmployee = new Employee();
        LOG.info("calling create method" + singleEmployee.toString());
        return "/manager/updateEmployee.xhtml";
    }

    /**
     *
     * @param e
     * @return
     */
    public String doViewEmployee(Employee e) {
        this.singleEmployee = e;
        LOG.info("calling view method" + singleEmployee.toString());
        return "/manager/viewEmployee.xhtml";
    }

    /**
     *
     * @param e
     * @return
     */
    public String doUpdateEmployee(Employee e) {
        this.singleEmployee = e;
        LOG.info("calling update method" + singleEmployee.toString());
        return "/manager/updateEmployee.xhtml";
    }

    /**
     *
     * @param e
     * @return
     */
    public String doDeleteEmployee(Employee e) {
        LOG.info("calling delete method" + singleEmployee.toString());
        employeeService.remove(e);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Delete Successful!", "Deleted employee " + e.getFirstName()));
        externalContext.getFlash().setKeepMessages(true);
        return "/manager/welcome.xhtml?faces-redirect=true\"";
    }

    /**
     *
     * @return
     */
    public String doExecuteEmployeeUpdate() {
        if (this.singleEmployee.getId() != null) {
            LOG.info("calling execute employee update method" + singleEmployee.toString());
            employeeService.update(singleEmployee);
            employee = employeeService.findAll();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Update Successful!", "Successfully updated employee "
                    + singleEmployee.getFirstName()));
        } else {
            LOG.info("calling execute employee create method" + singleEmployee.toString());
            employeeService.create(singleEmployee);
            employee = employeeService.findAll();
            user = userService.findAll();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Successfully added employee " + singleEmployee.getFirstName()));
        }
        return "/manager/welcome.xhtml";
    }
    
    /**
     *
     * @param employeeId
     * @return
     */
    public String doFindEmployee(int employeeId) {
        employeeId = this.employeeId;
        try {
            this.singleEmployee = employeeService.findByEmployeeId(employeeId);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No employee found!", "This employee is not in the system."));
            externalContext.getFlash().setKeepMessages(true);
            return "/manager/welcome.xhtml?faces-redirect=true\"";
        }
        return "/manager/viewEmployee.xhtml";
    }

    //Navigation and Action Methods(Department)

    /**
     *
     * @return
     */
    public String doCreateDepartment() {
        LOG.info("calling create method" + singleDepartment.toString());
        return "/manager/createDepartment.xhtml";
    }

    /**
     *
     * @param d
     * @return
     */
    public String doViewDepartment(Department d) {
        this.singleDepartment = d;
        LOG.info("calling view method" + singleDepartment.toString());
        return "/manager/viewDepartment.xhtml";
    }

    /**
     *
     * @param d
     * @return
     */
    public String doUpdateDepartment(Department d) {
        this.singleDepartment = d;
        LOG.info("calling update method" + singleDepartment.toString());
        return "/manager/updateDepartment.xhtml";
    }

    /**
     *
     * @param d
     * @return
     */
    public String doDeleteDepartment(Department d) {
        LOG.info("calling delete method" + singleDepartment.toString());
        try {
            departmentService.remove(d);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Delete Successful!", "Deleted department " + d.getDepartmentName()));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Delete Failed!", "Some employees still belong to this department"));
        }
        externalContext.getFlash().setKeepMessages(true);
        return "/manager/welcome.xhtml?faces-redirect=true\"";
    }

    /**
     *
     * @return
     */
    public String doExecuteDepartmentUpdate() {
        LOG.info("calling execute employee update method" + singleDepartment.toString());
        departmentService.update(singleDepartment);
        department = departmentService.findAll();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Update Successful!", "Successfully updated department "
                + singleDepartment.getDepartmentName()));
        return "/manager/welcome.xhtml";
    }

    /**
     *
     * @return
     */
    public String doExecuteDepartmentCreate() {
        LOG.info("calling execute employee create method" + singleDepartment.toString());
        departmentService.create(singleDepartment);
        department = departmentService.findAll();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
                "Successfully added department "
                + singleDepartment.getDepartmentName()));

        return "/manager/welcome.xhtml";
    }
    
    /**
     *
     * @param departmentId
     * @return
     */
    public String doFindDepartment(String departmentId) {
        departmentId = this.departmentId;
        try {
            this.singleDepartment = departmentService.findByDepartmentId(departmentId);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No department found!", "This department is not in the system."));
            externalContext.getFlash().setKeepMessages(true);
            return "/manager/welcome.xhtml?faces-redirect=true\"";
        }
        return "/manager/viewDepartment.xhtml";
    }

    //<------------Getters and setters-------->

    /**
     *
     * @return
     */
    public List<Department> getDepartment() {
        return department;
    }

    /**
     *
     * @param department
     */
    public void setDepartment(List<Department> department) {
        this.department = department;
    }

    /**
     *
     * @return
     */
    public List<Employee> getEmployee() {
        return employee;
    }

    /**
     *
     * @param employee
     */
    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    /**
     *
     * @return
     */
    public Employee getSingleEmployee() {
        return singleEmployee;
    }

    /**
     *
     * @param singleEmployee
     */
    public void setSingleEmployee(Employee singleEmployee) {
        this.singleEmployee = singleEmployee;
    }

    /**
     *
     * @return
     */
    public Department getSingleDepartment() {
        return singleDepartment;
    }

    /**
     *
     * @param singleDepartment
     */
    public void setSingleDepartment(Department singleDepartment) {
        this.singleDepartment = singleDepartment;
    }

    /**
     *
     * @return
     */
    public List<User> getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(List<User> user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     *
     * @param employeeId
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     *
     * @return
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     *
     * @param departmentId
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

}
