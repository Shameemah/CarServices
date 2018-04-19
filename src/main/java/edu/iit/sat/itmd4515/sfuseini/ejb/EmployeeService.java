/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.ejb;

import edu.iit.sat.itmd4515.sfuseini.domain.Employee;
import edu.iit.sat.itmd4515.sfuseini.domain.security.Group;
import edu.iit.sat.itmd4515.sfuseini.domain.security.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Named
@Stateless
public class EmployeeService extends BaseService<Employee> {

    /**
     *
     */
    public EmployeeService() {
        super(Employee.class);
    }

    /**
     *
     * @param id
     * @return
     */
    public Employee findByEmployeeId(int id) {
        return getEntityManager()
                .createNamedQuery("Employee.findByEmployeeId", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Employee> findAll() {
        return getEntityManager().createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    /**
     *
     * @param username
     * @return
     */
    public Employee findByUsername(String username) {
        return getEntityManager()
                .createNamedQuery("Employee.findByUsername", Employee.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    /**
     *
     * @param jsfEmployee
     */
    @Override
    public void update(Employee jsfEmployee) {
        Employee oldEmployee = getEntityManager().find(Employee.class, jsfEmployee.getId());

        jsfEmployee.setId(oldEmployee.getId());
        jsfEmployee.setUser(oldEmployee.getUser());
        getEntityManager().merge(jsfEmployee);
    }

    /**
     *
     * @param employee
     */
    @Override
    public void create(Employee employee) {

        User user = new User(employee.getFirstName().toLowerCase() + "1", "pass" + employee.getFirstName().toLowerCase());
        employee.setUser(user);
        if (employee.getRole().contains("CEO") || employee.getRole().contains("Managing") || employee.getRole().contains("Director")) {
            user.addGroup(getEntityManager().find(Group.class, "MANAGERS"));
        } else if (employee.getRole().contains("Secretary") || employee.getRole().contains("Clerk")) {
            user.addGroup(getEntityManager().find(Group.class, "CLERKS"));
        } else {
            user.addGroup(getEntityManager().find(Group.class, "LABORERS"));
        }

        getEntityManager().persist(user);
        super.create(employee); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param e
     */
    @Override
    public void remove(Employee e) {
        super.remove(e); //To change body of generated methods, choose Tools | Templates.
    }

}
