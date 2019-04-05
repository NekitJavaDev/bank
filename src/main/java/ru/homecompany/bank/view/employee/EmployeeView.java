package ru.homecompany.bank.view.employee;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

public class EmployeeView {

    @NotNull(message = "unique identifier can not be null")
    public Integer id;

    @NotNull
    public Integer officeId;

    public Integer countryId;

    public Integer documentId;

    @Size(max = 20)
    @NotEmpty(message = "first name of employee can not be null")
    public String firstName;

    @Size(max = 50)
    public String secondName;

    @Size(max = 25)
    public String middleName;

    @Size(max = 50)
    @NotEmpty(message = "position can not be null")
    public String position;

    @Size(max = 11)
    public String phone;

    public Boolean isIdentified;

    @Size(max = 50)
    public String docName;

    @Size(max = 20)
    public String docNumber;

    public Date docDate;

    @Size(max = 50)
    public String citizenshipName;

    @Size(max = 3)
    public String citizenshipCode;

    @Override
    public String toString() {
        return "{\n" +
                " \"id\":" + "\"" + id + "\",\n" +
                " \"firstName\":" + "\"" + firstName + "\",\n" +
                " \"secondName\":" + "\"" + secondName + "\",\n" +
                " \"middleName\":" + "\"" + middleName + "\"\n" +
                " \"position\":" + "\"" + position + "\"\n" +
                " \"phone\":" + "\"" + phone + "\"\n" +
                " \"docName\":" + "\"" + docName + "\"\n" +
                " \"docNumber\":" + "\"" + docNumber + "\"\n" +
                " \"docDate\":" + "\"" + docDate + "\"\n" +
                " \"citizenshipName\":" + "\"" + citizenshipName + "\"\n" +
                " \"citizenshipCode\":" + "\"" + citizenshipCode + "\"\n" +
                " \"isIdentified\":" + "\"" + isIdentified + "\"\n" +
                "}";
    }
}
