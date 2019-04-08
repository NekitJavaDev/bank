package ru.homecompany.bank.dao;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.Application;
import ru.homecompany.bank.dao.organization.OrganizationDao;
import ru.homecompany.bank.model.Organization;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration("src/main/resources")
@Transactional
@DirtiesContext
public class OrganizationDaoTest {

    @Autowired
    OrganizationDao organizationDao;

    @Test
    public void test() {

        List<Organization> organizations = organizationDao.findAll();
        Assert.assertNotNull(organizations);    //Assert.assertTrue(!organizations.isEmpty());
        Assert.assertEquals(2, organizations.size());

        Organization organization = organizationDao.findByName("ПАO \"Газпром\"");
        Assert.assertNotNull(organization);
        Assert.assertEquals(1, organization.getId().longValue());

        organization = organizationDao.findById(2);
        Assert.assertNotNull(organization);
        Assert.assertTrue(organization.getIsActive());

        /**
         * To save(add) new Organization
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

        /**
         * Check that the new Organization('Bell integrator') with auto incremented ID to value 3
         * was successfully added to H2 Data Base and now the size of all organization is equals 3
         */
        organizations = organizationDao.findAll();
        Assert.assertEquals(3, organizations.size());   //System.out.println(saveOrganization.toString());
        Assert.assertEquals(3, saveOrganization.getId().longValue());


    }
}
