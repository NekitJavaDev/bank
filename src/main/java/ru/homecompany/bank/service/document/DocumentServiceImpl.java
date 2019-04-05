package ru.homecompany.bank.service.document;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.dao.document.DocumentDao;
import ru.homecompany.bank.model.Document;

import java.util.List;

/**
 * Implementation of service DocumentService
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    private DocumentDao documentDao;

    /**
     * Dependency injection of DocumentDao implementation through public constructor
     *
     * @param documentDao
     */
    @Autowired
    public DocumentServiceImpl(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Document> findAll() {
        return documentDao.findAll();
    }
}
