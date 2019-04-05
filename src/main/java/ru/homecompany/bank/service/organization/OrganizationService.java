package ru.homecompany.bank.service.organization;


import ru.homecompany.bank.model.Organization;

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

    void update(Integer id, String name, String fullName, String inn, String kpp,
                String address, String phone, Boolean isActive);

    void save(String name, String fullName, String inn, String kpp,
              String address, String phone, Boolean isActive);
}
