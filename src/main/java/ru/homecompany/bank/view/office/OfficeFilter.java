package ru.homecompany.bank.view.office;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Input View for working with office filter methods. Sends ROW DATA (JSON STRING) to server
 */
public class OfficeFilter {

    /**
     * Short name of office. May be null or empty. Max length of chars equals 50
     */
    @Size(max = 50)
    public String name;

    /**
     * Work telephone of office. May be null or empty. Max length of chars equals 12
     */
    @Pattern(regexp = "(\\+*)([\\d]+)")
    @Size(max = 12)
    public String phone;

    /**
     * Activity of office. This isn't a required parameter. Must be TRUE or FALSE
     */
    public Boolean isActive;
}
