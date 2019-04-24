package ru.homecompany.bank.dao.office;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.homecompany.bank.model.Office;
import ru.homecompany.bank.view.office.OfficeFilter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of repository OfficeDao
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final Logger logger = LoggerFactory.getLogger(OfficeDaoImpl.class);

    private EntityManager em;

    /**
     * Dependency injection of JPA EntityManager implementation through public constructor
     *
     * @param em
     */
    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> filterList(Integer orgId, OfficeFilter filter) {
        if (filter != null) {
            logger.info("## DAO LAYER ## Get office by org ID and Filter parameters : " + orgId + " " + filter.name + " " + filter.isActive);
        }
        CriteriaQuery<Office> criteriaQuery = buildCriteriaFilterList(orgId, filter);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get office by org ID and Filter parameters : " + query.getResultList());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office findById(Integer id) {
        logger.info("## DAO LAYER ## Get office by ID : " + id);
        CriteriaQuery<Office> criteriaQuery = buildCriteriaId(id);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get office by ID : " + query.getSingleResult());
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) {
        logger.info("## DAO LAYER ## Update office : " + office.toString());
        em.merge(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        logger.info("## DAO LAYER ## Save office : " + office.toString());
        em.persist(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office findByName(String name) {
        logger.info("## DAO LAYER ## Get office by name : " + name);
        CriteriaQuery<Office> criteriaQuery = buildCriteriaName(name);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get office by name : " + query.getSingleResult());
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office findByAddress(String address) {
        logger.info("## DAO LAYER ## Get office by address : " + address);
        CriteriaQuery<Office> criteriaQuery = buildCriteriaAddress(address);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get office by address : " + query.getSingleResult());
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> findAll() {
        logger.info("## DAO LAYER ## Get all offices : ");
        CriteriaQuery<Office> criteriaQuery = buildCriteriaAll();
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get all offices : " + query.getResultList());
        return query.getResultList();
    }

    private CriteriaQuery<Office> buildCriteriaFilterList(Integer orgId, OfficeFilter filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);

        List<Predicate> predicateList = new ArrayList<>(4);
        Predicate pOrganization = criteriaBuilder.equal(officeRoot.get("organization").get("id"), orgId);
        if (orgId == null) {
            return null;
        } else if (filter == null) {
            predicateList.add(pOrganization);
        } else {
            if (filter.name != null) {
                predicateList.add(criteriaBuilder.like(officeRoot.get("name"), "%" + filter.name + "%"));
            }
            if (filter.phone != null) {
                predicateList.add(criteriaBuilder.like(officeRoot.get("phone"), "%" + filter.phone + "%"));
            }
            if (filter.isActive != null) {
                predicateList.add(criteriaBuilder.equal(officeRoot.get("isActive"), filter.isActive));
            }
            predicateList.add(pOrganization);
        }
        criteriaQuery.select(officeRoot).distinct(true).where(predicateList.toArray(new Predicate[]{}));
        return criteriaQuery;
    }

    private CriteriaQuery<Office> buildCriteriaId(Integer id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        criteriaQuery.where(criteriaBuilder.equal(officeRoot.get("id"), id));
        criteriaQuery.select(officeRoot);
        return criteriaQuery;
    }

    private CriteriaQuery<Office> buildCriteriaName(String name) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> office = criteriaQuery.from(Office.class);
        criteriaQuery.where(criteriaBuilder.equal(office.get("name"), name));
        criteriaQuery.select(office);
        return criteriaQuery;
    }

    private CriteriaQuery<Office> buildCriteriaAddress(String address) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> office = criteriaQuery.from(Office.class);
        criteriaQuery.where(criteriaBuilder.equal(office.get("address"), address));
        criteriaQuery.select(office);
        return criteriaQuery;
    }

    private CriteriaQuery<Office> buildCriteriaAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> office = criteriaQuery.from(Office.class);
        criteriaQuery.select(office);
        return criteriaQuery;
    }
}
