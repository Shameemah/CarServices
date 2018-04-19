/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Entity
@Table(name = "checkout")
@NamedQueries({
    @NamedQuery(name = "Checkout.findAll", query = "select c from Checkout c")
    ,
    @NamedQuery(name = "Checkout.findByCheckoutId", query = "select  c from Checkout c where c.id = :id")
})
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Enter a check out date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkoutDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "TOOL_ID")
    private Tool tool;

    /**
     *
     */
    public Checkout() {
    }

    /**
     *
     * @param id
     * @param checkoutDate
     * @param returnDate
     * @param employee
     * @param tool
     */
    public Checkout(Long id, Date checkoutDate, Date returnDate, Employee employee, Tool tool) {
        this.id = id;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
        this.employee = employee;
        this.tool = tool;
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
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    /**
     *
     * @param checkoutDate
     */
    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    /**
     *
     * @return
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     *
     * @param returnDate
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
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

    @Override
    public String toString() {
        return "Checkout{" + "id=" + id + ", checkoutDate=" + checkoutDate + ", returnDate=" + returnDate + ", employee=" + employee + ", tool=" + tool + '}';
    }

}
