package ru.homecompany.bank.dao.office;


import ru.homecompany.bank.model.Office;

import java.util.List;

/**
 * DAO for working with offices
 */
public interface OfficeDao {

    /**
     * Gets all offices
     *
     * @return {@Offices}
     */
    List<Office> findAll();

    /**
     * Get office by ID
     *
     * @param id Identifier
     * @return {@Office}
     */
    Office findById(Integer id);

    /**
     * Gets office by name
     *
     * @param name Name
     * @return {@Office}
     */
    Office findByName(String name);

    /**
     * Gets office by address
     *
     * @param address Address
     * @return {@Office}
     */
    Office findByAddress(String address);

    /**
     * Gets office by phone
     *
     * @param phone Work telephone
     * @return {@Office}
     */
    Office findByPhone(String phone);

    /**
     * Gets office by activity
     *
     * @param isActive Activity
     * @return {@Office}
     */
    List<Office> findByIsActive(Boolean isActive);

//    /**
//     * Gets all employees by office ID
//     *
//     * @param officeId Office Identifier
//     * @return {@Office}
//     */
//    List<Employee> loadAllEmployeesByOfficeId(Integer officeId);
}
