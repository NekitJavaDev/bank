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
import ru.homecompany.bank.model.Organization;
import ru.homecompany.bank.service.organization.OrganizationService;
import ru.homecompany.bank.view.organization.OrganizationFilter;
import ru.homecompany.bank.view.organization.OrganizationFilterView;
import ru.homecompany.bank.view.organization.OrganizationView;

import java.util.List;

/**
 * Test of all Organization methods from SERVICE layer of application
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration("src/main/resources")
@Transactional
public class OrganizationServiceTest {

    @Autowired
    OrganizationService organizationService;

    /**
     * Test of Organization search by filter (different filter values)
     */
    @Test
    public void testGetListByFilter() {

        /*
         * Try to find organizations with empty filter or without required parameter
         */
        OrganizationFilter inputFilter555 = new OrganizationFilter();
        try {
            List<OrganizationFilterView> listEmptyValues = organizationService.findByFilter(inputFilter555);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            List<OrganizationFilterView> listNullFilter = organizationService.findByFilter(null);
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }

        /*
         * Find all organizations with 1 required parameter of filter - "name"
         */
        OrganizationFilter inputFilter = new OrganizationFilter();
        inputFilter.name = "";    //input empty value of "name" to find all organizations without filter
        List<OrganizationFilterView> list = organizationService.findByFilter(inputFilter);
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());
        for (OrganizationFilterView org : list) {
            System.out.println(org.toString());
        }

        /*
         * Find organization by filter's "name"
         */
        inputFilter.name = "Наш";
        List<OrganizationFilterView> list1 = organizationService.findByFilter(inputFilter);
        Assert.assertNotNull(list1);
        Assert.assertEquals(1, list1.size());
        Assert.assertFalse(list1.get(0).isActive);
        Assert.assertEquals(2, list1.get(0).id.intValue());
        System.out.println(list1.get(0).toString());

        /*
         * Find organization by filter's "inn" + "isActive"
         */
        inputFilter.name = "";
        inputFilter.inn = "3605";    //Part of INN (only 4 characters)
        inputFilter.isActive = true;
        List<OrganizationFilterView> list2 = organizationService.findByFilter(inputFilter);
        Assert.assertNotNull(list2);
        Assert.assertEquals(1, list2.size());
        Assert.assertEquals(1, list2.get(0).id.intValue());
        System.out.println(list2.get(0).toString());
    }

    /**
     * Test of Organization search by ID
     */
    @Test
    public void testFindById() {

        /*
         * Try to find nonexistent organization with ID = 10
         */
        try {
            Organization organizationEmpty = organizationService.findById(10);    //doesn't exist Organization with ID = 10
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

        /*
         * Find first organization
         */
        Organization organization = organizationService.findById(1);
        Assert.assertNotNull(organization);
        System.out.println(organization.toString());

        /*
         * Find second organization
         */
        Organization organization1 = organizationService.findById(2);
        Assert.assertNotNull(organization1);
        Assert.assertFalse(organization1.getIsActive());
        System.out.println(organization1.toString());
    }

    /**
     * Test of add new Organization and then update(change) some values
     */
    @Test
    public void testSaveAndUpdate() {

        /*
         * Try to add new organization without several required parameters(such as "name" or "inn")
         */
        OrganizationView viewNewTryAdd = new OrganizationView();
        viewNewTryAdd.fullName = "Общество с ограниченной ответсвенностью 'Bell Integrator2'";
        viewNewTryAdd.kpp = "329999459";
        viewNewTryAdd.address = "ул. Корейка, 54";
        try {
            organizationService.save(viewNewTryAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /*
         * Add new organization with empty (not required) values of "phone" and "isActive"
         */
        OrganizationView viewNew = new OrganizationView();
        viewNew.name = "Bell Integrator";
        viewNew.fullName = "Общество с ограниченной ответсвенностью 'Bell Integrator'";
        viewNew.inn = "123456779054";
        viewNew.kpp = "999999899";
        viewNew.address = "ул. Лубянка, 11";
        organizationService.save(viewNew);

        /*
         * Find new organization by ID
         */
        Organization newOrganization = organizationService.findById(3);
        Assert.assertNotNull(newOrganization);
        Assert.assertEquals("Bell Integrator", newOrganization.getName());
        System.out.println(newOrganization.toString());

        /*
         * Update values of empty (not required) variables of added organization. Change "kpp"
         */
        OrganizationView viewUpdate = new OrganizationView();
        viewUpdate.id = 3;
        viewUpdate.name = "Bell Integrator";
        viewUpdate.fullName = "Общество с ограниченной ответсвенностью 'Bell Integrator'";
        viewUpdate.inn = "123456779054";
        viewUpdate.kpp = "111222333";
        viewUpdate.address = "ул. Лубянка, 11";
        viewUpdate.phone = "88005556677";    //not required parameter
        viewUpdate.isActive = true;    //not required parameter
        organizationService.update(viewUpdate);

        /*
         * Find updatable organization by ID
         */
        Organization organizationUpdate = organizationService.findById(3);
        Assert.assertNotNull(organizationUpdate);
        Assert.assertEquals("Bell Integrator", organizationUpdate.getName());    //"name" wasn't changed
        Assert.assertEquals("111222333", organizationUpdate.getKpp());    //"kpp" was changed!!!
        Assert.assertTrue(organizationUpdate.getIsActive());
        System.out.println(organizationUpdate.toString());
    }
}