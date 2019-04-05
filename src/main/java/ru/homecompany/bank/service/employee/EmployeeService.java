package ru.homecompany.bank.service.employee;


import ru.homecompany.bank.model.Employee;

/**
 * Service
 */
public interface EmployeeService {


    /**
     * Get employee by ID
     *
     * @param id Identifier
     * @return {@Employee}
     */
    Employee findById(Integer id);

}
