package ru.homecompany.bank.service.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homecompany.bank.dao.office.OfficeDao;
import ru.homecompany.bank.model.Office;

/**
 * Implementation of service OfficeService
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private OfficeDao officeDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office findById(Integer id) {
        return officeDao.findById(id);
    }
}
