/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Entity
@Table(name = "department")
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "select d from Department d")
    ,
    @NamedQuery(name = "Department.findByDepartmentId", query = "select  d from Department d where d.departmentId = :departmentId")
})
public class Department implements Serializable {

    @Id
    @NotNull(message = "Department ID cannot be null")
    private String departmentId;
    private String departmentName;
    private String departmentDescription;

    @OneToMany(mappedBy = "department")
    private List<Employee> employee = new ArrayList<>();

    /**
     *
     */
    public Department() {
    }

    /**
     *
     * @param departmentId
     */
    public Department(String departmentId) {
        this.departmentId = departmentId;
    }
    
    

    /**
     *
     * @param departmentId
     * @param departmentName
     * @param departmentDescription
     */
    public Department(String departmentId, String departmentName, String departmentDescription) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
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

    /**
     *
     * @return
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     *
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     *
     * @return
     */
    public String getDepartmentDescription() {
        return departmentDescription;
    }

    /**
     *
     * @param departmentDescription
     */
    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
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
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.departmentId);
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
        final Department other = (Department) obj;
        if (!Objects.equals(this.departmentId, other.departmentId)) {
            return false;
        }
        return true;
    } 

    @Override
    public String toString() {
        return "Department{" + "departmentId=" + departmentId + ", departmentName=" + departmentName + ", departmentDescription=" + departmentDescription + '}';
    }

}
