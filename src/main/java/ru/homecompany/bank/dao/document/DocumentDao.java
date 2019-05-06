package ru.homecompany.bank.dao.document;

import ru.homecompany.bank.model.Document;

import java.util.List;

/**
 * DAO for working with documents
 */
public interface DocumentDao {

    /**
     * Get all documents
     *
     * @return @Documents
     */
    List<Document> findAll();

    /**
     * Get document by code
     *
     * @param code Unique Code
     * @return @Document
     */
    Document findByCode(String code);

    /**
     * Get document by name
     *
     * @param name Unique Name
     * @return @Document
     */
    Document findByName(String name);
}
