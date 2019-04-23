package ru.homecompany.bank.view.organization;

/**
 * View for working with UPDATE and SAVE methods of organizations. Returns ROW DATA (JSON STRING) to user
 */
public class OrganizationView {

    public Integer id;

    public String name;

    public String fullName;

    public String inn;

    public String kpp;

    public String address;

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
