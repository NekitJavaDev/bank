package ru.homecompany.bank.dao.country;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.homecompany.bank.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Implementation of repository CountryDao
 */
@Repository
public class CountryDaoImpl implements CountryDao {

    private final Logger logger = LoggerFactory.getLogger(CountryDaoImpl.class);

    private EntityManager em;

    /**
     * Dependency injection of JPA EntityManager implementation through public constructor
     *
     * @param em
     */
    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> findAll() {
        logger.info("## Get all countries : ");
        CriteriaQuery<Country> criteriaQuery = buildCriteriaAll();
        TypedQuery<Country> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    private CriteriaQuery<Country> buildCriteriaAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        criteriaQuery.select(countryRoot);
        return criteriaQuery;
    }
}
