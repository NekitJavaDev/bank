package ru.homecompany.bank.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Organization
 */
@Entity
@Table(name = "organization")
public class Organization {

    /**
     * Service field for Hibernate
     */
    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 25, unique = true, nullable = false)
    private String name;

    @Column(name = "full_name", unique = true, length = 50, nullable = false)
    private String fullName;

    @Column(name = "inn", length = 12, unique = true, nullable = false)
    private String inn;

    @Column(name = "kpp", length = 9, unique = true, nullable = false)
    private String kpp;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "phone", length = 12, unique = true, nullable = false)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Office> offices;

    /**
     * Empty constructor for Hibernate
     */
    public Organization() {

    }

    /**
     * Constructor of class Organization with 5 required parameters
     *
     * @param nameOrg     Name
     * @param fullNameOrg Full name
     * @param inn         Taxpayer identification number
     * @param kpp         Reason Code
     * @param address     Address
     */
    public Organization(String nameOrg, String fullNameOrg, String inn, String kpp, String address) {
        this.name = nameOrg;
        this.fullName = fullNameOrg;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
    }

    /**
     * Constructor of class Organization with 6 required parameters
     *
     * @param id          Identifier
     * @param nameOrg     Name of organization
     * @param fullNameOrg Full name
     * @param inn         Taxpayer identification number
     * @param kpp         Reason Code
     * @param address     Address
     */
    public Organization(Integer id, String nameOrg, String fullNameOrg, String inn, String kpp, String address) {
        this.id = id;
        this.name = nameOrg;
        this.fullName = fullNameOrg;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
    }

//    public Organization(Integer id) {
//        this.id = id;
//    }

//    public Organization(String name) {
//        this.name = name;
//    }

//    public OrganizationView convertOrgToView() {
//        OrganizationView view = new OrganizationView();
//
//        view.id = id;
//        view.name = name;
//        view.fullname = fullName;
//        view.inn = inn;
//        view.kpp = kpp;
//        view.address = address;
//        view.phone = phone;
//        view.isActive = isActive;
//
//        return view;
//    }


    /**
     * Return unique ID of organization
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }


    /**
     * Returns name of organization
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of organization
     *
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return full name of organization
     *
     * @return full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Set full name of organization
     *
     * @param fullName Full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Return inn of organization
     *
     * @return inn
     */
    public String getInn() {
        return inn;
    }

    /**
     * Set taxpayer identification number of organization
     *
     * @param inn Taxpayer identification number
     */
    public void setInn(String inn) {
        this.inn = inn;
    }

    /**
     * Return Reason Code in Federal Migration Service(may be not unique)
     *
     * @return kpp
     */
    public String getKpp() {
        return kpp;
    }

    /**
     * Set kpp
     *
     * @param kpp Reason Code
     */
    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    /**
     * Return address of organization
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set address of organization
     *
     * @param address Address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Return phone of organization
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set phone of organization
     *
     * @param phone Work telephone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Return organization is active? true or false
     *
     * @return isActive
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Set organization is active?
     *
     * @param isActive Activity
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Return all offices of this organization
     *
     * @return offices
     */
    public List<Office> getOffices() {
        return offices;
    }

    /**
     * Set offices of this organization
     *
     * @param offices Offices
     */
    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    @Override
    public String toString() {
        return " {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                "}";
    }
}

