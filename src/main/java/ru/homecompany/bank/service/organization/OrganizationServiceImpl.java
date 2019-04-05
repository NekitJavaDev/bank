package ru.homecompany.bank.service.organization;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.dao.organization.OrganizationDao;
import ru.homecompany.bank.model.Organization;

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
     * @param organizationDao
     */
    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    @Transactional(readOnly = true)
    public List<Organization> findAll() {
        return organizationDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Organization findById(Integer id) {
        return organizationDao.findById(id);
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
