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
@Table(name = "repairs")
@NamedQueries({
    @NamedQuery(name = "Repairs.findAll", query = "select r from Repairs r")
    ,
    @NamedQuery(name = "Repairs.findByRepairId", query = "select  r from Repairs r where r.id = :id")
})
public class Repairs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String carPlateNumber;
    private String repairDescription;

    @Temporal(TemporalType.DATE)
    private Date dropoffDate;

    @Temporal(TemporalType.DATE)
    private Date pickupDate;
    private boolean repairCompleted;

    @OneToOne
    @JoinColumn(name = "PART_ID")
    private Parts parts;

    /**
     *
     */
    public Repairs() {
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
    public Date getDropoffDate() {
        return dropoffDate;
    }

    /**
     *
     * @param dropoffDate
     */
    public void setDropoffDate(Date dropoffDate) {
        this.dropoffDate = dropoffDate;
    }

    /**
     *
     * @return
     */
    public Date getPickupDate() {
        return pickupDate;
    }

    /**
     *
     * @param pickupDate
     */
    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    /**
     *
     * @return
     */
    public boolean isRepairCompleted() {
        return repairCompleted;
    }

    /**
     *
     * @param repairCompleted
     */
    public void setRepairCompleted(boolean repairCompleted) {
        this.repairCompleted = repairCompleted;
    }

    /**
     *
     * @return
     */
    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    /**
     *
     * @param carPlateNumber
     */
    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    /**
     *
     * @return
     */
    public String getRepairDescription() {
        return repairDescription;
    }

    /**
     *
     * @param repairDescription
     */
    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
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
        return "Repairs{" + "id=" + id + ", carPlateNumber=" + carPlateNumber + ", repairDescription=" + repairDescription + ", dropoffDate=" + dropoffDate + ", pickupDate=" + pickupDate + ", repairCompleted=" + repairCompleted + ", parts=" + parts + '}';
    }

}
