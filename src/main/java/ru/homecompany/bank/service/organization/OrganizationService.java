package ru.homecompany.bank.service.organization;


import ru.homecompany.bank.model.Organization;
import ru.homecompany.bank.view.organization.OrganizationFilter;
import ru.homecompany.bank.view.organization.OrganizationFilterView;
import ru.homecompany.bank.view.organization.OrganizationView;

import java.util.List;

/**
 * Service
 */
public interface OrganizationService {

    /**
     * Get list of organizations
     *
     * @return {@Organizations}
     */
    List<Organization> findAll();

    /**
     * Get organization by ID
     *
     * @param id Identifier
     * @return {@Organization}
     */
    Organization findById(Integer id);

    List<OrganizationFilterView> findByFilter(OrganizationFilter filter);

//    void update(OrganizationView organizationView);
//
//    void save(OrganizationView organizationView);
}
