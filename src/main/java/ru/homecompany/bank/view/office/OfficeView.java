package ru.homecompany.bank.view.office;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OfficeView {

    @NotEmpty
    public Integer id;

    @Size(max = 50)
    @NotNull(message = "name can not be null")
    public String name;


    @Size(max = 50)
    @NotNull(message = "code can not be null")
    public String address;

    @Size(max = 11)
    public String phone;

    public Integer isActive;

    @NotNull(message = "code can not be null")
    public Integer orgId;

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
