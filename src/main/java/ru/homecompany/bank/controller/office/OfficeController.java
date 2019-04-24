package ru.homecompany.bank.controller.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.homecompany.bank.service.office.OfficeService;
import ru.homecompany.bank.utils.MyResponse;
import ru.homecompany.bank.utils.ResponseDataView;
import ru.homecompany.bank.utils.ResponseErrorView;
import ru.homecompany.bank.view.office.OfficeFilter;
import ru.homecompany.bank.view.office.OfficeView;

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

    @PostMapping("/list/{orgId:[\\d]+}")
    public MyResponse getOfficeByOrganizationId(@PathVariable Integer orgId, @RequestBody OfficeFilter filter) {
        logger.info("## CONTROLLER LAYER ## Get office by orgId and by Filter: " + "ORG_ID= " + orgId);
        try {
            Object dataBody = officeService.findByFilter(orgId, filter);
            logger.info(dataBody.toString());
            return ResponseDataView.newCreator().setData(dataBody).create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }

    @GetMapping("/{id:[\\d]+}")
    public MyResponse getOfficeById(@PathVariable Integer id) {
        logger.info("## CONTROLLER LAYER ## Get office by ID : " + id);
        try {
            Object dataBody = officeService.findById(id);
            logger.info(dataBody.toString());
            return ResponseDataView.newCreator().setData(dataBody).create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }

    @PostMapping("/save")
    public MyResponse saveOffice(@RequestBody OfficeView view) {
        logger.info("## CONTROLLER LAYER ## Save office : " + view.toString());
        try {
            officeService.save(view);
            return ResponseDataView.newCreator().setData("success").create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }

    @PostMapping("/update")
    public MyResponse updateOffice(@RequestBody OfficeView view) {
        logger.info("## CONTROLLER LAYER ## Update office by ID : " + view.id);
        try {
            officeService.update(view);
            logger.info("## CONTROLLER LAYER ## Update office by ID : " + officeService.findById(view.id));
            return ResponseDataView.newCreator().setData("success").create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }
}
