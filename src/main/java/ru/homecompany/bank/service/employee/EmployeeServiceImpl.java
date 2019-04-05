package ru.homecompany.bank.service.employee;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.dao.employee.EmployeeDao;
import ru.homecompany.bank.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Employee findById(Integer id) {
        return employeeDao.findById(id);
    }
}
