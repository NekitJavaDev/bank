package ru.homecompany.bank.view.organization;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * View for working with UPDATE and SAVE methods of organizations. Returns ROW DATA (JSON STRING) to user
 */
public class OrganizationView {

    public Integer id;

    @NotNull(message = "Имя организации должно быть задано")
    @NotEmpty(message = "Имя организации не может быть пустым")
    @Size(min = 4, max = 25, message = "Имя организации должно быть длиннее 3 символов и max == 25")
    public String name;

    @NotNull(message = "Полное имя организации должно быть задано")
    @NotEmpty(message = "Полное имя организации не может быть пустым")
    @Size(min = 11, max = 50, message = "Полное имя организации должно быть длиннее 10 символов и max == 50")
    public String fullName;

    @NotNull(message = "ИНН организации должно быть задано")
    @NotEmpty(message = "ИНН организации не может быть пустым")
    @Size(min = 12, max = 12, message = "Длинна ИНН организации должна == 12 символов")
    public String inn;

    @NotNull(message = "КПП организации должно быть задано")
    @NotEmpty(message = "КПП организации не может быть пустым")
    @Size(min = 9, max = 9, message = "Длинна КПП организации должна == 9")
    public String kpp;

    @NotNull
    @NotEmpty
    @Size(min = 11, max = 50, message = "Длинна строки адреса организации должна быть длинне 10 символов и max == 50 символов")
    public String address;

    @Pattern(regexp = ("(8|7|\\+7)[\\d]{10}"))
    public String phone;

    public Boolean isActive;

    @Override
    public String toString() {
        return "OrganizationView{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", fullName:'" + fullName + '\'' +
                ", inn:'" + inn + '\'' +
                ", kpp:'" + kpp + '\'' +
                ", address:'" + address + '\'' +
                ", phone:'" + phone + '\'' +
                ", isActive:" + isActive +
                '}';
    }
}
