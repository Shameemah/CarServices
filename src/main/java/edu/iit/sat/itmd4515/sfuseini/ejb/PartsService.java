/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.ejb;

import edu.iit.sat.itmd4515.sfuseini.domain.Parts;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Named
@Stateless
public class PartsService extends BaseService<Parts> {

    /**
     *
     */
    public PartsService() {
        super(Parts.class);
    }

    /**
     *
     * @param serialNumber
     * @return
     */
    public Parts findByPartId(int serialNumber) {
        return getEntityManager()
                .createNamedQuery("Parts.findByPartsId", Parts.class)
                .setParameter("serialNumber", serialNumber)
                .getSingleResult();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Parts> findAll() {
        return getEntityManager().createNamedQuery("Parts.findAll", Parts.class).getResultList();
    }

    /**
     *
     * @param jsfPart
     */
    @Override
    public void update(Parts jsfPart) {
        Parts oldPart = getEntityManager().find(Parts.class, jsfPart.getSerialNumber());

        jsfPart.setSerialNumber(oldPart.getSerialNumber());
        getEntityManager().merge(jsfPart);
    }

    /**
     *
     * @param part
     */
    @Override
    public void create(Parts parts) {
        super.create(parts); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param e
     */
    @Override
    public void remove(Parts e) {
        super.remove(e); //To change body of generated methods, choose Tools | Templates.
    }
}
