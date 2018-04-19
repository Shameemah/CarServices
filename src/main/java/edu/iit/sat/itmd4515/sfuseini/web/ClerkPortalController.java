/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.web;

import edu.iit.sat.itmd4515.sfuseini.domain.Parts;
import edu.iit.sat.itmd4515.sfuseini.domain.Repairs;
import edu.iit.sat.itmd4515.sfuseini.domain.Tool;
import edu.iit.sat.itmd4515.sfuseini.ejb.PartsService;
import edu.iit.sat.itmd4515.sfuseini.ejb.RepairsService;
import edu.iit.sat.itmd4515.sfuseini.ejb.ToolService;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Named
@RequestScoped
public class ClerkPortalController extends BaseController {

    private static final Logger LOG = Logger.getLogger(ClerkPortalController.class.getName());

    @EJB
    private PartsService partsService;
    @EJB
    private ToolService toolService;
    @EJB
    private RepairsService repairsService;

    private List<Repairs> repairs;
    private List<Tool> tool;
    private List<Parts> parts;
    private Repairs singleRepair;
    private Parts singlePart;
    private Tool singleTool;
    private int repairId;
    private int toolId;
    private int partId;

    /**
     *
     */
    public ClerkPortalController() {
    }

    /**
     *
     */
    @Override
    @PostConstruct
    public void postConstruct() {
        super.postConstruct(); //To change body of generated methods, choose Tools | Templates.
        repairs = repairsService.findAll();
        parts = partsService.findAll();
        tool = toolService.findAll();
        this.singleRepair = new Repairs();
        this.singlePart = new Parts();
        this.singleTool = new Tool();
    }

    //<----------Action Methods -------------->
    //<----- repairs---->

    /**
     *
     * @return
     */
    public String doCreateRepair() {
        this.singleRepair = new Repairs();
        LOG.info("calling create method" + singleRepair.toString());
        return "/clerk/updateRepair.xhtml";
    }

    /**
     *
     * @param m
     * @return
     */
    public String doViewRepair(Repairs r) {
        this.singleRepair = r;
        LOG.info("calling view method" + singleRepair.toString());
        return "/clerk/viewRepair.xhtml";
    }

    /**
     *
     * @param m
     * @return
     */
    public String doUpdateRepair(Repairs r) {
        this.singleRepair = r;
        LOG.info("calling update method" + singleRepair.toString());
        return "/clerk/updateRepair.xhtml";
    }

    /**
     *
     * @return
     */
    public String doExecuteRepairUpdate() {
        if (this.singleRepair.getId() != null) {
            LOG.info("calling execute update method" + singleRepair.toString());
            repairsService.update(singleRepair);
            repairs = repairsService.findAll();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Update Successful!", "Successfully updated repair ID#"
                    + singleRepair.getId()));
        } else {
            LOG.info("calling execute employee create method" + singleRepair.toString());
            repairsService.create(singleRepair);
            repairs = repairsService.findAll();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Success!", "Successfully added repair ID# "
                    + singleRepair.getId()));
        }
        return "/clerk/welcome.xhtml";
    }

    /**
     *
     * @param repairId
     * @return
     */
    public String doFindRepair(int repairId) {
        repairId = this.repairId;
        try {
            this.singleRepair = repairsService.findByRepairId(repairId);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No repair entry found!", "This entry does not exist."));
            externalContext.getFlash().setKeepMessages(true);
            return "/clerk/welcome.xhtml?faces-redirect=true\"";
        }
        return "/clerk/viewRepair.xhtml";
    }

    //<-------------------- Tool--------------------------->

    /**
     *
     * @return
     */
    public String doCreateTool() {
        this.singleTool = new Tool();
        LOG.info("calling create method" + singleTool.toString());
        return "/clerk/createTool.xhtml";
    }

    /**
     *
     * @param t
     * @return
     */
    public String doViewTool(Tool t) {
        this.singleTool = t;
        LOG.info("calling view method" + singleTool.toString());
        return "/clerk/viewTool.xhtml";
    }

    /**
     *
     * @param t
     * @return
     */
    public String doUpdateTool(Tool t) {
        this.singleTool = t;
        LOG.info("calling update method" + singleTool.toString());
        return "/clerk/updateTool.xhtml";
    }

    /**
     *
     * @param t
     * @return
     */
    public String doDeleteTool(Tool t) {
        LOG.info("calling delete method" + singleTool.toString());
        toolService.remove(t);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Delete Successful!", "Deleted tool ID#" + t.getSerialNumber()));
        externalContext.getFlash().setKeepMessages(true);
        return "/clerk/welcome.xhtml?faces-redirect=true\"";
    }

    /**
     *
     * @return
     */
    public String doExecuteToolUpdate() {
        LOG.info("calling execute tool update method" + singleTool.toString());
        toolService.update(singleTool);
        tool = toolService.findAll();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Update Successful!", "Successfully updated tool ID#"
                + singleTool.getSerialNumber()));
        return "/clerk/welcome.xhtml";
    }

    /**
     *
     * @return
     */
    public String doExecuteToolCreate() {
        LOG.info("calling execute employee create method" + singlePart.toString());
        toolService.create(singleTool);
        tool = toolService.findAll();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
                "Successfully added tool ID#"
                + singleTool.getSerialNumber()));

        return "/clerk/welcome.xhtml";
    }

    /**
     *
     * @param toolId
     * @return
     */
    public String doFindTool(int toolId) {
        toolId = this.toolId;
        try {
            this.singleTool = toolService.findByToolId(toolId);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No tool found!", "This tool is not in the system."));
            externalContext.getFlash().setKeepMessages(true);
            return "/clerk/welcome.xhtml?faces-redirect=true\"";
        }
        return "/clerk/viewTool.xhtml";
    }

    //<-------------------- parts--------------------------->

    /**
     *
     * @return
     */
    public String doCreatePart() {
        this.singlePart = new Parts();
        LOG.info("calling create method" + singlePart.toString());
        return "/clerk/createPart.xhtml";
    }

    /**
     *
     * @param p
     * @return
     */
    public String doViewPart(Parts p) {
        this.singlePart = p;
        LOG.info("calling view method" + singlePart.toString());
        return "/clerk/viewPart.xhtml";
    }

    /**
     *
     * @param p
     * @return
     */
    public String doUpdatePart(Parts p) {
        this.singlePart = p;
        LOG.info("calling update method" + singlePart.toString());
        return "/clerk/updatePart.xhtml";
    }

    /**
     *
     * @param p
     * @return
     */
    public String doDeletePart(Parts p) {
        LOG.info("calling delete method" + singlePart.toString());
        partsService.remove(p);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Delete Successful!", "Deleted part ID#" + p.getSerialNumber()));
        externalContext.getFlash().setKeepMessages(true);
        return "/clerk/welcome.xhtml?faces-redirect=true\"";
    }

    /**
     *
     * @return
     */
    public String doExecutePartUpdate() {
        LOG.info("calling execute employee update method" + singlePart.toString());
        partsService.update(singlePart);
        parts = partsService.findAll();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Update Successful!", "Successfully updated part ID# "
                + singlePart.getSerialNumber()));
        return "/clerk/welcome.xhtml";
    }

    /**
     *
     * @return
     */
    public String doExecutePartCreate() {
        LOG.info("calling execute employee create method" + singlePart.toString());
        partsService.create(singlePart);
        parts = partsService.findAll();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
                "Successfully added part ID#"
                + singlePart.getSerialNumber()));

        return "/clerk/welcome.xhtml";
    }

    /**
     *
     * @param partId
     * @return
     */
    public String doFindPart(int partId) {
        partId = this.partId;
        try {
            this.singlePart = partsService.findByPartId(partId);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No part found!", "This part is not in the system."));
            externalContext.getFlash().setKeepMessages(true);
            return "/clerk/welcome.xhtml?faces-redirect=true\"";
        }
        return "/clerk/viewPart.xhtml";
    }

    //<---------Getters and Setters ------------>

    /**
     *
     * @return
     */
    public List<Repairs> getRepairs() {
        return repairs;
    }

    /**
     *
     * @param repairs
     */
    public void setRepairs(List<Repairs> repairs) {
        this.repairs = repairs;
    }

    /**
     *
     * @return
     */
    public List<Tool> getTool() {
        return tool;
    }

    /**
     *
     * @param tool
     */
    public void setTool(List<Tool> tool) {
        this.tool = tool;
    }

    /**
     *
     * @return
     */
    public List<Parts> getParts() {
        return parts;
    }

    /**
     *
     * @param parts
     */
    public void setParts(List<Parts> parts) {
        this.parts = parts;
    }

    /**
     *
     * @return
     */
    public Repairs getSingleRepair() {
        return singleRepair;
    }

    /**
     *
     * @param singleRepair
     */
    public void setSingleRepair(Repairs singleRepair) {
        this.singleRepair = singleRepair;
    }

    /**
     *
     * @return
     */
    public Parts getSinglePart() {
        return singlePart;
    }

    /**
     *
     * @param singlePart
     */
    public void setSinglePart(Parts singlePart) {
        this.singlePart = singlePart;
    }

    /**
     *
     * @return
     */
    public Tool getSingleTool() {
        return singleTool;
    }

    /**
     *
     * @param singleTool
     */
    public void setSingleTool(Tool singleTool) {
        this.singleTool = singleTool;
    }

    /**
     *
     * @return
     */
    public int getRepairId() {
        return repairId;
    }

    /**
     *
     * @param repairId
     */
    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    /**
     *
     * @return
     */
    public int getToolId() {
        return toolId;
    }

    /**
     *
     * @param toolId
     */
    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    /**
     *
     * @return
     */
    public int getPartId() {
        return partId;
    }

    /**
     *
     * @param partId
     */
    public void setPartId(int partId) {
        this.partId = partId;
    }

}
