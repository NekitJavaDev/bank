package ru.homecompany.bank.service.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.dao.country.CountryDao;
import ru.homecompany.bank.model.Country;

import java.util.List;

/**
 * Implementation of service CountryService
 */
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    /**
     * Dependency injection of CountryDao implementation through public constructor
     *
     * @param countryDao
     */
    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        return countryDao.findAll();
    }
}