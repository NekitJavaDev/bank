package ru.homecompany.bank.dao.employee;

import ru.homecompany.bank.model.Employee;
import ru.homecompany.bank.view.employee.EmployeeFilter;

import java.util.List;

/**
 * DAO for working with employees by interacting with DATA BASE
 */
public interface EmployeeDao {

    /**
     * Get employees by Filter
     *
     * @param filter Filter(JSON string)
     * @return @Employees
     */
    List<Employee> findByFilter(EmployeeFilter filter);

    /**
     * Get employee by ID
     *
     * @param id Identifier
     * @return @Employee
     */
    Employee findById(Integer id);

    /**
     * Update employee by ID
     *
     * @param employee Entity of employee
     */
    void update(Employee employee);

    /**
     * Save new employee and his ID was AUTO INCREMENTED
     *
     * @param employee Entity of employee
     */
    void save(Employee employee);

    /**
     * Get all employees
     *
     * @return @Employees
     */
    List<Employee> findAll();

    /**
     * Get employees by his first name
     *
     * @param firstName Name
     * @return @Employees
     */
    List<Employee> findByFirstName(String firstName);

    /**
     * Get employees by his position at work
     *
     * @param position Position
     * @return @Employees
     */
    List<Employee> findByPosition(String position);
}
