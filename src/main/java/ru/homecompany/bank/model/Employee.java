package ru.homecompany.bank.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    /**
     * Service field for Hibernate
     */
    @Version
    private Integer version;

    /**
     * Unique ID of employee
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    /**
     * Name of employee
     */
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    /**
     * Lst name of employee
     */
    @Column(name = "second_name", length = 50, nullable = false)
    private String secondName;

    /**
     * Middle name of employee
     */
    @Column(name = "middle_name", length = 25, nullable = true)
    private String middleName;

    /**
     * Position at the work
     */
    @Column(name = "position", length = 50, nullable = false)
    private String position;

    /**
     * Cellular telephone of employee
     */
    @Column(name = "phone", length = 11, unique = true, nullable = true)
    private String phone;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doc_code")
    private Document document;

    @Column(name = "doc_number", length = 45, unique = true, nullable = false)
    private String docNumber;

    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citizenship_code", nullable = false)
    private Country country;

    /**
     * Identify of employee
     */
    @Column(name = "is_identified", nullable = true)
    private Boolean isIdentified;

    /**
     * Constructor for Hibernate
     */
    public Employee() {

    }

    public Employee(String firstName, String secondName, String middleName, String position, String phone, Document document, String docNumber, Date docDate, Country country, Boolean isIdentified) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.document = document;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.country = country;
        this.isIdentified = isIdentified;
    }

    public Employee(Integer id, String firstName, String secondName, String middleName, String position, String phone, Document document, String docNumber, Date docDate, Country country, Boolean isIdentified) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.document = document;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.country = country;
        this.isIdentified = isIdentified;
    }

    /**
     * Return unique ID of employee
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    /**
     * Return name of employee
     *
     * @return name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set name of employee
     *
     * @param firstName name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Return last name of employee
     *
     * @return lastName
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Set last name of employee
     *
     * @param secondName last name
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * Return middle name of employee
     *
     * @return middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Set middle name of employee
     *
     * @param middleName middle name of employee
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Return position of employee
     *
     * @return position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Set position of employee
     *
     * @param position position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Return phone of employee
     *
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set phone of employee
     *
     * @param phone phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Return identify of employee: true or false
     *
     * @return identify of employee
     */
    public Boolean getIsIdentified() {
        return isIdentified;
    }

    /**
     * Set identify of employee
     *
     * @param isIdentified identify
     */
    public void setIsIdentified(Boolean isIdentified) {
        this.isIdentified = isIdentified;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", docName='" + document.getName() + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", docDate='" + docDate + '\'' +
                ", citizenshipName='" + country.getName() + '\'' +
                ", citizenshipCode='" + country.getCode() + '\'' +
                ", isIdentified='" + isIdentified + '\'' +
                '}';
    }
}
