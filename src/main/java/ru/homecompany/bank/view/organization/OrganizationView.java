package ru.homecompany.bank.view.organization;

public class OrganizationView {

    //    @NotEmpty
    public Integer id;

    //    @Size(max = 25)
//    @NotNull(message = "name can not be null")
    public String name;

    //    @Size(max = 50)
//    @NotNull(message = "name can not be null")
    public String fullName;

    //    @Size(max = 12)
//    @NotNull(message = "code can not be null")
    public String inn;

    //    @Size(max = 9)
//    @NotNull(message = "code can not be null")
    public String kpp;

    //    @Size(max = 50)
//    @NotNull(message = "code can not be null")
    public String address;

    //    @Size(max = 11)
    public String phone;

    public Boolean isActive;


    public OrganizationView(Integer id, String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public OrganizationView(String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

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
