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

    /**
     * {@inheritDoc}
     */
    @Override
    public Country findByCode(String code) {
        CriteriaQuery<Country> criteriaQuery = buildCriteriaCode(code);
        TypedQuery<Country> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country findByName(String name) {
        CriteriaQuery<Country> criteriaQuery = buildCriteriaName(name);
        TypedQuery<Country> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    private CriteriaQuery<Country> buildCriteriaAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        criteriaQuery.select(countryRoot);
        return criteriaQuery;
    }

    private CriteriaQuery<Country> buildCriteriaCode(String code) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        criteriaQuery.where(criteriaBuilder.equal(countryRoot.get("code"), code));
        criteriaQuery.select(countryRoot);
        return criteriaQuery;
    }

    private CriteriaQuery<Country> buildCriteriaName(String name) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        criteriaQuery.where(criteriaBuilder.equal(countryRoot.get("name"), name));
        criteriaQuery.select(countryRoot);
        return criteriaQuery;
    }
}
