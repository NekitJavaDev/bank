package ru.homecompany.bank.service.employee;

import org.springframework.validation.annotation.Validated;
import ru.homecompany.bank.model.Employee;
import ru.homecompany.bank.view.employee.EmployeeFilter;
import ru.homecompany.bank.view.employee.EmployeeFilterView;
import ru.homecompany.bank.view.employee.EmployeeView;

import javax.validation.Valid;
import java.util.List;

/**
 * Service
 */
@Validated
public interface EmployeeService {

    /**
     * Get employees by Filter
     *
     * @param filter Filter(JSON string)
     * @return @Employees
     */
    List<EmployeeFilterView> findByFilter(@Valid EmployeeFilter filter);

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
     * @param view View of employee
     */
    void update(@Valid EmployeeView view);

    /**
     * Save new employee and his ID was AUTO INCREMENTED
     *
     * @param view View of employee
     */
    void save(@Valid EmployeeView view);
}
