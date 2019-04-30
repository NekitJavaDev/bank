package ru.homecompany.bank.view.employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.homecompany.bank.model.Country;
import ru.homecompany.bank.model.Document;
import ru.homecompany.bank.model.Office;

import java.util.Date;

public class EmployeeView {

    public Office office;

    public Integer id;

    public String firstName;

    public String secondName;

    public String middleName;

    public String position;

    public String phone;

    //    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Document document;

    public String docNumber;

    public Date docDate;

    //    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Country country;

    public Boolean isIdentified;

    @Override
    public String toString() {
        return "{\n" +
                " \"officeId\":" + "\"" + office + "\",\n" +
                " \"id\":" + "\"" + id + "\",\n" +
                " \"firstName\":" + "\"" + firstName + "\",\n" +
                " \"secondName\":" + "\"" + secondName + "\",\n" +
                " \"middleName\":" + "\"" + middleName + "\"\n" +
                " \"position\":" + "\"" + position + "\"\n" +
                " \"phone\":" + "\"" + phone + "\"\n" +
                " \"docName\":" + "\"" + document + "\"\n" +
                " \"docNumber\":" + "\"" + docNumber + "\"\n" +
                " \"docDate\":" + "\"" + docDate + "\"\n" +
                " \"citizenshipCode\":" + "\"" + country + "\"\n" +
                " \"isIdentified\":" + "\"" + isIdentified + "\"\n" +
                "}";
    }
}
