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
import ru.homecompany.bank.dao.organization.OrganizationDao;
import ru.homecompany.bank.model.Organization;
import ru.homecompany.bank.view.organization.OrganizationFilter;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration("src/main/resources")
@Transactional
public class OrganizationDaoTest {

    @Autowired
    OrganizationDao organizationDao;

    /**
     * Testing all Organization methods from DAO layer
     */
    @Test
    public void test() {
        /*
         * Testing to find all list of organizations
         */
        List<Organization> organizations = organizationDao.findAll();
        Assert.assertNotNull(organizations);    //Assert.assertTrue(!organizations.isEmpty());
        Assert.assertEquals(2, organizations.size());

        Organization organization = organizationDao.findByName("ПАО \"Газпром\"");
        Assert.assertNotNull(organization);
        Assert.assertEquals(1, organization.getId().longValue());

        organization = organizationDao.findById(2);
        Assert.assertNotNull(organization);
        Assert.assertFalse(organization.getIsActive());

        /*
         * Testing to save(add) new Organization
         */
        Organization saveOrganization = new Organization();
        saveOrganization.setName("Bell Integrator");
        saveOrganization.setFullName("Bell Integrator Company");
        saveOrganization.setInn("3333333");
        saveOrganization.setKpp("3335555");
        saveOrganization.setAddress("ул. Пестеля, 16а, Москва");
        saveOrganization.setPhone("88003334533");
        saveOrganization.setIsActive(true); //Boolean.TRUE
        organizationDao.save(saveOrganization);
        /*
         * Check that the new Organization('Bell integrator') with auto incremented ID to value 3
         * was successfully added to H2 Data Base and now the size of all organizations will be equals 3
         */
        organizations = organizationDao.findAll();  //output to console the list of organizations
        for (Organization org : organizations) {
            System.out.println(org.toString());
        }
        Assert.assertEquals(3, organizations.size());   //System.out.println(saveOrganization.toString());
        Assert.assertEquals(3, saveOrganization.getId().longValue());
        Assert.assertEquals(3, organizationDao.findByName("Bell Integrator").getId().longValue());

        /*
         * Testing to find Organization by Filter
         */
        OrganizationFilter filter = new OrganizationFilter();
        filter.name = "Газ";
        filter.inn = "00";
        List<Organization> list = organizationDao.filterList(filter);
        Assert.assertNotNull(list);
        int countOrganizationsByFilter = 0;
        for (Organization org : list) {
            countOrganizationsByFilter++;
            System.out.println("By Filter(name+inn) " + org.toString());
        }
        Assert.assertEquals(1, countOrganizationsByFilter);
        /*
         * Testing to find Organization by Filter ACTIVITY (name isn't equals NULL but is EMPTY
         */
        OrganizationFilter filter1 = new OrganizationFilter();
        filter1.name = "";  //find all organization(without filter name)
        filter1.isActive = true;
        List<Organization> list1 = organizationDao.filterList(filter1);
        Assert.assertNotNull(list1);
        int countOrganizationsByFilter1 = 0;
        for (Organization org : list1) {
            countOrganizationsByFilter1++;
            System.out.println("By Filter(empty name+isActive) " + org.toString());
        }
        Assert.assertEquals(2, countOrganizationsByFilter1);
        Assert.assertEquals(3, list1.get(1).getId().longValue());   //Second organization in the Filter list is newly added "Bell Integrator" with ID 3

        /*
         * Testing to update Organization
         */
        Organization updatableOrganization = organizationDao.findById(3);
        updatableOrganization.setName("ЗАО \"Банк Кредит\"");
        updatableOrganization.setFullName("Закрытое Акционерное Общество \"Банк Кредит\"");
        updatableOrganization.setInn("111111111111");
        updatableOrganization.setKpp("999999999");
        updatableOrganization.setAddress("ул. Северный бульвар, 13, Москва");
        updatableOrganization.setPhone("89995555555");
        updatableOrganization.setIsActive(false);
        Assert.assertNotNull(updatableOrganization);
        organizationDao.update(updatableOrganization);
        System.out.println(updatableOrganization);
        int tmpCount = 0;
        List<Organization> list2 = organizationDao.findAll();
        for (Organization org : list2) {
            tmpCount++;
            System.out.println(org.toString());
        }
        Assert.assertEquals(3, tmpCount);    //total number of organizations after updating one of them has not changed and remained equal 3
        String tmp = "ЗАО \"Банк Кредит\"";
        Organization organizationUpdatable = organizationDao.findByName(tmp);
        Assert.assertNotNull(organizationUpdatable);
        Assert.assertEquals(3, updatableOrganization.getId().longValue());
    }
}
