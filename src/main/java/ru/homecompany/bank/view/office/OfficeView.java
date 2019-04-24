package ru.homecompany.bank.view.office;

import ru.homecompany.bank.model.Organization;

public class OfficeView {

    public Integer id;

    public String name;

    public String address;

    public String phone;

    public Boolean isActive;

    public Organization orgId;

    @Override
    public String toString() {
        return "{\n" +
                " \"id\":" + "\"" + id + "\",\n" +
                " \"name\":" + "\"" + name + "\",\n" +
                " \"address\":" + "\"" + address + "\"\n" +
                " \"phone\":" + "\"" + phone + "\"\n" +
                " \"isActive\":" + "\"" + isActive + "\"\n" +
                " \"orgId\":" + "\"" + orgId + "\"\n" +
                "}";
    }
}
