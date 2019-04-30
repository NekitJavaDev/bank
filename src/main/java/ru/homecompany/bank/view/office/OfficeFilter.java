package ru.homecompany.bank.view.office;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OfficeFilter {

    @Size(max = 50)
    public String name;

    @Pattern(regexp = "(\\+*)([\\d]+)")
    @Size(max = 12)
    public String phone;

    public Boolean isActive;
}
