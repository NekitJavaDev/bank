package ru.homecompany.bank.dao.organization;

import ru.homecompany.bank.model.Organization;

import java.util.List;
import java.util.Set;

/**
 * DAO for working with organizations
 */
public interface OrganizationDao {

    /**
     * Get all organizations
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

    // TODO: 27.03.2019
//    List<Organization> list(OrganizationFilterView filter);

    /**
     * Get organization by name
     *
     * @param name Name
     * @return {@Organization}
     */
    Organization findByName(String name);

    void update(Organization organization);

    void save(Organization organization);


}