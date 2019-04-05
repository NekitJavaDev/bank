package ru.homecompany.bank.view.organization;

import javax.validation.constraints.NotNull;

public class OrganizationFilter {

    @NotNull
    public String name;

    public String inn;

    public Boolean isActive;
}
