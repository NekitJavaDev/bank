//package ru.homecompany.bank.model;
//
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "employee")
//public class Employee {
//    /**
//     * Unique ID of employee
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Integer id;
//
//    /**
//     * Service field for Hibernate
//     */
//    @Version
//    private Integer version;
//
//    /**
//     * Name of employee
//     */
//    @Column(name = "first_name", length = 20, nullable = false)
//    private String firstName;
//
//    /**
//     * Lst name of employee
//     */
//    @Column(name = "second_name", length = 50, nullable = true)
//    private String secondName;
//
//    /**
//     * Middle name of employee
//     */
//    @Column(name = "middle_name", length = 25, nullable = true)
//    private String middleName;
//
//    /**
//     * Position at the work
//     */
//    @Column(name = "position", length = 50, nullable = false)
//    private String position;
//
//    /**
//     * Cellular telephone of employee
//     */
//    @Column(name = "phone", length = 11, unique = true, nullable = true)
//    private String phone;
//
//    /**
//     * Identify of employee
//     */
//    @Column(name = "is_identified", nullable = true)
//    private Boolean isIdentified;
//
////    @OneToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "doc_id")
////    private Document documentId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "country_id")
//    private Country info;
//
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "office_id")
////    private Office office;
//
//    /**
//     * Constructor for Hibernate
//     */
//    public Employee() {
//
//    }
//
//    /**
//     * Constructor of class Employee with 2 required parameters
//     *
//     * @param firstName name of employee
//     * @param position  name of position at work
//     */
//    public Employee(String firstName, String position) {
//        this.firstName = firstName;
//        this.position = position;
//    }
//
//    /**
//     * Returns unique ID of employee
//     *
//     * @return id of employee
//     */
//    public Integer getId() {
//        return id;
//    }
//
//    /**
//     * Returns id of the office
//     *
//     * @return id of the office
//     */
////    public Integer getOfficeId() {
////        return this.office.getId();
////    }
//
//    /**
//     * Sets ID of the office
//     *
//     * @param officeId id of the office
//     */
////    public void setOfficeId(Integer officeId) {
////        this.office.setId(officeId);
////    }
//
//    /**
//     * Returns ID of the info
//     *
//     * @return id of the info
//     */
////    public Integer getCountryId() {
////        return this.info.getId();
////    }
//
//    /**
//     * Sets ID of the info
//     *
//     * @param countryId id of the info
//     */
////    public void setCountryId(Integer countryId) {
////        this.info.setId(countryId);
////    }
//
//    /**
//     * Returns ID of document
//     *
//     * @return id of document
//     */
////    public Integer getDocId() {
////        return documentId.getId();
////    }
//
//    /**
//     * Sets ID of the contract
//     *
//     * @param docId id of the contract
//     */
////    public void setDocumentId(Integer docId) {
////        this.documentId.setId(docId);
////    }
//
//    /**
//     * Returns name of employee
//     *
//     * @return name of employee
//     */
//    public String getFirstName() {
//        return firstName;
//    }
//
//    /**
//     * Sets name of employee
//     *
//     * @param firstName name of employee
//     */
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    /**
//     * Returns last name of employee
//     *
//     * @return last name of employee
//     */
//    public String getSecondName() {
//        return secondName;
//    }
//
//    /**
//     * Sets last name of employee
//     *
//     * @param secondName last name of employee
//     */
//    public void setSecondName(String secondName) {
//        this.secondName = secondName;
//    }
//
//    /**
//     * Returns middle name of employee
//     *
//     * @return middle name of employee
//     */
//    public String getMiddleName() {
//        return middleName;
//    }
//
//    /**
//     * Sets middle name of employee
//     *
//     * @param middleName middle name of employee
//     */
//    public void setMiddleName(String middleName) {
//        this.middleName = middleName;
//    }
//
//    /**
//     * Returns position of employee
//     *
//     * @return position of employee
//     */
//    public String getPosition() {
//        return position;
//    }
//
//    /**
//     * Sets position of employee at work
//     *
//     * @param position position of employee
//     */
//    public void setPosition(String position) {
//        this.position = position;
//    }
//
//    /**
//     * Returns phone of employee
//     *
//     * @return phone of employee
//     */
//    public String getPhone() {
//        return phone;
//    }
//
//    /**
//     * Sets phone of employee
//     *
//     * @param phone phone of employee
//     */
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    /**
//     * Returns identify of employee: true or false
//     *
//     * @return identify of employee
//     */
//    public Boolean getIsIdentified() {
//        return isIdentified;
//    }
//
//    /**
//     * Sets identify of employee
//     *
//     * @param isIdentified identify of employee
//     */
//    public void setIsIdentified(Boolean isIdentified) {
//        this.isIdentified = isIdentified;
//    }
//
//    @Override
//    public String toString() {
//        return "\"data\"{" +
//                "\n \"id=" + id +
//                ",\n \"firstName\":\"" + firstName + "\"" +
//                ",\n \"secondName\":\"" + secondName + "\"" +
//                ",\n \"middleName\":\"" + middleName + "\"" +
//                ",\n \"position\":\"" + position + "\"" +
//                ",\n \"phone\":\"" + phone + '\'' + "\"" +
//                ",\n \"isIdentified\":\"" + isIdentified + "\"" +
//                "\n}";
//    }
//}
