/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.ejb;
import edu.iit.sat.itmd4515.sfuseini.domain.Checkout;
import edu.iit.sat.itmd4515.sfuseini.domain.Parts;
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
public class CheckoutService extends BaseService<Checkout> {

    /**
     *
     */
    public CheckoutService() {
        super(Checkout.class);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Checkout findByCheckoutId(int id) {
        return getEntityManager()
                .createNamedQuery("Checkout.findByCheckoutId", Checkout.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Checkout> findAll() {
        return getEntityManager().createNamedQuery("Checkout.findAll", Checkout.class).getResultList();
    }

    /**
     *
     * @param c
     */
    @Override
    public void create(Checkout c) {
        super.create(c); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param jsfCheckout
     */
    @Override
    public void update(Checkout jsfCheckout) {
        Checkout oldCheckout = getEntityManager().find(Checkout.class, jsfCheckout.getId());
        jsfCheckout.setId(oldCheckout.getId());
        jsfCheckout.setCheckoutDate(oldCheckout.getCheckoutDate());
        jsfCheckout.setEmployee(oldCheckout.getEmployee());
        jsfCheckout.setTool(oldCheckout.getTool());
        getEntityManager().merge(jsfCheckout);
    }
    
    
}
