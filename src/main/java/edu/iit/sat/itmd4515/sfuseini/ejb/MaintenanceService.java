/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.ejb;
import edu.iit.sat.itmd4515.sfuseini.domain.Maintenance;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Named
@Stateless
public class MaintenanceService extends BaseService<Maintenance> {

    /**
     *
     */
    public MaintenanceService() {
        super(Maintenance.class);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Maintenance findByMaintenanceId(int id) {
        return getEntityManager()
                .createNamedQuery("Maintenance.findByMaintenanceId", Maintenance.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Maintenance> findAll() {
        return getEntityManager().createNamedQuery("Maintenance.findAll", Maintenance.class).getResultList();
    }

    /**
     *
     * @param jsfMaintenance
     */
    @Override
    public void update(Maintenance jsfMaintenance) {
        Maintenance oldMaintenance = getEntityManager().find(Maintenance.class, jsfMaintenance.getId());
        
        jsfMaintenance.setId(oldMaintenance.getId());
        getEntityManager().merge(jsfMaintenance);
    }
    
    /**
     *
     * @param maintenance
     */
    @Override
    public void create(Maintenance maintenance) {
        super.create(maintenance); //To change body of generated methods, choose Tools | Templates.
    }
    
}
