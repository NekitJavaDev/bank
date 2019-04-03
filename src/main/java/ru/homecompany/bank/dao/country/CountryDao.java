package ru.homecompany.bank.dao.country;

import ru.homecompany.bank.model.Country;

import java.util.List;
import java.util.Set;

/**
 * DAO for working with country documents
 */
public interface CountryDao {

    /**
     * Get all countries
     *
     * @return {@Countries}
     */
    List<Country> findAll();
}
