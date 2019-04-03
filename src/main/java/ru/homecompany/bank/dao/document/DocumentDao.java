package ru.homecompany.bank.dao.document;

import ru.homecompany.bank.model.Document;

import java.util.List;
import java.util.Set;

/**
 * DAO for working with documents
 */
public interface DocumentDao {

    /**
     * Get all documents
     *
     * @return {@Documents}
     */
    List<Document> findAll();
}
