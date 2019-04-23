package ru.homecompany.bank.dao.organization;

import ru.homecompany.bank.model.Organization;
import ru.homecompany.bank.view.organization.OrganizationFilter;

import java.util.List;

/**
 * DAO for working with organizations by interacting with DATA BASE
 */
public interface OrganizationDao {

    /**
     * Get organizations by Filter (can be input first chars of a word)
     *
     * @param filter NAME(not null), INN, KPP
     * @return {@Organizations}
     */
    List<Organization> filterList(OrganizationFilter filter);

    /**
     * Get organization by ID
     *
     * @param id Identifier
     * @return {@Organization}
     */
    Organization findById(Integer id);

    /**
     * Update organization by ID
     *
     * @param organization Organization
     */
    void update(Organization organization);

    /**
     * Save new organization and ID was AUTO INCREMENTED
     *
     * @param organization Organization
     */
    void save(Organization organization);

    /**
     * Get all organizations
     *
     * @return {@Organizations}
     */
    List<Organization> findAll();

    /**
     * Get organization by name
     *
     * @param name Name
     * @return {@Organization}
     */
    Organization findByName(String name);
}