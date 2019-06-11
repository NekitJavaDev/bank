package ru.homecompany.bank.view.organization;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Input View for working with organization filter methods. Sends ROW DATA (JSON STRING) to server.
 * You can enter a couple of letters instead of the full name, unless "isActive"!
 * Matches are searched everywhere (begin/middle/end) in a word. Case sensitive (register) search!
 */
public class OrganizationFilter {

    /**
     * Short name of organization. This is a required parameter.
     * But value may be empty (this way to find all organizations)!
     */
    @NotNull
    @Size(max = 25)
    public String name;

    /**
     * Taxpayer identification number of organization.
     */
    @Pattern(regexp = "[0-9]*")
    public String inn;

    /**
     * Activity of organization. This isn't a required parameter. Must be TRUE or FALSE
     */
    public Boolean isActive;
}
