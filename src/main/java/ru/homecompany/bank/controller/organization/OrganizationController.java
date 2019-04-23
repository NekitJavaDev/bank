package ru.homecompany.bank.controller.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.homecompany.bank.service.organization.OrganizationService;
import ru.homecompany.bank.utils.MyResponse;
import ru.homecompany.bank.utils.ResponseDataView;
import ru.homecompany.bank.utils.ResponseErrorView;
import ru.homecompany.bank.view.organization.OrganizationFilter;
import ru.homecompany.bank.view.organization.OrganizationView;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/organization", produces = "application/json")
public class OrganizationController {

    private final OrganizationService organizationService;
    private static Logger logger = Logger.getLogger(OrganizationController.class.getName());

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/list")
    public MyResponse postListOfOrganizations(@RequestBody OrganizationFilter filter) {
        logger.info("## CONTROLLER LAYER ## Get list of organizations by Filter : " + filter.name + " " + filter.inn + " " + filter.isActive);
        try {
            Object dataBody = organizationService.findByFilter(filter);
            logger.info("## CONTROLLER LAYER ## " + dataBody.toString());
            return ResponseDataView.newCreator().setData(dataBody).create();
        } catch (Throwable e) {
            if (filter.name != null) {
                return ResponseErrorView.newCreator().setError("name = " + e.getMessage()).create();
            } else {
                return ResponseErrorView.newCreator().setError(e.getMessage()).create();
            }
        }

    }

    @GetMapping("/{id:[\\d]+}")
    public MyResponse getOrganizationById(@PathVariable("id") Integer orgId) {
        logger.info("## CONTROLLER LAYER ## Get organization by ID : " + orgId);
        try {
            Object dataBody = organizationService.findById(orgId);
            logger.info("## CONTROLLER LAYER ## " + dataBody.toString());
            return ResponseDataView.newCreator().setData(dataBody).create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }

    @PostMapping("/update")
    public MyResponse updateOrganization(@RequestBody OrganizationView view) {
        logger.info("## CONTROLLER LAYER ## Update organization by ID : " + view.id);
        try {
            organizationService.update(view);
            logger.info("## CONTROLLER LAYER ## " + view.toString());
            return ResponseDataView.newCreator().setData("success").create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }

    @PostMapping("/save")
    public MyResponse saveOrganization(@RequestBody OrganizationView view) {
        try {
            logger.info("## CONTROLLER LAYER ## Save organization : " + view.toString());
            organizationService.save(view);
            return ResponseDataView.newCreator().setData("success").create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }
}
