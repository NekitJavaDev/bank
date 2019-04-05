package ru.homecompany.bank.service.office;

import ru.homecompany.bank.model.Office;

/**
 * Service
 */
public interface OfficeService {

    /**
     * Get office by ID
     *
     * @param id Identifier
     * @return {@Office}
     */
    Office findById(Integer id);

}
