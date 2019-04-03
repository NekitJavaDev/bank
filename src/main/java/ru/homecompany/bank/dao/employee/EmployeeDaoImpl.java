//package ru.homecompany.bank.dao.employee;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import ru.homecompany.bank.model.Employee;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.util.List;
//
///**
// * Implementation of repository EmployeeDao
// */
//@Repository
//public class EmployeeDaoImpl implements EmployeeDao {
//
//    private EntityManager em;
//
//    /**
//     * Dependency injection of JPA EntityManager implementation through public constructor
//     *
//     * @param em
//     */
//    @Autowired
//    public EmployeeDaoImpl(EntityManager em) {
//        this.em = em;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public List<Employee> findAll() {
//        CriteriaQuery<Employee> criteriaQuery = buildCriteriaAll();
//        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
//        return query.getResultList();
//    }
//
//    private CriteriaQuery<Employee> buildCriteriaAll() {
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
//        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
//        criteriaQuery.select(employeeRoot);
//        return criteriaQuery;
//    }
//
////    /**
////     * {@inheritDoc}
////     */
////    @Override
////    public Employee findById(Integer id) {
////        return null;
////    }
////
////    /**
////     * {@inheritDoc}
////     */
////    @Override
////    public List<Employee> findByFirstName(String firstName) {
////        CriteriaQuery<Employee> criteriaQuery = buildCriteriaName(firstName);
////        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
////        return query.getResultList();
////    }
////
////    /**
////     * {@inheritDoc}
////     */
////    @Override
////    public List<Employee> findByPosition(String position) {
////        CriteriaQuery<Employee> criteriaQuery = buildCriteriaPosition(position);
////        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
////        return query.getResultList();
////    }
////
////    /**
////     * {@inheritDoc}
////     */
////    @Override
////    public Employee findByDocumentId(Integer documentId) {
////        CriteriaQuery<Employee> criteriaQuery = buildCriteriaDocument(documentId);
////        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
////        return query.getSingleResult();
////    }
////
////    /**
////     * {@inheritDoc}
////     */
////    @Override
////    public List<Employee> findByCountryId(Integer countryId) {
////        CriteriaQuery<Employee> criteriaQuery = buildCriteriaCountry(countryId);
////        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
////        return query.getResultList();
////    }
////
////    private CriteriaQuery<Employee> buildCriteriaDocument(Integer documentId) {
////        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
////        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
////        Root<Employee> employee = criteriaQuery.from(Employee.class);
////        criteriaQuery.where(criteriaBuilder.equal(employee.get("documentId"), documentId));
////        criteriaQuery.select(employee);
////        return criteriaQuery;
////    }
//
//
////    private CriteriaQuery<Employee> buildCriteriaName(String firstName) {
////        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
////        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
////        Root<Employee> employee = criteriaQuery.from(Employee.class);
////        criteriaQuery.where(criteriaBuilder.equal(employee.get("firstName"), firstName));
////        criteriaQuery.select(employee);
////        return criteriaQuery;
////    }
////
////    private CriteriaQuery<Employee> buildCriteriaPosition(String position) {
////        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
////        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
////        Root<Employee> employee = criteriaQuery.from(Employee.class);
////        criteriaQuery.where(criteriaBuilder.equal(employee.get("position"), position));
////        criteriaQuery.select(employee);
////        return criteriaQuery;
////    }
////
////    private CriteriaQuery<Employee> buildCriteriaCountry(Integer countryId) {
////        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
////        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
////        Root<Employee> employee = criteriaQuery.from(Employee.class);
////        criteriaQuery.where(criteriaBuilder.equal(employee.get("info"), countryId));
////        criteriaQuery.select(employee);
////        return criteriaQuery;
////    }
//}
