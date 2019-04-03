package ru.homecompany.bank.dao.document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.homecompany.bank.model.Document;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of repository DocumentDao
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {

    private final Logger logger = LoggerFactory.getLogger(DocumentDaoImpl.class);

    private EntityManager em;

    /**
     * Dependency injection of JPA EntityManager implementation through public constructor
     *
     * @param em
     */
    @Autowired
    public DocumentDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Document> findAll() {
        logger.info("## Get all documents : ");
        CriteriaQuery<Document> criteriaQuery = buildCriteriaAll();
        TypedQuery<Document> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }


    private CriteriaQuery<Document> buildCriteriaAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Document> criteriaQuery = criteriaBuilder.createQuery(Document.class);
        Root<Document> documentRoot = criteriaQuery.from(Document.class);
        criteriaQuery.select(documentRoot);
        return criteriaQuery;
    }
}
