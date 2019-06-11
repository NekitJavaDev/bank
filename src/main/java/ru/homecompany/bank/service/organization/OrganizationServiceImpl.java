package ru.homecompany.bank.service.organization;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.dao.organization.OrganizationDao;
import ru.homecompany.bank.model.Organization;
import ru.homecompany.bank.view.organization.OrganizationFilter;
import ru.homecompany.bank.view.organization.OrganizationFilterView;
import ru.homecompany.bank.view.organization.OrganizationView;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of service OrganizationService
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;

    /**
     * Dependency injection of OrganizationDao implementation through public constructor
     *
     * @param organizationDao organization from DAO layer
     */
    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<OrganizationFilterView> findByFilter(OrganizationFilter filter) {
        if (filter == null || filter.name == null) {
            throw new ServiceException("Empty JSON row or missed required parameter: 'name'");
        }
        List<Organization> organizations = organizationDao.filterList(filter);
        List<OrganizationFilterView> list = new ArrayList<>(10);
        for (int i = 0; i < organizations.size(); i++) {
            OrganizationFilterView viewList = new OrganizationFilterView();
            viewList.setId(organizations.get(i).getId());
            viewList.setName(organizations.get(i).getName());
            viewList.setActive(organizations.get(i).getIsActive());
            list.add(i, viewList);
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Organization findById(Integer id) {
        Organization organization = organizationDao.findById(id);
        if (organization == null) {
            throw new ServiceException("Organization with ID: " + id + " doesn't exist");
        } else {
            return organization;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(OrganizationView view) {
        if (view.id == null || view.id.toString().isEmpty()) {
            throw new ServiceException("Cannot update organization with empty or null ID");
        }
        Organization organization = findById(view.id);
        organization.setName(view.name);
        organization.setFullName(view.fullName);
        organization.setInn(view.inn);
        organization.setKpp(view.kpp);
        organization.setAddress(view.address);
        if (view.phone != null) {
            organization.setPhone(view.phone);
        }
        if (view.isActive != null) {
            organization.setIsActive(view.isActive);
        }
        organizationDao.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(OrganizationView view) {
        Organization organization = new Organization(view.name, view.fullName, view.inn, view.kpp, view.address, view.phone, view.isActive);
        organizationDao.save(organization);
    }
}

