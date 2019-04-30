package ru.homecompany.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "phone", length = 12, unique = true)
    private String phone;

    @Column(name = "is_active")
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
     * Return unique ID of office
     *
     * @return id Identifier
     */
    public Integer getId() {
        return id;
    }

    /**
     * Return ID of organization
     *
     * @return organization Organization
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Set ORG_ID of the office
     *
     * @param organization ID of Organization
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * Return unique name of the office
     *
     * @return name Unique Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set unique name of the office. Max length equals 50
     *
     * @param name Unique Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return address of the office
     *
     * @return address Unique Address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set address of the office. Max length equals 50
     *
     * @param address Unique Address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Return phone of the office
     *
     * @return phone Unique number of Work Telephone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set phone of the office. Max length equals 11
     *
     * @param phone Unique Work Telephone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Return activity status of the office: true or false
     *
     * @return isActive Activity Status
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Set activity status of the office
     *
     * @param isActive Activity Status
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + "\'" +
                ", org_id='" + organization.getId() + "\'" +
                ", name='" + name + "\'" +
                ", address='" + address + "\'" +
                ", phone='" + phone + "\'" +
                ", isActive='" + isActive + "\'" +
                '}';
    }
}
