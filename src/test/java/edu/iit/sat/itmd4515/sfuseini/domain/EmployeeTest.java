/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.sfuseini.domain;

import edu.iit.sat.itmd4515.sfuseini.domain.Department;
import edu.iit.sat.itmd4515.sfuseini.domain.Employee;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author shameemahfuseini-codjoe
 */
public class EmployeeTest {

    private static EntityManagerFactory emf;
    private static Validator validator;
    private EntityManager em;
    private EntityTransaction et;
    private static final Logger LOG = Logger.getLogger(EmployeeTest.class.getName());

    Date date = new Date();
    SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");

    /**
     *
     */
    @BeforeClass
    public static void beforeClassTesyFixture() {
        emf = Persistence.createEntityManagerFactory("itmd4515PU_TEST");
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     *
     */
    @AfterClass
    public static void afterClassTestFixture() {
        emf.close();
    }

    /**
     *
     */
    @Before
    public void beforeEachTestMethod() {
        em = emf.createEntityManager();
        et = em.getTransaction();

        try {
            date = dateformat3.parse("02/04/1992");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Employee seed = new Employee("Mock", "Employee", "manager", date, 10000);
        et.begin();
        em.persist(seed);
        et.commit();
    }

    /**
     *
     */
    @After
    public void afterEachTestMethod() {

        Employee seed = em.createNamedQuery("Employee.findBylastName", Employee.class).setParameter("lastName", "Mock").getSingleResult();
        et.begin();
        em.remove(seed);
        et.commit();
        em.close();
    }

    /**
     *
     */
    @Test
    public void validatePastDateSunnyDay() {
        Employee emp5 = new Employee("Adedayo", "Olufemi", "supervisor", date, 10000);
        Set<ConstraintViolation<Employee>> violations = validator.validate(emp5);
        assertTrue(violations.isEmpty());
    }

    /**
     *
     */
    @Test
    public void validatePastDateandNullNameRainyDay() {

        try {
            date = dateformat3.parse("02/04/2021");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Employee emp5 = new Employee(null, "Olufemi", "supervisor", date, 10000);
        Set<ConstraintViolation<Employee>> violations = validator.validate(emp5);
        for (ConstraintViolation<Employee> violation : violations) {
            LOG.info(violation.toString());
        }

        assertFalse(violations.isEmpty());
        assertTrue(violations.size() == 2);
        assertEquals(violations.size(), 2);
    }

    /**
     *
     */
    @Test
    public void validateEmployeeDepartmentRelationshipSunnyDay() {
        Department dep1 = new Department("RFN1", "Roofing", "In charge of monitoring "
                + "roofing supplies and providing skilled roofers for all building projects");
        Employee emp = new Employee("Karen", "Wilson", "laborer", date, 2000);

        assertNull("Department ID for employee should be NULL before commit", emp.getDepartment());
        emp.setDepartment(dep1);
        et.begin();
        em.persist(dep1);
        em.persist(emp);
        et.commit();
        assertNotNull("Department ID for employee should be not be NULL after commit persist", emp.getDepartment());
        assertTrue("Department ID should be RFN1", emp.getDepartment().getDepartmentId().equals("RFN1"));
    }

    /**
     *
     */
    @Test
    public void validateEmployeeDepartmentRelationshipRainyDay() {
        Department dep2 = new Department("TLN1", "Tiling", "In charge of monitoring "
                + "tiling supplies and providing skilled tilers for all building projects");
        Employee emp6 = new Employee("Hambe", "Kosha", "laborer", date, 2500);

        emp6.setDepartment(dep2);
        et.begin();
        em.persist(dep2);
        em.persist(emp6);
        et.commit();
        assertFalse("Department ID should not be empty", emp6.getDepartment().getDepartmentId().isEmpty());
    }

    /**
     *
     */
    @Test
    public void verifySeedData() {
        List<Employee> seeds
                = em.createNamedQuery("Employee.findBylastName", Employee.class)
                        .setParameter("lastName", "Mock")
                        .getResultList();

        assertEquals(seeds.size(), 1);
        assertSame(seeds.get(0).getLastName(), "Mock");
    }

    /**
     *
     */
    @Test
    public void persistNewEmployeeTest() {
        Employee emp1 = new Employee("Doe", "Jane", "manager", date, 10000);

        et.begin();
        assertNull("Employee ID should be NULL before persist", emp1.getId());
        em.persist(emp1);
        assertNull("Employee ID should be NULL after persist and before commit", emp1.getId());
        et.commit();
        assertNotNull("Employee ID should not be NULL after commit", emp1.getId());
        assertTrue("Employee ID should be greater than 0 after commit", emp1.getId() > 0);
    }

    /**
     *
     */
    @Test
    public void readEmployeeTest() {
        Employee emp2 = new Employee("Johnson", "John", "manager", date, 10000);

        et.begin();
        em.persist(emp2);
        et.commit();

        et.begin();
        Employee employee = em.find(Employee.class, emp2.getId());
        et.commit();
        assertNotNull("Employee should not be NULL after commit", employee);
        assertTrue("Employee username should be mjohnson", emp2.getLastName().equals("Johnson"));
        System.out.println(employee.toString());
    }

    /**
     *
     */
    @Test
    public void updateEmployeeTest() {

        Employee emp3 = new Employee("Patterson", "Carl", "manager", date, 10000);

        et.begin();
        em.persist(emp3);
        et.commit();

        et.begin();
        Employee employee = em.find(Employee.class, emp3.getId());
        employee.setRole("field worker");
        et.commit();
        assertNotNull("Employee should not be NULL after commit", employee);
        assertTrue("Employee role should be field worker", emp3.getRole().equals("field worker"));
        assertFalse("Employee role should not be construction worker", emp3.getRole().equals("construction worker"));
    }

    /**
     *
     */
    @Test
    public void removeEmployeeTest() {

        Employee emp4 = new Employee("Jackson", "Andrew", "manager", date, 10000
        );

        et.begin();
        em.persist(emp4);
        em.remove(emp4);
        et.commit();
        assertNull("Employee should now be NULL after commit", emp4.getId());
    }
}
