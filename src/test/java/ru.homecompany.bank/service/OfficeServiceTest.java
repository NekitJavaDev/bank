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
import ru.homecompany.bank.model.Office;
import ru.homecompany.bank.service.office.OfficeService;
import ru.homecompany.bank.view.office.OfficeFilter;
import ru.homecompany.bank.view.office.OfficeFilterView;
import ru.homecompany.bank.view.office.OfficeView;

import java.util.List;

/**
 * Test of all Office methods from SERVICE layer of application
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration("src/main/resources")
@Transactional
public class OfficeServiceTest {

    @Autowired
    OfficeService officeService;

    /**
     * Test of Office search by filter (different filter values)
     */
    @Test
    public void testGetListByFilter() {

        /*
         * Try to find nonexistent office with empty required parameter: "orgId"
         */
        try {
            List<OfficeFilterView> listByNonexistentOrgId = officeService.findByFilter(null, null);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

        /*
         * Find all offices of the first Organization (test with 1 required parameter - "orgId")
         */
        List<OfficeFilterView> listByOrgId = officeService.findByFilter(1, null);
        Assert.assertNotNull(listByOrgId);
        Assert.assertEquals(2, listByOrgId.size());
        for (OfficeFilterView view : listByOrgId) {
            System.out.println(view.toString());
        }

        /*
         * Find all offices of the second Organization and by filter's "isActive"
         */
        OfficeFilter filter = new OfficeFilter();
        filter.isActive = true;
        filter.name = "с";    //Required presence of Russian letter "с" in the names of offices
        List<OfficeFilterView> listByOrgIdAndIsActive = officeService.findByFilter(2, filter);
        Assert.assertNotNull(listByOrgIdAndIsActive);
        Assert.assertEquals(2, listByOrgIdAndIsActive.size());
        for (OfficeFilterView view : listByOrgIdAndIsActive) {
            System.out.println(view.toString());
        }
    }

    /**
     * Test of Office search by ID
     */
    @Test
    public void testFindById() {

        /*
         * Try to find nonexistent office with ID = 10
         */
        try {
            Office officeEmpty = officeService.findById(10);    //doesn't exist Office with ID = 10
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

        /*
         * Find office with ID = 3
         */
        Office office = officeService.findById(3);
        Assert.assertNotNull(office);
        Assert.assertEquals(2, office.getOrganization().getId().intValue());
        System.out.println(office.toString());
    }

    /**
     * Test of add new Office and then update(change) some values
     */
    @Test
    public void testSaveAndUpdate() {

        /*
         * Try to update office without several required parameters (such as "name" or "address")
         */
        OfficeView viewNewTryUpdate = new OfficeView();
        viewNewTryUpdate.id = 1;
        viewNewTryUpdate.address = "ул. Верхоянская, 22, 1 подъезд";
        viewNewTryUpdate.isActive = true;
        try {
            officeService.update(viewNewTryUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /*
         * Try to add new office without several required parameters (such as "orgId","name" or "address")
         */
        OfficeView viewNewTryAdd = new OfficeView();
        viewNewTryAdd.name = "Малый Офис 'Газпром №12'";
        viewNewTryAdd.address = "ул. Верхоянская, 22, 1 подъезд";
        viewNewTryAdd.isActive = true;
        try {
            officeService.save(viewNewTryAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /*
         * Add new Office with empty (not required) values (such as "phone" and "isActive"
         */
        OfficeView viewNew = new OfficeView();
        viewNew.orgId = 1;
        viewNew.name = "Office 333/4445";
        viewNew.address = "ул. Лубянка, 33, 2 подъезд";
        officeService.save(viewNew);

        /*
         * Find new added office by ID
         */
        Office newOffice = officeService.findById(5);
        Assert.assertNotNull(newOffice);
        Assert.assertNull(newOffice.getIsActive());
        Assert.assertEquals("Office 333/4445", newOffice.getName());
        System.out.println(newOffice.toString());

        /*
         * Update values of empty (not required) variables of added office. Change "name"
         */
        OfficeView viewUpdate = new OfficeView();
        viewUpdate.id = 5;
        viewUpdate.orgId = 2;
        viewUpdate.name = "Офис 444/7";
        viewUpdate.address = "ул. Лубянка, 33, 3 подъезд";
        viewUpdate.phone = "+79151111116";    //not required parameter
        viewUpdate.isActive = false;    //not required parameter
        officeService.update(viewUpdate);

        /*
         * Find updatable office by ID
         */
        Office officeUpdate = officeService.findById(5);
        Assert.assertNotNull(officeUpdate);
        Assert.assertNotEquals(1, officeUpdate.getOrganization().getId().intValue());    //№ organization of this office was changed
        Assert.assertEquals("Офис 444/7", officeUpdate.getName());    //"name" wasn't changed
        Assert.assertEquals("+79151111116", officeUpdate.getPhone());    //"phone" was changed!!!
        Assert.assertFalse(officeUpdate.getIsActive());
        System.out.println(officeUpdate.toString());
    }
}
