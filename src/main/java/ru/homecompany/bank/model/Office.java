package ru.homecompany.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Office
 */
@Entity
@Table(name = "office")
public class Office {

    /**
     * Service field for Hibernate
     */
    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "phone", length = 11, unique = true, nullable = false)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @JsonIgnore
    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    private List<Employee> employees;

    /**
     * Public/Protected constructor for Hibernate
     */
    public Office() {

    }

    /**
     * Constructor of class Office with 4 required parameters
     *
     * @param name     Name
     * @param address  Address
     * @param phone    Work Telephone
     * @param isActive Activity
     */
    public Office(String name, String address, String phone, Boolean isActive) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    /**
     * Constructor of class Office with 5 required parameters
     *
     * @param id       identifier
     * @param name     Name
     * @param address  Address
     * @param phone    Work Telephone
     * @param isActive Activity
     */
    public Office(Integer id, String name, String address, String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Office(String name) {
        this.name = name;
    }

    public Office(Integer id) {
        this.id = id;
    }

    /**
     * Returns unique ID of office
     *
     * @return id of office
     */
    public Integer getId() {
        return id;
    }

    //    /**
//     * Returns ID of organization
//     *
//     * @return id of organization
//     */
    public Organization getOrganization() {
        return organization;
    }

    //    /**
//     * Sets ID of the office
//     *
//     * @param orgId id of the office
//     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * Return unique name of the office
     *
     * @return unique name
     */
    public String getName() {
        return name;
    }

    /**
     * Set unique name of the office
     * Max length of name equals 50
     *
     * @param name unique name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return address of the office
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set address of the office
     * Max length of address equals 50
     *
     * @param address unique address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Return phone of the office
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set phone of the office
     * Max length of phone equals 11
     *
     * @param phone unique phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Return dynamical of the office: true or false
     *
     * @return dynamical
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Set isActive(dynamical) of the office
     *
     * @param isActive
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + "\'" +
                ", org_id='" + getOrganization().getId() + "\'" +
                ", name='" + name + "\'" +
                ", address='" + address + "\'" +
                ", phone='" + phone + "\'" +
                ", isActive='" + isActive + "\'" +
                '}';
    }
}
