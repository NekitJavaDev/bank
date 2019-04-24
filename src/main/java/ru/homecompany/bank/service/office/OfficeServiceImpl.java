package ru.homecompany.bank.service.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.dao.office.OfficeDao;
import ru.homecompany.bank.model.Office;
import ru.homecompany.bank.view.office.OfficeFilter;
import ru.homecompany.bank.view.office.OfficeFilterView;
import ru.homecompany.bank.view.office.OfficeView;

import java.util.ArrayList;
import java.util.List;

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
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<OfficeFilterView> findByFilter(Integer orgId, OfficeFilter filter) {
        List<Office> offices = officeDao.filterList(orgId, filter);
        List<OfficeFilterView> list = new ArrayList<>(6);
        for (int i = 0; i < offices.size(); i++) {
            OfficeFilterView viewList = new OfficeFilterView();
            viewList.setId(offices.get(i).getId());
            viewList.setName(offices.get(i).getName());
            viewList.setActive(offices.get(i).getIsActive());
            list.add(viewList);
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Office findById(Integer id) {
        return officeDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(OfficeView view) {
        Office office = officeDao.findById(view.id);
        if (office != null) {
            office.setOrganization(view.orgId);
            office.setName(view.name);
            office.setAddress(view.address);
            office.setPhone(view.phone);
            office.setIsActive(view.isActive);
            officeDao.update(office);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(OfficeView view) {
        Office office = new Office(view.name, view.address, view.phone, view.isActive);
        office.setOrganization(view.orgId);
        officeDao.save(office);
    }
}
