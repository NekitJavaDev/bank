package ru.homecompany.bank.dao.organization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.homecompany.bank.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Implementation of repository OrganizationDao
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
    public List<Organization> findAll() {
        logger.info("## Get all organizations : ");
        CriteriaQuery<Organization> criteriaQuery = buildCriteriaAll();
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization findById(Integer id) {
        logger.info("## Get organization by ID : " + id);
        CriteriaQuery<Organization> criteriaQuery = buildCriteriaId(id);
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization findByName(String name) {
        logger.info("## Get organization by name : " + name);
        CriteriaQuery<Organization> criteriaQuery = buildCriteriaName(name);
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {
        logger.info("Update organization " + organization.toString());
        em.merge(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        logger.info("## Save organization :" + organization.toString());
        em.persist(organization);
    }

//    @Override
//    public void delete(Long id) {
//        Organization organization = em.find(Organization.class, id);
//        logger.info("Organization deleted ID:" + id);
//        em.remove(organization);
//    }

    private CriteriaQuery<Organization> buildCriteriaAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        criteriaQuery.select(organizationRoot);
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

    private CriteriaQuery<Organization> buildCriteriaName(String name) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        criteriaQuery.where(criteriaBuilder.equal(organizationRoot.get("name"), name));
        criteriaQuery.select(organizationRoot);
        return criteriaQuery;
    }
}
