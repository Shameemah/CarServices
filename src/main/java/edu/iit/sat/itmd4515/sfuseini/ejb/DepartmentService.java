/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.ejb;

import edu.iit.sat.itmd4515.sfuseini.domain.Department;
import edu.iit.sat.itmd4515.sfuseini.domain.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author shameemahfuseini-codjoe
 */
@Named
@Stateless
public class DepartmentService extends BaseService<Department> {

    /**
     *
     */
    public DepartmentService() {
        super(Department.class);
    }

    /**
     *
     * @param departmentId
     * @return
     */
    public Department findByDepartmentId(String departmentId) {
        return getEntityManager()
                .createNamedQuery("Department.findByDepartmentId", Department.class)
                .setParameter("departmentId", departmentId)
                .getSingleResult();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Department> findAll() {
        return getEntityManager().createNamedQuery("Department.findAll", Department.class).getResultList();
    }

    /**
     *
     * @param jsfDep
     */
    @Override
    public void update(Department jsfDep) {
        Department oldDepartment = getEntityManager().find(Department.class, jsfDep.getDepartmentId());

        jsfDep.setDepartmentId(oldDepartment.getDepartmentId());
        getEntityManager().merge(jsfDep);
    }

    /**
     *
     * @param department
     */
    @Override
    public void create(Department department) {
        super.create(department); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param d
     */
    @Override
    public void remove(Department d) {
        super.remove(d); //To change body of generated methods, choose Tools | Templates.
    }
}
