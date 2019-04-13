package ru.homecompany.bank.dao.organization;

import ru.homecompany.bank.model.Organization;
import ru.homecompany.bank.view.organization.OrganizationFilter;

import java.util.List;

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

    /**
     * Get organizations by Filter (can be input first chars of a word)
     *
     * @param filter NAME(not null), INN, KPP
     * @return {@Organizations}
     */
    List<Organization> list(OrganizationFilter filter);

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