package ru.homecompany.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    /**
     * Service field for Hibernate
     */
    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Column(name = "second_name", length = 50)
    private String secondName;

    @Column(name = "middle_name", length = 25)
    private String middleName;

    @Column(name = "position", length = 40, nullable = false)
    private String position;

    @Column(name = "phone", length = 12, unique = true)
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doc_code")
    private Document document;

    @Column(name = "doc_number", length = 45, unique = true)
    private String docNumber;

    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "citizenship_code")
    private Country country;

    /**
     * Status of identification.
     * If the employee’s identity is successfully established -> status is "success" (true)
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    /**
     * Constructor for Hibernate
     */
    public Employee() {

    }

    /**
     * Constructor to save(add) new Employee
     *
     * @param firstName    name
     * @param secondName   surname
     * @param middleName   patronymic
     * @param position     position(function) at work
     * @param phone        cell phone number
     * @param docNumber    № and serial of Document Identity
     * @param docDate      date of issue
     * @param isIdentified status of identification(true/false)
     */
    public Employee(String firstName, String secondName, String middleName, String position, String phone, String docNumber, Date docDate, Boolean isIdentified) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.docNumber = docNumber;
        this.docDate = docDate;
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
                " id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", docName='" + document.getName() + '\'' +
                ", docCode='" + document.getName() + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", docDate='" + docDate + '\'' +
                ", citizenshipName='" + country.getName() + '\'' +
                ", citizenshipCode='" + country.getCode() + '\'' +
                ", isIdentified='" + isIdentified + '\'' +
                '}';
    }

    public String toStringWhenSave() {
        StringBuffer sb = new StringBuffer();
        sb.append("{ id=").append(getId()).append(",");
        sb.append(" firstName=").append(getFirstName()).append(",");
        sb.append(" secondName=").append(getSecondName()).append(",");
        sb.append(" middleName=").append(getMiddleName()).append(",");
        sb.append(" position=").append(getPosition()).append(",");
        sb.append(" phone=").append(getPhone()).append(",");
        if (document != null) {
            sb.append(" docName=").append(getDocument().getName()).append(",");
            sb.append(" docCode=").append(getDocument().getCode()).append(",");
        } else {
            sb.append(" docName=").append("null").append(",");
            sb.append(" docCode=").append("null").append(",");
        }
        sb.append(" docNumber=").append(getDocNumber()).append(",");
        sb.append(" docDate=").append(getDocDate()).append(",");
        if (country != null) {
            sb.append(" citizenshipCode=").append(getCountry().getCode()).append(",");
        } else {
            sb.append(" citizenshipCode=").append("null").append(",");
        }
        sb.append(" isIdentified=").append(getIsIdentified()).append("}");
        return String.valueOf(sb);
    }
}
