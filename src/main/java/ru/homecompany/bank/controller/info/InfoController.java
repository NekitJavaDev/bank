package ru.homecompany.bank.controller.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.homecompany.bank.service.country.CountryService;
import ru.homecompany.bank.service.document.DocumentService;
import ru.homecompany.bank.utils.MyResponse;
import ru.homecompany.bank.utils.ResponseDataView;
import ru.homecompany.bank.utils.ResponseErrorView;

import java.util.logging.Logger;
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class InfoController {

    private final CountryService countryService;
    private final DocumentService documentService;
    private static Logger logger = Logger.getLogger(InfoController.class.getName());

    @Autowired
    public InfoController(CountryService countryService, DocumentService documentService) {
        this.countryService = countryService;
        this.documentService = documentService;
    }

    @GetMapping("/countries")
    public MyResponse getListOfCountries() {
        try {
            logger.info("## Get all countries : ");
            Object dataBody = countryService.findAll();
            logger.info(dataBody.toString());
            return ResponseDataView.newCreator().setData(dataBody).create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }

    @GetMapping("/docs")
    public MyResponse getListOfDocuments() {
        try {
            logger.info("## Get all documents : ");
            Object dataBody = documentService.findAll();
            logger.info((dataBody).toString());
            return ResponseDataView.newCreator().setData(dataBody).create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }
}
