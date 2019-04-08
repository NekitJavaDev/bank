package ru.homecompany.bank.dao;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.Application;
import ru.homecompany.bank.dao.country.CountryDao;
import ru.homecompany.bank.dao.document.DocumentDao;
import ru.homecompany.bank.model.Country;
import ru.homecompany.bank.model.Document;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration("src/main/resources")
@Transactional
public class DocumentInfoDaoTest {

    @Autowired
    CountryDao countryDao;

    @Autowired
    DocumentDao documentDao;

    @Test
    public void test() {
        List<Country> countries = countryDao.findAll();
        Assert.assertNotNull(countries);    //Assert.assertTrue(!countries.isEmpty());
        Assert.assertEquals(5, countries.size());

        List<Document> documents = documentDao.findAll();
        Assert.assertNotNull(documents);    //Assert.assertTrue(!documents.isEmpty());
        Assert.assertEquals(6, documents.size());
    }

}
