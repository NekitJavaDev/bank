package ru.homecompany.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "document")
public class Document {

    /**
     * Service field for Hibernate
     */
    @Version
    private Integer version;

    @Id
    @Column(name = "code", unique = true, length = 5, nullable = false)
    private String code;

    @Column(name = "name", unique = true, length = 50, nullable = false)
    private String name;

    /**
     * Public/Protected constructor for Hibernate
     */
    public Document() {
    }

    /**
     * Constructor of the class Document with 2 required parameters
     *
     * @param code unique code
     * @param name name
     */
    public Document(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Return an unique code of the document
     *
     * @return unique code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set an unique code of the document
     * Max length of code equals 5
     *
     * @param code unique code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Return an unique name of the country
     *
     * @return unique name
     */
    public String getName() {
        return name;
    }

    /**
     * Set an unique name of the country
     * Max length of name equals 5
     *
     * @param name unique name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
