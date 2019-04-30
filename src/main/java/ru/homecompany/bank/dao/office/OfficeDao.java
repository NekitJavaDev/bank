package ru.homecompany.bank.dao.office;

import ru.homecompany.bank.model.Office;
import ru.homecompany.bank.view.office.OfficeFilter;

import java.util.List;

/**
 * DAO for working with offices by interacting with DATA BASE
 */
public interface OfficeDao {

    /**
     * Get office by organization ID and Filter parameters
     *
     * @param orgId  Identifier
     * @param filter Filter(name,phone and isActive)
     * @return @Offices
     */
    List<Office> filterList(Integer orgId, OfficeFilter filter);

    /**
     * Get office by ID
     *
     * @param id Identifier
     * @return @Office
     */
    Office findById(Integer id);

    /**
     * Update office by ID
     *
     * @param office Office
     */
    void update(Office office);

    /**
     * Save new office and ID was AUTO INCREMENTED
     *
     * @param office Office
     */
    void save(Office office);

    /**
     * Get office by name
     *
     * @param name Name
     * @return @Office
     */
    Office findByName(String name);

    /**
     * Get office by address
     *
     * @param address Address
     * @return @Office
     */
    Office findByAddress(String address);

    /**
     * Get all offices
     *
     * @return @Offices
     */
    List<Office> findAll();
}
