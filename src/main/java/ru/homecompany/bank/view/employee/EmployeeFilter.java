package ru.homecompany.bank.view.employee;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Input View for working with organization filter methods. Sends ROW DATA (JSON STRING) to server
 */
public class EmployeeFilter {

    /**
     * Office ID of employee. This is a required parameter
     */
    @NotNull(message = "ID офиса должно быть задано")
    public Integer officeId;

    /**
     * First name of employee. May be null or empty. Max length of chars equals 20
     */
    @Size(max = 20, message = "Max длина имени работника == 20")
    @Pattern(regexp = "[a-zA-Zа-яА-Я]*")
    public String firstName;

    /**
     * Second name of employee. May be null or empty. Max length of chars equals 50
     */
    @Size(max = 50, message = "Max длина фамилии работника == 50")
    @Pattern(regexp = "[a-zA-Zа-яА-Я]*")
    public String lastName;

    /**
     * Middle name of employee. May be null or empty. Max length of chars equals 25
     */
    @Size(max = 25, message = "Max длина отчества работника == 25")
    @Pattern(regexp = "[a-zA-Zа-яА-Я]*")
    public String middleName;

    /**
     * Position of employee. May be null or empty. Max length of chars equals 40
     */
    @Size(max = 40, message = "Max длина должности работника == 40")
    @Pattern(regexp = "[a-zA-Zа-яА-Я]*")
    public String position;

    /**
     * Document code of employee. May be null or empty. Max length of code equals 5
     */
    @Size(max = 5, message = "Max длина кода документа, удостоверяющего личность == 5")
    @Pattern(regexp = "[\\d]*")
    public String docCode;

    /**
     * Citizenship code of employee. May be null or empty. Max length of code equals 5
     */
    @Size(max = 5, message = "Max длина кода гражданства == 5")
    @Pattern(regexp = "[\\d]*")
    public String citizenshipCode;
}
