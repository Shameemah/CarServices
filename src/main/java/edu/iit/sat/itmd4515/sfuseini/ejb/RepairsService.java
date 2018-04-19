/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.ejb;
import edu.iit.sat.itmd4515.sfuseini.domain.Repairs;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Named
@Stateless
public class RepairsService extends BaseService<Repairs> {

    /**
     *
     */
    public RepairsService() {
        super(Repairs.class);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Repairs findByRepairId(int id) {
        return getEntityManager()
                .createNamedQuery("Repairs.findByRepairId", Repairs.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Repairs> findAll() {
        return getEntityManager().createNamedQuery("Repairs.findAll", Repairs.class).getResultList();
    }

    /**
     *
     * @param jsfRepairs
     */
    @Override
    public void update(Repairs jsfRepairs) {
        Repairs oldRepairs = getEntityManager().find(Repairs.class, jsfRepairs.getId());
        
        jsfRepairs.setId(oldRepairs.getId());
        getEntityManager().merge(jsfRepairs);
    }
    
    /**
     *
     * @param repairs
     */
    @Override
    public void create(Repairs repairs) {
        super.create(repairs); //To change body of generated methods, choose Tools | Templates.
    }
    
}
