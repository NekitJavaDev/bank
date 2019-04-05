package ru.homecompany.bank.service.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.dao.organization.OrganizationDao;
import ru.homecompany.bank.model.Organization;
import ru.homecompany.bank.service.mapper.MapperFacade;
import ru.homecompany.bank.view.organization.OrganizationView;

import java.util.List;

/**
 * Implementation of service OrganizationService
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    /**
     * Dependency injection of OrganizationDao implementation through public constructor
     *
     * @param organizationDao
     */
    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> findAll() {
        List<Organization> all = organizationDao.findAll();
        return mapperFacade.mapAsList(all, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrganizationView findById(Integer id) {
        Organization organization = organizationDao.findById(id);
        return mapperFacade.map(organization, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Integer id, String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {

    }
}
