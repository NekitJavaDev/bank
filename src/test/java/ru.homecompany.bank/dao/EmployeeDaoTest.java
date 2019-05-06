package ru.homecompany.bank.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.Application;
import ru.homecompany.bank.dao.country.CountryDao;
import ru.homecompany.bank.dao.document.DocumentDao;
import ru.homecompany.bank.dao.employee.EmployeeDao;
import ru.homecompany.bank.dao.office.OfficeDao;
import ru.homecompany.bank.model.Country;
import ru.homecompany.bank.model.Document;
import ru.homecompany.bank.model.Employee;
import ru.homecompany.bank.model.Office;
import ru.homecompany.bank.view.employee.EmployeeFilter;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration("src/main/resources")
@Transactional
public class EmployeeDaoTest {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    OfficeDao officeDao;

    @Autowired
    DocumentDao documentDao;

    @Autowired
    CountryDao countryDao;

    /**
     * Testing all Employee methods from DAO layer
     */
    @Test
    public void test() {

        /*
         * Testing to find all list of Offices
         */
        List<Employee> all = employeeDao.findAll();
        Assert.assertNotNull(all);
        for (Employee eml : all) {
            System.out.println(eml.toString());
        }
        Assert.assertEquals(5, all.size());

        /*
         * Testing to find Employee by ID
         */
        Employee employeeById = employeeDao.findById(3);
        Assert.assertNotNull(employeeById);
        Assert.assertEquals("Борис", employeeById.getFirstName());
        Assert.assertEquals(3, employeeById.getOffice().getId().longValue());

        /*
         * Testing to find Employee by Filter with REQUIRED PARAMETER: OFFICE_ID + potential parameter firstNAme and citizenshipCode
         */
        EmployeeFilter filter = new EmployeeFilter();
        filter.officeId = 3;
        filter.firstName = "рис";
        List<Employee> employeeByFilterList = employeeDao.findByFilter(filter);
        Assert.assertNotNull(employeeByFilterList);
        for (Employee emp : employeeByFilterList) {
            System.out.println(emp.toString());
        }
        Assert.assertEquals(3, employeeByFilterList.get(0).getId().longValue());

        EmployeeFilter filter1 = new EmployeeFilter();
        filter1.officeId = 1;
        filter1.citizenshipCode = "601";
        List<Employee> employeeList = employeeDao.findByFilter(filter1);
        Assert.assertNotNull(employeeList);
        for (Employee emp : employeeList) {
            System.out.println(emp.toString());
        }
        Assert.assertEquals(1, employeeList.size());
        Assert.assertEquals("Российская Федерация", employeeList.get(0).getCountry().getName());
        Assert.assertEquals("Никита", employeeList.get(0).getFirstName());

        /*
         * Testing to save(add) new Employee
         */
        Office setableOffice = officeDao.findById(1);
        Document setableDocument = documentDao.findByCode(String.valueOf(21));
        Country setableCountry = countryDao.findByCode(String.valueOf(601));
        Assert.assertNotNull(setableOffice);
        Employee savableEmployee = new Employee();
        savableEmployee.setOffice(setableOffice);
        savableEmployee.setFirstName("Евгений");
        savableEmployee.setPosition("программист");
        savableEmployee.setDocument(setableDocument);
        savableEmployee.setCountry(setableCountry);
        employeeDao.save(savableEmployee);
        Employee empl2 = employeeDao.findById(6);
        System.out.println(empl2.toString());
        Assert.assertEquals("Евгений", empl2.getFirstName());

        /*
         * Testing to update Employee by ID
         */
        Employee updatebleEmployee = employeeDao.findById(6);
        updatebleEmployee.setFirstName("Евгения");
        updatebleEmployee.setSecondName("Лоськова");
        updatebleEmployee.setPosition("младший программист");
        updatebleEmployee.setIsIdentified(true);
        employeeDao.update(updatebleEmployee);
        System.out.println(employeeDao.findById(6).toString());
        Assert.assertEquals("Лоськова", employeeDao.findById(6).getSecondName());
    }
}
