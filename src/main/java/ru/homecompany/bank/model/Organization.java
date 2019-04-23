package ru.homecompany.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 25, unique = true, nullable = false)
    private String name;

    @Column(name = "full_name", length = 50, unique = true, nullable = false)
    private String fullName;

    @Column(name = "inn", length = 12, unique = true, nullable = false)
    private String inn;

    @Column(name = "kpp", length = 9, unique = true, nullable = false)
    private String kpp;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @JsonIgnore
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Office> offices;

    /**
     * Empty public/protected constructor for Hibernate
     */
    public Organization() {

    }

    /**
     * Constructor of class Organization with 5 required parameters
     *
     * @param name     Name
     * @param fullName Full name
     * @param inn      Taxpayer identification number
     * @param kpp      Reason Code
     * @param address  Address
     * @param phone    Telephone
     * @param isActive Activity (true or false)
     */
    public Organization(String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
    }

    /**
     * Return unique ID of organization
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }


    /**
     * Return name of organization
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of organization
     * Max length of name equals 25
     *
     * @param name unique name
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
     * Max length of full name equals 50
     *
     * @param fullName unique full name
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
     * Max length of INN equals 12
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
     * Set kpp of organization
     * Max length of KPP equals 9
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
     * Max length of address equals 50
     *
     * @param address address
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
     * Max length of phone equals 11
     *
     * @param phone work telephone
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
     * Set all offices of this organization
     *
     * @param offices Offices
     */
    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    @Override
    public String toString() {
        return " {" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive='" + isActive + '\'' +
                "}";
    }
}

