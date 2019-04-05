package ru.homecompany.bank.view.organization;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrganizationView {

    @NotEmpty
    public Integer id;

    @Size(max = 25)
    @NotNull(message = "name can not be null")
    public String name;

    @Size(max = 25)
    @NotNull(message = "name can not be null")
    public String fullName;

    @Size(max = 3)
    @NotNull(message = "code can not be null")
    public String inn;

    @Size(max = 3)
    @NotNull(message = "code can not be null")
    public String kpp;

    @Size(max = 3)
    @NotNull(message = "code can not be null")
    public String address;

    @Size(max = 11)
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
