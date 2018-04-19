/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.web;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shameemahfuseini-codjoe
 */
public abstract class BaseController {

    FacesContext context;
    ExternalContext externalContext;

    /**
     *
     */
    protected BaseController() {
    }

    /**
     *
     */
    @PostConstruct
    public void postConstruct() {
        context = FacesContext.getCurrentInstance();
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
    }
}
