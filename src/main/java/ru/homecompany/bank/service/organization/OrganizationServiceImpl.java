package ru.homecompany.bank.service.organization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.dao.organization.OrganizationDao;
import ru.homecompany.bank.model.Organization;
import ru.homecompany.bank.view.organization.OrganizationFilter;
import ru.homecompany.bank.view.organization.OrganizationFilterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of service OrganizationService
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);
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

    @Transactional
    @Override
    public List<OrganizationFilterView> findByFilter(OrganizationFilter filter) {
        List<Organization> organizations = organizationDao.list(filter);
        if (!organizations.isEmpty()) {
            logger.info("## SERVICE LAYER ORG 1: " + organizations.get(0).toString());
        }
        List<OrganizationFilterView> list = new ArrayList<>();
        for (int i = 0; i < organizations.size(); i++) {
            OrganizationFilterView viewList = new OrganizationFilterView();
            viewList.setId(organizations.get(i).getId());
            viewList.setName(organizations.get(i).getName());
            viewList.setActive(organizations.get(i).getIsActive());
            list.add(i, viewList);
        }
        return list;
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void save(OrganizationView organizationView) {
//        return organizationView;
//    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    @Transactional
//    public void update(OrganizationView organizationView) {
//        System.out.println(organizationView.toString());
//        Integer id = organizationView.id;
//        Organization organization = organizationDao.findById(id);
//        mapperFacade.map(organizationView, organization);
//        System.out.println(organization.toString());
//        organizationDao.update(organization);
//    }
//
//    @Override
//    public void save(OrganizationView organizationView) {
//
//    }
}

