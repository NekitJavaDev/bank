package ru.homecompany.bank.dao.office;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.homecompany.bank.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public List<Office> findAll() {
        logger.info("## Get all offices : ");
        CriteriaQuery<Office> criteriaQuery = buildCriteriaAll();
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office findById(Integer id) {
        logger.info("## Get office by ID : " + id);
        CriteriaQuery<Office> criteriaQuery = buildCriteriaId(id);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office findByName(String name) {
        logger.info("## Get office by name : " + name);
        CriteriaQuery<Office> criteriaQuery = buildCriteriaName(name);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office findByAddress(String address) {
        logger.info("## Get office by address : " + address);
        CriteriaQuery<Office> criteriaQuery = buildCriteriaAddress(address);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office findByPhone(String phone) {
        logger.info("## Get office by phone : " + phone);
        CriteriaQuery<Office> criteriaQuery = buildCriteriaPhone(phone);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> findByIsActive(Boolean isActive) {
        logger.info("## Get office by active status : " + isActive);
        CriteriaQuery<Office> criteriaQuery = buildCriteriaIsActive(isActive);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

//    @Override
//    public List<Employee> loadAllEmployeesByOfficeId(Integer officeId) {
//        CriteriaQuery<Employee> criteriaQuery = buildCriteriaAllEmployees(officeId);
//        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
//        return query.getResultList();
//    }
//
//    private CriteriaQuery<Employee> buildCriteriaAllEmployees(Integer officeId) {
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
//        Root<Employee> employee = criteriaQuery.from(Employee.class);
//        criteriaQuery.where(criteriaBuilder.equal(employee.get("office"), officeId));
//        criteriaQuery.select(employee);
//        return criteriaQuery;
//    }

    private CriteriaQuery<Office> buildCriteriaAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> office = criteriaQuery.from(Office.class);
        criteriaQuery.select(office);
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

    private CriteriaQuery<Office> buildCriteriaPhone(String phone) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> office = criteriaQuery.from(Office.class);
        criteriaQuery.where(criteriaBuilder.equal(office.get("phone"), phone));
        criteriaQuery.select(office);
        return criteriaQuery;
    }

    private CriteriaQuery<Office> buildCriteriaIsActive(Boolean isActive) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> office = criteriaQuery.from(Office.class);
        criteriaQuery.where(criteriaBuilder.equal(office.get("isActive"), isActive));
        criteriaQuery.select(office);
        return criteriaQuery;
    }
}
