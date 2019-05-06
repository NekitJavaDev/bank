package ru.homecompany.bank.dao.country;

import ru.homecompany.bank.model.Country;

import java.util.List;

/**
 * DAO for working with country documents
 */
public interface CountryDao {

    /**
     * Get all countries
     *
     * @return @Countries
     */
    List<Country> findAll();

    /**
     * Get country by code
     *
     * @param code Unique Code
     * @return @Country
     */
    Country findByCode(String code);

    /**
     * Get country by name
     *
     * @param name Unique Name
     * @return @Country
     */
    Country findByName(String name);

}
