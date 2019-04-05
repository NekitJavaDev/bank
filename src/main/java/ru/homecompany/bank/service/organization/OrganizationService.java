package ru.homecompany.bank.service.organization;


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
    List<OrganizationView> findAll();

    /**
     * Get organization by ID
     *
     * @param id Identifier
     * @return {@Organization}
     */
    OrganizationView findById(Integer id);

    void update(Integer id, String name, String fullName, String inn, String kpp,
                String address, String phone, Boolean isActive);

    void save(String name, String fullName, String inn, String kpp,
              String address, String phone, Boolean isActive);
}
