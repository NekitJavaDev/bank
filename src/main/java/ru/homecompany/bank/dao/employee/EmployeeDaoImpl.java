package ru.homecompany.bank.dao.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.homecompany.bank.model.Employee;
import ru.homecompany.bank.view.employee.EmployeeFilter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of repository EmployeeDao
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private EntityManager em;

    private Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    /**
     * Dependency injection of JPA EntityManager implementation through public constructor
     *
     * @param em
     */
    @Autowired
    public EmployeeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> findByFilter(EmployeeFilter filter) {
        if (filter != null) {
            logger.info("## DAO LAYER ## Get employee by office ID and Filter parameters: " + filter.officeId + " " + filter.firstName + " " +
                    filter.lastName + " " + filter.middleName + " " + filter.position + " " + filter.docCode + " " + filter.citizenshipCode);
            CriteriaQuery<Employee> criteriaQuery = buildCriteriaFilterList(filter);
            TypedQuery<Employee> query = em.createQuery(criteriaQuery);
            logger.info("## DAO LAYER ## Get employee by office ID and Filter parameters: " + query.getResultList());
            return query.getResultList();
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee findById(Integer id) {
        logger.info("## DAO LAYER ## Get employee by ID : " + id);
        CriteriaQuery<Employee> criteriaQuery = buildCriteriaId(id);
        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get employee by ID : " + query.getSingleResult());
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Employee employee) {
        logger.info("## DAO LAYER ## Update employee : " + employee.toString());
        em.merge(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Employee employee) {
        logger.info("## DAO LAYER ## Save employee : " + employee.toString());
        em.persist(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> findAll() {
        logger.info("## DAO LAYER ## Get all employees : ");
        CriteriaQuery<Employee> criteriaQuery = buildCriteriaAll();
        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get all employees : " + query.getResultList());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> findByFirstName(String firstName) {
        logger.info("## DAO LAYER ## Get employee by first name : " + firstName);
        CriteriaQuery<Employee> criteriaQuery = buildCriteriaFirstName(firstName);
        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get office by first name : " + query.getSingleResult());
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> findByPosition(String position) {
        logger.info("## DAO LAYER ## Get employee by position : " + position);
        CriteriaQuery<Employee> criteriaQuery = buildCriteriaPosition(position);
        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        logger.info("## DAO LAYER ## Get employee by position : " + query.getResultList());
        return query.getResultList();
    }

    private CriteriaQuery<Employee> buildCriteriaFilterList(EmployeeFilter filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

        List<Predicate> predicateList = new ArrayList<>(7);
        if (filter.officeId == null) {
            return null;
        } else {
            predicateList.add(criteriaBuilder.equal(employeeRoot.get("office").get("id"), filter.officeId));
            if (filter.firstName != null) {
                predicateList.add(criteriaBuilder.like(employeeRoot.get("firstName"), "%" + filter.firstName + "%"));
            }
            if (filter.lastName != null) {
                predicateList.add(criteriaBuilder.like(employeeRoot.get("secondName"), "%" + filter.lastName + "%"));
            }
            if (filter.middleName != null) {
                predicateList.add(criteriaBuilder.like(employeeRoot.get("middleName"), "%" + filter.middleName + "%"));
            }
            if (filter.position != null) {
                predicateList.add(criteriaBuilder.like(employeeRoot.get("position"), "%" + filter.position + "%"));
            }
            if (filter.docCode != null) {
                predicateList.add(criteriaBuilder.equal(employeeRoot.get("document").get("code"), filter.docCode));
            }
            if (filter.citizenshipCode != null) {
                predicateList.add(criteriaBuilder.equal(employeeRoot.get("country").get("code"), filter.citizenshipCode));
            }
        }
        criteriaQuery.select(employeeRoot).where(predicateList.toArray(new Predicate[]{}));
        return criteriaQuery;
    }

    private CriteriaQuery<Employee> buildCriteriaId(Integer id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.where(criteriaBuilder.equal(employeeRoot.get("id"), id));
        criteriaQuery.select(employeeRoot);
        return criteriaQuery;
    }

    private CriteriaQuery<Employee> buildCriteriaAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(employeeRoot);
        return criteriaQuery;
    }

    private CriteriaQuery<Employee> buildCriteriaFirstName(String firstName) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.where(criteriaBuilder.equal(employeeRoot.get("firstName"), firstName));
        criteriaQuery.select(employeeRoot);
        return criteriaQuery;
    }

    private CriteriaQuery<Employee> buildCriteriaPosition(String position) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.where(criteriaBuilder.equal(employeeRoot.get("position"), position));
        criteriaQuery.select(employeeRoot);
        return criteriaQuery;
    }
}
