/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tool | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Entity
@Table(name = "maintenance")
@NamedQueries({
    @NamedQuery(name = "Maintenance.findAll", query = "select m from Maintenance m")
    ,
    @NamedQuery(name = "Maintenance.findByMaintenanceId", query = "select  m from Maintenance m where m.id = :id")
})
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Future
    private Date dueDate;

    @Temporal(TemporalType.DATE)
    @Future
    private Date reminderDate;
    private boolean maintenanceDone;

    @OneToOne
    @JoinColumn(name = "PART_ID")
    private Parts parts;

    /**
     *
     */
    public Maintenance() {
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
    public Date getDueDate() {
        return dueDate;
    }

    /**
     *
     * @param dueDate
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     *
     * @return
     */
    public Date getReminderDate() {
        return reminderDate;
    }

    /**
     *
     * @param reminderDate
     */
    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
    }

    /**
     *
     * @return
     */
    public boolean isMaintenanceDone() {
        return maintenanceDone;
    }

    /**
     *
     * @param maintenanceDone
     */
    public void setMaintenanceDone(boolean maintenanceDone) {
        this.maintenanceDone = maintenanceDone;
    }

    /**
     *
     * @return
     */
    public Parts getParts() {
        return parts;
    }

    /**
     *
     * @param parts
     */
    public void setParts(Parts parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return "Maintenance{" + "id=" + id + ", dueDate=" + dueDate + ", reminderDate=" + reminderDate + ", maintenanceDone=" + maintenanceDone + ", equipment=" + parts + '}';
    }

}
