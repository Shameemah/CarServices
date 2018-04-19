/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.ejb;

import edu.iit.sat.itmd4515.sfuseini.domain.security.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Shameemah
 */
@Named
@Stateless
public class UserService extends BaseService<User> {

    /**
     *
     */
    public UserService() {
        super(User.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return getEntityManager().createNamedQuery("User.findAll", User.class).getResultList();
    }

    /**
     *
     * @param user
     */
    @Override
    public void create(User user) {
        super.create(user); //To change body of generated methods, choose Tools | Templates.
    }
}
