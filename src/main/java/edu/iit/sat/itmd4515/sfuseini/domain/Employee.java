/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.domain;

import edu.iit.sat.itmd4515.sfuseini.domain.security.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Entity
@Table(name = "employee")
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "select e from Employee e ")
    ,
    @NamedQuery(name = "Employee.findBylastName", query = "select e from Employee e where e.lastName = :lastName")
    ,
    @NamedQuery(name = "Employee.findByUsername", query = "select e from Employee e where e.user.userName = :username")
    ,
    @NamedQuery(name = "Employee.findByEmployeeId", query = "select  e from Employee e where e.id = :id")
})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Employee last name cannot be null")
    private String lastName;
    @NotNull(message = "Employee first name cannot be null")
    private String firstName;
    private String role;
    @Temporal(TemporalType.DATE)
    @Past(message = "Hire date must be in the past")
    private Date hireDate;
    private int salary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Checkout> checkout = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     *
     */
    public Employee() {
    }

    /**
     *
     * @param id
     */
    public Employee(Long id) {
        this.id = id;
    }

    /**
     *
     * @param lastName
     * @param firstName
     * @param role
     * @param hireDate
     * @param salary
     */
    public Employee(String lastName, String firstName, String role, Date hireDate, int salary) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.role = role;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     *
     * @return
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     *
     * @param hireDate
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    /**
     *
     * @return
     */
    public int getSalary() {
        return salary;
    }

    /**
     *
     * @param salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     *
     * @return
     */
    public Department getDepartment() {
        return department;
    }

    /**
     *
     * @param department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", role=" + role + ", hireDate=" + hireDate + ", salary=" + salary + ", department=" + department + '}';
    }

}
