package ru.homecompany.bank.view.office;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * View for working with UPDATE and SAVE methods of office. Returns ROW DATA (JSON STRING) to user
 */
public class OfficeView {

    public Integer id;

    public Integer orgId;

    @NotNull(message = "Имя офиса должно быть задано")
    @NotEmpty(message = "Имя офиса не может быть пустым")
    @Size(min = 4, max = 50, message = "Имя офиса должно быть длиннее 3 символов и max == 50")
    public String name;

    @NotNull
    @NotEmpty
    @Size(min = 11, max = 50, message = "Адрес офиса должен быть длиннее 3 символов и max == 50")
    public String address;

    @Pattern(regexp = "(7|\\+7|8)([0-9]{10})")
    public String phone;

    public Boolean isActive;

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
