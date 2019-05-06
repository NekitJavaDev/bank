package ru.homecompany.bank.view.employee;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class EmployeeView {

    public Integer officeId;

    public Integer id;

    @NotNull(message = "Имя работника должно быть задано")
    @NotEmpty(message = "Имя работника не может быть пустым")
    @Size(min = 3, max = 20, message = "Имя работника должно быть длиннее 2 символов и max == 20")
    public String firstName;

    @Size(max = 50, message = "Фамилия работника должно быть не длиннее 50 символов")
    public String secondName;

    @Size(max = 25, message = "Отчество работника должно быть не длиннее 25 символов")
    public String middleName;

    @NotNull(message = "Имя работника должно быть задано")
    @NotEmpty(message = "Имя работника не может быть пустым")
    @Size(min = 5, max = 40, message = "Имя работника должно быть длиннее 4 символов и max == 40")
    public String position;

    @Pattern(regexp = "(7|\\+7|8)([0-9]{10})")
    public String phone;

    public String docCode;

    public String docName;

    public String docNumber;

    public Date docDate;

    public String citizenshipCode;

    public Boolean isIdentified;

    @Override
    public String toString() {
        return "{\n" +
                " \"officeId\":" + "\"" + officeId + "\",\n" +
                " \"id\":" + "\"" + id + "\",\n" +
                " \"firstName\":" + "\"" + firstName + "\",\n" +
                " \"secondName\":" + "\"" + secondName + "\",\n" +
                " \"middleName\":" + "\"" + middleName + "\"\n" +
                " \"position\":" + "\"" + position + "\"\n" +
                " \"phone\":" + "\"" + phone + "\"\n" +
                " \"docCode\":" + "\"" + docCode + "\"\n" +
                " \"docName\":" + "\"" + docName + "\"\n" +
                " \"docNumber\":" + "\"" + docNumber + "\"\n" +
                " \"docDate\":" + "\"" + docDate + "\"\n" +
                " \"citizenshipCode\":" + "\"" + citizenshipCode + "\"\n" +
                " \"isIdentified\":" + "\"" + isIdentified + "\"\n" +
                "}";
    }
}
