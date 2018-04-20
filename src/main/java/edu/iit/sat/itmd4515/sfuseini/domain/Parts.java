/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Entity
@Table(name = "parts")
@NamedQueries({
    @NamedQuery(name = "Parts.findAll", query = "select p from Parts p")
    ,
    @NamedQuery(name = "Parts.findByPartsId", query = "select  p from Parts p where p.serialNumber = :serialNumber")
})
public class Parts implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull(message = "Serial number cannot be null")
    @Column(name = "SERIALNUMBER")
    private Long serialNumber;
    @Size(max = 255)
    @Column(name = "BRANDNAME")
    private String brandName;
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "CHASSISNUMBER")
    private Integer chassisNumber;
    @Column(name = "ENGINENUMBER")
    private Integer engineNumber;
    @Column(name = "MANUFACTUREDATE")
    @Temporal(TemporalType.DATE)
    private Date manufactureDate;
    @Size(max = 255)
    @Column(name = "MODEL")
    private String model;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ODOMETERREADING")
    private Double odometerReading;
    @Column(name = "PURCHASEDATE")
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;
    @Column(name = "REGISTRATIONNUMBER")
    private Integer registrationNumber;
    @Size(max = 255)
    @Column(name = "TYPE")
    private String type;

    @OneToOne(mappedBy = "parts", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Repairs repairs;

    /**
     *
     */
    public Parts() {
    }

    /**
     *
     * @param serialNumber
     */
    public Parts(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     *
     * @param serialNumber
     * @param type
     * @param category
     * @param brandName
     * @param model
     * @param registrationNumber
     * @param engineNumber
     * @param chassisNumber
     * @param odometerReading
     * @param manufactureDate
     * @param purchaseDate
     */
    public Parts(Long serialNumber, String type, String category, String brandName, String model, int registrationNumber, int engineNumber, int chassisNumber, double odometerReading, Date manufactureDate, Date purchaseDate) {
        this.serialNumber = serialNumber;
        this.type = type;
        this.category = category;
        this.brandName = brandName;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.engineNumber = engineNumber;
        this.chassisNumber = chassisNumber;
        this.odometerReading = odometerReading;
        this.manufactureDate = manufactureDate;
        this.purchaseDate = purchaseDate;
    }

    /**
     *
     * @return
     */
    public Long getSerialNumber() {
        return serialNumber;
    }

    /**
     *
     * @param serialNumber
     */
    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     *
     * @param brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     *
     * @return
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @return
     */
    public int getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     *
     * @param registrationNumber
     */
    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     *
     * @return
     */
    public int getEngineNumber() {
        return engineNumber;
    }

    /**
     *
     * @param engineNumber
     */
    public void setEngineNumber(int engineNumber) {
        this.engineNumber = engineNumber;
    }

    /**
     *
     * @return
     */
    public int getChassisNumber() {
        return chassisNumber;
    }

    /**
     *
     * @param chassisNumber
     */
    public void setChassisNumber(int chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    /**
     *
     * @return
     */
    public double getOdometerReading() {
        return odometerReading;
    }

    /**
     *
     * @param odometerReading
     */
    public void setOdometerReading(double odometerReading) {
        this.odometerReading = odometerReading;
    }

    /**
     *
     * @return
     */
    public Date getManufactureDate() {
        return manufactureDate;
    }

    /**
     *
     * @param manufactureDate
     */
    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    /**
     *
     * @return
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     *
     * @param purchaseDate
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     *
     * @return
     */
    public Repairs getRepairs() {
        return repairs;
    }

    /**
     *
     * @param repairs
     */
    public void setRepairs(Repairs repairs) {
        this.repairs = repairs;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.serialNumber);
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
        final Parts other = (Parts) obj;
        if (!Objects.equals(this.serialNumber, other.serialNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Part{" + "serialNumber=" + serialNumber + ", type=" + type + ", category=" + category + ", brandName=" + brandName + ", model=" + model + ", registrationNumber=" + registrationNumber + ", engineNumber=" + engineNumber + ", chassisNumber=" + chassisNumber + ", odometerReading=" + odometerReading + ", manufactureDate=" + manufactureDate + ", purchaseDate=" + purchaseDate + '}';
    }
}
