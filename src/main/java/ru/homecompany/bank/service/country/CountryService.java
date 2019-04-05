package ru.homecompany.bank.service.country;

import ru.homecompany.bank.model.Country;

import java.util.List;

/**
 * Service
 */
public interface CountryService {

    /**
     * Get list of countries
     *
     * @return {@Countries}
     */
    List<Country> findAll();
}
