/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.domain.security;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Entity
@Table(name = "sec_user")
@NamedQuery(name = "User.findAll", query = "select u from User u ")
public class User {

    @Id
    private String userName;
    private String password;
    private Boolean enabled;

    @ManyToMany
    @JoinTable(name = "sec_user_groups", joinColumns = @JoinColumn(name = "USERNAME"), inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))
    private List<Group> groups = new ArrayList<>();

    /**
     *
     * @param g
     */
    public void addGroup(Group g) {
        if (!this.groups.contains(g)) {
            this.groups.add(g);
        }
        if (!g.getUsers().contains(this)) {
            g.getUsers().add(this);
        }
    }

    /**
     *
     */
    public User() {
    }

    /**
     *
     * @param userName
     * @param password
     * @param enabled
     */
    public User(String userName, String password, Boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    /**
     *
     * @param userName
     * @param password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    

    
    @PrePersist
    @PreUpdate
    private void hashPassword() {
        String sha256hex = Hashing.sha256()
                .hashString(this.password, StandardCharsets.UTF_8)
                .toString();
        
        this.password = sha256hex;
    }

    /**
     * Get the value of enabled
     *
     * @return the value of enabled
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Set the value of enabled
     *
     * @param enabled new value of enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the value of groups
     *
     * @return the value of groups
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * Set the value of groups
     *
     * @param groups new value of groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
