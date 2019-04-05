package ru.homecompany.bank.service.document;


import ru.homecompany.bank.model.Document;

import java.util.List;

/**
 * Service
 */
public interface DocumentService {

    /**
     * Get list of documents
     *
     * @return {@Documents}
     */
    List<Document> findAll();
}
