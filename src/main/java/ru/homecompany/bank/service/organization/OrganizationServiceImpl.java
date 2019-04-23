package ru.homecompany.bank.service.organization;

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
        return organizationDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(OrganizationView view) {
        Organization organization = findById(view.id);
        organization.setName(view.name);
        organization.setFullName(view.fullName);
        organization.setInn(view.inn);
        organization.setKpp(view.kpp);
        organization.setAddress(view.address);
        organization.setPhone(view.phone);
        organization.setIsActive(view.isActive);
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

