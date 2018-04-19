/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.ejb;

import edu.iit.sat.itmd4515.sfuseini.domain.Tool;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Named
@Stateless
public class ToolService extends BaseService<Tool> {

    /**
     *
     */
    public ToolService() {
        super(Tool.class);
    }

    /**
     *
     * @param serialNumber
     * @return
     */
    public Tool findByToolId(int serialNumber) {
        return getEntityManager()
                .createNamedQuery("Tool.findByToolId", Tool.class)
                .setParameter("serialNumber", serialNumber)
                .getSingleResult();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tool> findAll() {
        return getEntityManager().createNamedQuery("Tool.findAll", Tool.class).getResultList();
    }

    /**
     *
     * @param jsfTool
     */
    @Override
    public void update(Tool jsfTool) {
        Tool oldTool = getEntityManager().find(Tool.class, jsfTool.getSerialNumber());

        jsfTool.setSerialNumber(oldTool.getSerialNumber());
        getEntityManager().merge(jsfTool);
    }

    /**
     *
     * @param tool
     */
    @Override
    public void create(Tool tool) {
        super.create(tool); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param t
     */
    @Override
    public void remove(Tool t) {
        super.remove(t); //To change body of generated methods, choose Tools | Templates.
    }

}
