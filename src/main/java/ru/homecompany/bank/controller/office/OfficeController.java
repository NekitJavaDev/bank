package ru.homecompany.bank.controller.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.homecompany.bank.service.office.OfficeService;
import ru.homecompany.bank.utils.MyResponse;
import ru.homecompany.bank.utils.ResponseDataView;
import ru.homecompany.bank.utils.ResponseErrorView;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/office", produces = "application/json")
public class OfficeController {

    private OfficeService officeService;

    private Logger logger = Logger.getLogger(OfficeController.class.getName());

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/{id:[\\d]+}")
    public MyResponse getOfficeById(@PathVariable Integer id) {
        logger.info("## Get office by ID : " + id);
        try {
            Object dataBody = officeService.findById(id);
            logger.info(dataBody.toString());
            return ResponseDataView.newCreator().setData(dataBody).create();
        } catch (Throwable e){
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }
}
