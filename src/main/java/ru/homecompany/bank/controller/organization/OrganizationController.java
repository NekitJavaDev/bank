package ru.homecompany.bank.controller.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.homecompany.bank.service.organization.OrganizationService;
import ru.homecompany.bank.utils.ControllerException;
import ru.homecompany.bank.utils.MyResponse;
import ru.homecompany.bank.utils.ResponseDataView;
import ru.homecompany.bank.utils.ResponseErrorView;
import ru.homecompany.bank.view.organization.OrganizationFilter;
import ru.homecompany.bank.view.organization.OrganizationView;

import java.util.List;
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

//    @PostMapping(value = "/update", params = {"id", "name", "fullName"})
//
//    public MyResponse updateOrganization(OrganizationView organizationView) {
//        try {
//            logger.info("## Organization update " + organizationView.toString());
//            organizationService.update(organizationView);
//            return ResponseDataView.newCreator().setData("success").create();
//        } catch (Throwable e) {
//            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
//        }
//    }

    @PostMapping("/list")
    public MyResponse postListOfOrganizations(@RequestBody OrganizationFilter filter) {
        logger.info("## Get list of organization by Filter : " + filter.name + " " + filter.inn + " " + filter.isActive);
        try {
            Object dataBody = organizationService.findByFilter(filter);
            logger.info("## GET LIST OF ORGANIZATION WITH FILTER : " + dataBody);
            return ResponseDataView.newCreator().setData(dataBody).create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }

    @GetMapping("/{id:[\\d]+}")
    public MyResponse getOrganizationById(@PathVariable("id") Integer orgId) {
        logger.info("## Get organization by ID : " + orgId);
        try {
            Object dataBody = organizationService.findById(orgId);
            return ResponseDataView.newCreator().setData(dataBody).create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }

}
