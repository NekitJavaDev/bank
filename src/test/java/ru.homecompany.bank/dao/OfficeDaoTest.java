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
import ru.homecompany.bank.dao.office.OfficeDao;
import ru.homecompany.bank.dao.organization.OrganizationDao;
import ru.homecompany.bank.model.Office;
import ru.homecompany.bank.model.Organization;
import ru.homecompany.bank.view.office.OfficeFilter;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration("src/main/resources")
@Transactional
public class OfficeDaoTest {

    @Autowired
    OfficeDao officeDao;

    @Autowired
    OrganizationDao organizationDao;

    /**
     * Testing all Office methods from DAO layer
     */
    @Test
    public void test() {

        /*
         * Testing to find all list of Offices
         */
        List<Office> all = officeDao.findAll();
        Assert.assertNotNull(all);
        int count = 0;
        for (Office office : all) {
            count++;
            System.out.println(office);
        }
        Assert.assertEquals(4, count);

        /*
         * Testing to find Office by ID
         */
        Office officeById = officeDao.findById(3);
        Assert.assertNotNull(officeById);
        Assert.assertEquals("ул. Космонавта Волкова, 10, Москва", officeById.getAddress());
        Assert.assertEquals(2, officeById.getOrganization().getId().longValue());

        /*
         * Testing to save(add) new Office
         */
        Organization setableOrganization = organizationDao.findById(2);
        Assert.assertNotNull(setableOrganization);
        Office saveOffice = new Office();
        saveOffice.setOrganization(setableOrganization);
        saveOffice.setName("Офис 333333");
        saveOffice.setAddress("ул. Плещеева, 55в, Москва");
        saveOffice.setPhone("84954444444");
        saveOffice.setIsActive(false);
        officeDao.save(saveOffice);
        List<Office> tmpAll = officeDao.findAll();
        count = 0;
        for (Office office : tmpAll) {
            count++;
            System.out.println(office);
        }
        Assert.assertEquals(5, count);

        /*
         * Testing to update Office by ID
         */
        Organization updatebleOrganization = organizationDao.findById(1);
        Office updateOffice = officeDao.findById(5);
        updateOffice.setName("Офис 555333");
        updateOffice.setAddress("ул. Мишина, 13, Санкт-Петербург");
        updateOffice.setIsActive(true);
        updateOffice.setOrganization(updatebleOrganization);
        officeDao.update(updateOffice);
        List<Office> tmp1All = officeDao.findAll();
        count = 0;
        for (Office office : tmp1All) {
            count++;
            System.out.println(office);
        }
        Assert.assertEquals(5, count);
        Assert.assertEquals("Офис 555333", officeDao.findById(5).getName());
        Assert.assertEquals(1, officeDao.findById(5).getOrganization().getId().longValue());

        /*
         * Testing to find Offices without Filter with REQUIRED PARAMETER: orgId
         */
        List<Office> pathValueOffices = officeDao.filterList(1, null);
        count = 0;
        for (Office office : pathValueOffices) {
            count++;
            System.out.println(office);
        }
        Assert.assertEquals(3, count);

        /*
         * Testing to find Offices by Filter with REQUIRED PARAMETER: ORG_ID + potential parameter NAME
         */
        OfficeFilter filter = new OfficeFilter();
        filter.name = "555";
        System.out.println("FILTER FROM TEST: " + filter.name + filter.phone + filter.isActive);
        List<Office> nameParamOffices = officeDao.filterList(1, filter);
        count = 0;
        for (Office office : nameParamOffices) {
            count++;
            System.out.println(office);
        }
        Assert.assertNotNull(nameParamOffices);
        Assert.assertEquals(1, count);
        Assert.assertEquals(5, officeDao.findByAddress("ул. Мишина, 13, Санкт-Петербург").getId().longValue());

        /*
         * Testing to find Office by Filter with REQUIRED PARAMETER: ORG_ID + potential parameter IS_ACTIVE
         */
        OfficeFilter filter1 = new OfficeFilter();
        filter1.isActive = true;
        System.out.println("FILTER FROM TEST: " + filter1.name + filter1.phone + filter1.isActive);
        List<Office> isActiveParamOffices = officeDao.filterList(1, filter1); //All offices from 1-st (first) company which are TRUE
        count = 0;
        for (Office office : isActiveParamOffices) {
            count++;
            System.out.println(office);
        }
        Assert.assertNotNull(isActiveParamOffices);
        Assert.assertEquals(2, count);
        Assert.assertEquals(5, isActiveParamOffices.get(1).getId().longValue());
    }
}
