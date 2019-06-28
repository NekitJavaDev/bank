package ru.homecompany.bank.service;

import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.Application;
import ru.homecompany.bank.model.Employee;
import ru.homecompany.bank.service.employee.EmployeeService;
import ru.homecompany.bank.view.employee.EmployeeFilter;
import ru.homecompany.bank.view.employee.EmployeeFilterView;
import ru.homecompany.bank.view.employee.EmployeeView;

import java.util.List;

/**
 * Test of all Employee methods from SERVICE layer of application
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration("src/main/resources")
@Transactional
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    /**
     * Test of Employee search by filter (different filter values)
     */
    @Test
    public void testGetListByFilter() {

        /*
         * Try to find nonexistent employee with empty required parameter: "officeId"
         */
        EmployeeFilter employeeFilter = new EmployeeFilter();
        employeeFilter.firstName = "икита";
        employeeFilter.docCode = "601";
        try {
            List<EmployeeFilterView> listByNonexistentOfficeId = employeeService.findByFilter(employeeFilter);
        } catch (Exception e) {
            System.out.println(e.getMessage());    //ConstraintViolationException
        }

        /*
         * Try to find nonexistent employee with NULL filter (empty parameters)
         */
        try {
            List<EmployeeFilterView> listByNullFilter = employeeService.findByFilter(null);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

        /*
         * Find all employees with 1 required parameter of filter - "officeId"
         */
        try {
            EmployeeFilter filter = new EmployeeFilter();
            filter.officeId = 4;
            List<EmployeeFilterView> listByOfficeId = employeeService.findByFilter(filter);
            Assert.assertNotNull(listByOfficeId);
            Assert.assertEquals(2, listByOfficeId.size());
            Assert.assertEquals("водитель", listByOfficeId.get(1).position);
            for (EmployeeFilterView view : listByOfficeId) {
                System.out.println(view.toString());
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of Employee search by ID
     */
    @Test
    public void testFindById() {

        /*
         * Try to find nonexistent employee with ID = 10
         */
        try {
            Employee employeeEmpty = employeeService.findById(10);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

        /*
         * Find employee with ID = 1
         */
        try {
            Employee employee = employeeService.findById(1);
            Assert.assertNotNull(employee);
            Assert.assertEquals("Никита", employee.getFirstName());
            Assert.assertEquals("601", employee.getCountry().getCode());
            System.out.println(employee.toString());
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of add new Employee and then update(change) some values
     */
    @Test
    public void testSaveAndUpdate() {

        /*
         * Try to update employee without or null ID
         */
        EmployeeView viewWithoutId = new EmployeeView();
        viewWithoutId.firstName = "Андрей";
        viewWithoutId.position = "программсит";
        try {
            employeeService.update(viewWithoutId);    //without "id"
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

        /*
         * Try to update employee without other several required parameters (such as "firstName")
         */
        EmployeeView viewWithoutName = new EmployeeView();
        viewWithoutName.id = 1;
        viewWithoutName.position = "программсит";
        try {
            employeeService.update(viewWithoutName);    //without "firstName"
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /*
         * Try to add new employee without several required parameters (such as "officeId", "firstName" or "position")
         */
        EmployeeView viewSaveWithoutRequiredPosition = new EmployeeView();
        viewSaveWithoutRequiredPosition.firstName = "Антон";
        viewSaveWithoutRequiredPosition.position = "водитель";
        viewSaveWithoutRequiredPosition.isIdentified = true;
        try {
            employeeService.save(viewSaveWithoutRequiredPosition);    //without officeId
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        /*
         * Add new Employee with empty (not required) values (such as "secondName", "phone", "docName" or "docDate and others")
         */
        EmployeeView viewSaveNewEmployee = new EmployeeView();
        viewSaveNewEmployee.officeId = 3;
        viewSaveNewEmployee.firstName = "Антон";
        viewSaveNewEmployee.position = "водитель";
        viewSaveNewEmployee.docCode = "44";
        employeeService.save(viewSaveNewEmployee);

        /*
         * Find new added Employee by ID
         */
        Employee newEmployee = employeeService.findById(6);
        Assert.assertNotNull(newEmployee);
        Assert.assertNull(newEmployee.getDocDate());
        Assert.assertNull(newEmployee.getIsIdentified());


        /*
         * Find all employees of 3 office. (Count of people will be equals 2)
         */
        EmployeeFilter thirdOfficeFilter = new EmployeeFilter();
        thirdOfficeFilter.officeId = 3;
        try {
            List<EmployeeFilterView> employeesOfThirdOffice = employeeService.findByFilter(thirdOfficeFilter);
            Assert.assertNotNull(employeesOfThirdOffice);
            Assert.assertEquals(2, employeesOfThirdOffice.size());
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }


    }

}
