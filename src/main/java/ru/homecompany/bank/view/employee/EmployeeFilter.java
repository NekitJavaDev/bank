package ru.homecompany.bank.view.employee;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EmployeeFilter {

    @NotNull
    @NotEmpty
    public Integer officeId;

    @Size(max = 20)
    @Pattern(regexp = "[\\s]*")
    public String firstName;

    @Size(max = 50)
    @Pattern(regexp = "[\\s]*")
    public String lastName;

    @Size(max = 25)
    @Pattern(regexp = "[\\s]*")
    public String middleName;

    @Size(max = 40)
    @Pattern(regexp = "[\\s]*")
    public String position;

    @Size(max = 5)
    @Pattern(regexp = "[\\d]*")
    public String docCode;

    @Size(max = 5)
    @Pattern(regexp = "[\\d]*")
    public String citizenshipCode;
}
