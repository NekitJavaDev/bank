package ru.homecompany.bank.view.organization;

import javax.validation.constraints.NotNull;

/**
 * Input View for working with organization filter methods. Sends ROW DATA (JSON STRING) to server
 */
public class OrganizationFilter {

    /**
     * Short name of organization. This is a required parameter
     */
    @NotNull
    public String name;

    /**
     * Taxpayer identification number of organization. This isn't a required parameter
     */
    public String inn;

    /**
     * Activity of organization. This isn't a required parameter. Must be TRUE or FALSE
     */
    public Boolean isActive;
}
