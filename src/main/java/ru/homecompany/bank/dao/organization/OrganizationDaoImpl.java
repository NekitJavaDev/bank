package ru.homecompany.bank.dao.organization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.homecompany.bank.model.Organization;
import ru.homecompany.bank.view.organization.OrganizationFilter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of repository OrganizationDao using JPA
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final Logger logger = LoggerFactory.getLogger(OrganizationDaoImpl.class);

    private final EntityManager em;

    /**
     * Dependency injection of JPA EntityManager implementation through public constructor
     *
     * @param em
     */
    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> filterList(OrganizationFilter filter) {
        logger.info("## DAO LAYER ## Get organizations by Filter(name,inn,isActive) : " + filter.name + " " + filter.inn + " " + filter.isActive);
        CriteriaQuery<Organization> criteriaQuery = buildCriteriaFilterList(filter);
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get organizations by Filter : " + query.getResultList());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization findById(Integer id) {
        logger.info("## DAO LAYER ## Get organization by ID : " + id);
        CriteriaQuery<Organization> criteriaQuery = buildCriteriaId(id);
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get organization by ID : " + query.getSingleResult());
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {
        logger.info("## DAO LAYER ## Update organization : " + organization.toString());
        em.merge(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        logger.info("## DAO LAYER ## Save organization : " + organization.toString());
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> findAll() {
        logger.info("## DAO LAYER ## Get all organizations : ");
        CriteriaQuery<Organization> criteriaQuery = buildCriteriaAll();
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get all organizations : " + query.getResultList());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization findByName(String name) {
        logger.info("## DAO LAYER ## Get organization by name : " + name);
        CriteriaQuery<Organization> criteriaQuery = buildCriteriaName(name);
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get organization by name : " + query.getSingleResult());
        return query.getSingleResult();
    }

    private CriteriaQuery<Organization> buildCriteriaFilterList(OrganizationFilter filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);

        Predicate pName = criteriaBuilder.like(organizationRoot.get("name"), "%" + filter.name + "%");
        Predicate pInn = criteriaBuilder.like(organizationRoot.get("inn"), "%" + filter.inn + "%");
        Predicate pIsActive = criteriaBuilder.equal(organizationRoot.get("isActive"), filter.isActive);
        List<Predicate> predicates = new ArrayList<>(3);
        if (filter.name != null) {
            predicates.add(pName);
        } else {
            return null;
        }
        if (filter.inn != null) {
            predicates.add(pInn);
        }
        if (filter.isActive != null) {
            predicates.add(pIsActive);
        }
        criteriaQuery.select(organizationRoot).where(predicates.toArray(new Predicate[]{}));
        return criteriaQuery;
    }

    private CriteriaQuery<Organization> buildCriteriaId(Integer id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        criteriaQuery.where(criteriaBuilder.equal(organizationRoot.get("id"), id));
        criteriaQuery.select(organizationRoot);
        return criteriaQuery;
    }

    private CriteriaQuery<Organization> buildCriteriaAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        criteriaQuery.select(organizationRoot);
        return criteriaQuery;
    }

    private CriteriaQuery<Organization> buildCriteriaName(String name) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        criteriaQuery.where(criteriaBuilder.equal(organizationRoot.get("name"), name));
        criteriaQuery.select(organizationRoot);
        return criteriaQuery;
    }
}
