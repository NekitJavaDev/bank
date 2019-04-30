package ru.homecompany.bank.service.organization;

import org.springframework.validation.annotation.Validated;
import ru.homecompany.bank.model.Organization;
import ru.homecompany.bank.view.organization.OrganizationFilter;
import ru.homecompany.bank.view.organization.OrganizationFilterView;
import ru.homecompany.bank.view.organization.OrganizationView;

import javax.validation.Valid;
import java.util.List;

/**
 * Service
 */
@Validated
public interface OrganizationService {

    /**
     * Get organizations by Filter (can be input first chars of a word)
     *
     * @param filter NAME(not null), INN, KPP
     * @return @Organizations
     */
    List<OrganizationFilterView> findByFilter(@Valid OrganizationFilter filter);

    /**
     * Get organization by ID
     *
     * @param id Identifier
     * @return @Organization
     */
    Organization findById(Integer id);

    /**
     * Update organization by ID
     *
     * @param view View of organization
     */
    void update(@Valid OrganizationView view);

    /**
     * Save new organization and ID was AUTO INCREMENTED
     *
     * @param view View of organization
     */
    void save(@Valid OrganizationView view);
}
