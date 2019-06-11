package ru.homecompany.bank.service.employee;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.dao.country.CountryDao;
import ru.homecompany.bank.dao.document.DocumentDao;
import ru.homecompany.bank.dao.employee.EmployeeDao;
import ru.homecompany.bank.dao.office.OfficeDao;
import ru.homecompany.bank.model.Country;
import ru.homecompany.bank.model.Document;
import ru.homecompany.bank.model.Employee;
import ru.homecompany.bank.model.Office;
import ru.homecompany.bank.view.employee.EmployeeFilter;
import ru.homecompany.bank.view.employee.EmployeeFilterView;
import ru.homecompany.bank.view.employee.EmployeeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of service EmployeeService
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    private OfficeDao officeDao;

    private CountryDao countryDao;

    private DocumentDao documentDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao, OfficeDao officeDao, CountryDao countryDao, DocumentDao documentDao) {
        this.employeeDao = employeeDao;
        this.officeDao = officeDao;
        this.countryDao = countryDao;
        this.documentDao = documentDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<EmployeeFilterView> findByFilter(EmployeeFilter filter) {
        if (filter == null) {
            throw new ServiceException("Filter must include 1 required parameter: 'officeId'");
        }
        List<Employee> employees = employeeDao.findByFilter(filter);
        List<EmployeeFilterView> list = new ArrayList<>(10);
        for (int i = 0; i < employees.size(); i++) {
            EmployeeFilterView viewList = new EmployeeFilterView();
            viewList.setId(employees.get(i).getId());
            viewList.setFirstName(employees.get(i).getFirstName());
            viewList.setSecondName(employees.get(i).getSecondName());
            viewList.setMiddleName(employees.get(i).getMiddleName());
            viewList.setPosition(employees.get(i).getPosition());
            list.add(viewList);
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Employee findById(Integer id) {
        Employee employee = employeeDao.findById(id);
        if (employee == null) {
            throw new ServiceException("Employee with ID: " + id + " doesn't exist");
        } else {
            return employee;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(EmployeeView view) {
        if (view.id == null || view.id.toString().isEmpty()) {
            throw new ServiceException("Cannot update employee with empty or null ID");
        }
        Employee employee = employeeDao.findById(view.id);
        if (employee != null) {
            employee.setFirstName(view.firstName);
            employee.setPosition(view.position);
            if (view.secondName != null) {
                employee.setSecondName(view.secondName);
            }
            if (view.middleName != null) {
                employee.setMiddleName(view.middleName);
            }
            if (view.phone != null) {
                employee.setPhone(view.phone);
            }
            if (view.docName != null) {
                Document document = documentDao.findByName(view.docName);
                employee.setDocument(document);
            }
            if (view.docCode != null) {
                Document document = documentDao.findByCode(view.docCode);
                employee.setDocument(document);
            }
            if (view.docDate != null) {
                employee.setDocDate(view.docDate);
            }
            if (view.citizenshipCode != null) {
                Country country = countryDao.findByCode(view.citizenshipCode);
                employee.setCountry(country);
            }
            if (view.isIdentified != null) {
                employee.setIsIdentified(view.isIdentified);
            }
        }
        employeeDao.update(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(EmployeeView view) {
        if (view.officeId == null || view.officeId.toString().isEmpty()) {
            throw new ServiceException("Cannot save employee with empty or null officeId");
        }
        Office office = officeDao.findById(view.officeId);
        Employee employee = new Employee(view.firstName, view.secondName, view.middleName, view.position, view.phone, view.docNumber, view.docDate, view.isIdentified);
        employee.setOffice(office);
        if (view.docCode != null) {
            Document documentByCode = documentDao.findByCode(view.docCode);
            employee.setDocument(documentByCode);
        } else if (view.docName != null) {
            Document documentByName = documentDao.findByName(view.docName);
            employee.setDocument(documentByName);
        }
        if (view.citizenshipCode != null) {
            Country countryByCode = countryDao.findByCode(view.citizenshipCode);
            employee.setCountry(countryByCode);
        }else{
            employee.setCountry(null);
        }
        employeeDao.save(employee);
    }
}
