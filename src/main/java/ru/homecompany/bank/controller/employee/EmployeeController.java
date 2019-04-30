package ru.homecompany.bank.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.homecompany.bank.service.employee.EmployeeService;
import ru.homecompany.bank.utils.MyResponse;
import ru.homecompany.bank.utils.ResponseDataView;
import ru.homecompany.bank.utils.ResponseErrorView;
import ru.homecompany.bank.view.employee.EmployeeFilter;
import ru.homecompany.bank.view.employee.EmployeeView;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
public class EmployeeController {

    private static Logger logger = Logger.getLogger(EmployeeController.class.getName());

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/list")
    public MyResponse getEmployeesByFilter(@RequestBody EmployeeFilter filter) {
        logger.info("## CONTROLLER LAYER ## Get employees by Filter officeId: " + filter.officeId);
        try {
            Object dataBody = employeeService.findByFilter(filter);
            logger.info("## CONTROLLER LAYER ## Get employees by Filter: " + dataBody.toString());
            return ResponseDataView.newCreator().setData(dataBody).create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }

    @GetMapping("/{id:[\\d]+}")
    public MyResponse getEmployeeById(@PathVariable Integer id) {
        try {
            logger.info("## CONTROLLER LAYER ## Get employees by id: " + id);
            Object dataBody = employeeService.findById(id).toString();
            logger.info(dataBody.toString());
            return ResponseDataView.newCreator().setData(dataBody).create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }

    @PostMapping("/update")
    public MyResponse updateEmployee(@RequestBody EmployeeView view) {
        logger.info("## CONTROLLER LAYER ## Update employee by ID: " + view.id);
        try {
            employeeService.update(view);
            logger.info("## CONTROLLER LAYER ## Update employee by ID : " + employeeService.findById(view.id));
            return ResponseDataView.newCreator().setData("success").create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }

    @PostMapping("/save")
    public MyResponse saveEmployee(@RequestBody EmployeeView view) {
        logger.info("## CONTROLLER LAYER ## Save employee: " + view.toString());
        try {
            employeeService.save(view);
            return ResponseDataView.newCreator().setData("success").create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }
}
