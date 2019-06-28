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

    /**
     * Testing all Country and Document methods from DAO layer
     */
    @Test
    public void test() {
        /*
            Testing to find all list of countries
         */
        List<Country> countries = countryDao.findAll();
        Assert.assertNotNull(countries);    //Assert.assertTrue(!countries.isEmpty());
        Assert.assertEquals(5, countries.size());
        for (Country c : countries) {
            System.out.println(c.toString());
        }

        /*
            Testing to find all list of documents
         */
        List<Document> documents = documentDao.findAll();
        Assert.assertNotNull(documents);    //Assert.assertTrue(!documents.isEmpty());
        Assert.assertEquals(6, documents.size());
        for (Document doc : documents) {
            System.out.println(doc.toString());
        }

        /*
            Testing to find document by code and name
         */
        Document documentName = documentDao.findByName("Водительское удостоверение РФ");
        Document documentCode = documentDao.findByCode(String.valueOf(21));
        Assert.assertNotNull(documentName);
        Assert.assertEquals(44, Long.parseLong(documentName.getCode()));
        Assert.assertNotNull(documentCode);
        Assert.assertEquals("Паспорт гражданина РФ", documentCode.getName());

        /*
            Testing to find country by code and name
         */
        Country countryName = countryDao.findByName("Португалия");
        Country countryCode = countryDao.findByCode(String.valueOf(601));
        Assert.assertNotNull(countryName);
        Assert.assertEquals(703, Long.parseLong(countryName.getCode()));
        Assert.assertNotNull(countryCode);
        Assert.assertEquals("Российская Федерация", countryCode.getName());
    }
}
