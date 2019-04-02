package ru.homecompany.bank.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Office
 */
@Entity
@Table(name = "office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     * Service field for Hibernate
     */
    @Version
    private Integer version;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "phone", length = 11, unique = true, nullable = false)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

//    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Employee> employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    /**
     * Empty constructor for Hibernate
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
     * Returns name of the office
     *
     * @return name of the office
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the office
     *
     * @param name of the office
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns address of the office
     *
     * @return address of the office
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address of the office
     *
     * @param address of the office
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns phone of the office
     *
     * @return phone of the office
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone of the office
     *
     * @param phone of the office
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns dynamical of the office: true or false
     *
     * @return dynamical of the office
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Sets isActive(dynamical) of the office
     *
     * @param isActive of the office
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


    /**
     * Returns list of employees. Default capacity 10
     *
     * @return list of employees
     */
//    public Set<Employee> getEmployees() {
//        if (employees == null) {
//            employees = new HashSet<>();
//        }
//        return employees;
//    }
//
//    public void setEmployees(Set<Employee> employees) {
//        this.employees = employees;
//    }
    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                "org_id=" + getOrganization().getId() +
                ", name='" + name +
                ", address='" + address +
                ", phone='" + phone +
                ", isActive=" + isActive +
//                ", employees=" + employees +
                ", organization=" + organization.getName() +
                '}';
    }
}
