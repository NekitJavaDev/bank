package ru.homecompany.bank.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.homecompany.bank.Application;
import ru.homecompany.bank.model.Country;
import ru.homecompany.bank.model.Document;
import ru.homecompany.bank.service.country.CountryService;
import ru.homecompany.bank.service.document.DocumentService;

import java.util.List;

/**
 * Test of all methods of the 2 classes (info for working with documents) from SERVICE layer of application
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration("src/main/resources")
@Transactional
public class DocumentInfoServiceTest {

    @Autowired
    DocumentService documentService;

    @Autowired
    CountryService countryService;

    /**
     * Test of all Country methods
     */
    @Test
    public void testCountries() {

        /*
         * Find all countries
         */
        List<Country> countries = countryService.findAll();
        Assert.assertNotNull(countries);
        Assert.assertFalse(countries.isEmpty());
        Assert.assertEquals(5, countries.size());
        Assert.assertEquals(601, Integer.parseInt(countries.get(0).getCode()));    //Code of the Russia equals '601'
        for (Country c : countries) {
            System.out.println(c.toString());
        }
    }

    /**
     * Test of all Document methods
     */
    @Test
    public void testDocuments() {

        /*
         * Find all documents
         */
        List<Document> documents = documentService.findAll();
        Assert.assertNotNull(documents);
        Assert.assertFalse(documents.isEmpty());
        Assert.assertEquals(6, documents.size());
        Assert.assertEquals(44, Integer.parseInt(documents.get(4).getCode()));    //Code of doc(the Driver's license of Russia) equals '44'
        for (Document d : documents) {
            System.out.println(d.toString());
        }
    }
}
