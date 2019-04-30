package ru.homecompany.bank.service.office;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.dao.office.OfficeDao;
import ru.homecompany.bank.dao.organization.OrganizationDao;
import ru.homecompany.bank.model.Office;
import ru.homecompany.bank.model.Organization;
import ru.homecompany.bank.view.office.OfficeFilter;
import ru.homecompany.bank.view.office.OfficeFilterView;
import ru.homecompany.bank.view.office.OfficeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of service OfficeService
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private OfficeDao officeDao;

    private OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<OfficeFilterView> findByFilter(Integer orgId, OfficeFilter filter) {
        List<Office> offices = officeDao.filterList(orgId, filter);
        List<OfficeFilterView> list = new ArrayList<>(6);
        for (int i = 0; i < offices.size(); i++) {
            OfficeFilterView viewList = new OfficeFilterView();
            viewList.setId(offices.get(i).getId());
            viewList.setName(offices.get(i).getName());
            viewList.setActive(offices.get(i).getIsActive());
            list.add(viewList);
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Office findById(Integer id) {
        return officeDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(OfficeView view) {
        if (view.id == null || view.id.toString().isEmpty()) {
            throw new ServiceException("Cannot update organization with empty or null ID");
        }
        Organization organization = organizationDao.findById(view.orgId);
        Office office = officeDao.findById(view.id);
        if (office != null) {
            office.setOrganization(organization);
            office.setName(view.name);
            office.setAddress(view.address);
            if (view.phone != null) {
                office.setPhone(view.phone);
            }
            if (view.isActive != null) {
                office.setIsActive(view.isActive);
            }
            officeDao.update(office);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(OfficeView view) {
        Office office = new Office(view.name, view.address, view.phone, view.isActive);
        Organization organization = organizationDao.findById(view.orgId);
        office.setOrganization(organization);
        officeDao.save(office);
    }
}
