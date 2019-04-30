package ru.homecompany.bank.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.dao.employee.EmployeeDao;
import ru.homecompany.bank.model.Employee;
import ru.homecompany.bank.view.employee.EmployeeFilter;
import ru.homecompany.bank.view.employee.EmployeeFilterView;
import ru.homecompany.bank.view.employee.EmployeeView;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

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
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<EmployeeFilterView> findByFilter(EmployeeFilter filter) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Employee findById(Integer id) {

        return employeeDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(EmployeeView view) {
        Employee employee = employeeDao.findById(view.id);
        if (employee != null) {
            employee.setOffice(view.office);
            employee.setFirstName(view.firstName);
            employee.setSecondName(view.secondName);
            employee.setMiddleName(view.middleName);
            employee.setPosition(view.position);
            employee.setPhone(view.firstName);
            employee.setDocument(view.document);
            employee.setDocNumber(view.docNumber);
            employee.setDocDate(view.docDate);
            employee.setCountry(view.country);
            employee.setIsIdentified(view.isIdentified);
            employeeDao.save(employee);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(EmployeeView view) {
        Employee employee = new Employee();
        employee.setOffice(view.office);
        employee.setFirstName(view.firstName);
        employee.setSecondName(view.secondName);
        employee.setMiddleName(view.middleName);
        employee.setPosition(view.position);
        employee.setPhone(view.firstName);
        employee.setDocument(view.document);
        employee.setDocNumber(view.docNumber);
        employee.setDocDate(view.docDate);
        employee.setCountry(view.country);
        employee.setIsIdentified(view.isIdentified);
        employeeDao.save(employee);
    }
}
