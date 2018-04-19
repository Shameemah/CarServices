/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tool | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.domain;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Entity
@Table(name = "tool")
@NamedQueries({
    @NamedQuery(name = "Tool.findAll", query = "select t from Tool t")
    ,
    @NamedQuery(name = "Tool.findByToolId", query = "select  t from Tool t where t.serialNumber = :serialNumber")
})
public class Tool {

    @Id
    @NotNull(message = "Serial number cannot be null")
    private Long serialNumber;
    private String type;
    private String category;
    private String brandName;
    private String model;

    @OneToOne(mappedBy = "tool", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Checkout checkout;

    /**
     *
     */
    public Tool() {
    }

    /**
     *
     * @param serialNumber
     */
    public Tool(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     *
     * @param serialNumber
     * @param type
     * @param category
     * @param brandName
     * @param model
     */
    public Tool(Long serialNumber, String type, String category, String brandName, String model) {
        this.serialNumber = serialNumber;
        this.type = type;
        this.category = category;
        this.brandName = brandName;
        this.model = model;
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
    public Checkout getCheckout() {
        return checkout;
    }

    /**
     *
     * @param checkout
     */
    public void setCheckout(Checkout checkout) {
        this.checkout = checkout;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.serialNumber);
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
        final Tool other = (Tool) obj;
        if (!Objects.equals(this.serialNumber, other.serialNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tool{" + "serialNumber=" + serialNumber + ", type=" + type + ", category=" + category + ", brandName=" + brandName + ", model=" + model + '}';
    }

}
