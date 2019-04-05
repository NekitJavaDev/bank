package ru.homecompany.bank.controller.employee;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.homecompany.bank.service.employee.EmployeeService;
import ru.homecompany.bank.utils.ControllerException;
import ru.homecompany.bank.utils.MyResponse;
import ru.homecompany.bank.utils.ResponseDataView;
import ru.homecompany.bank.utils.ResponseErrorView;

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

//    @GetMapping("/list")
//    public @ResponseBody
//    String getListOfEmployees() {
//        return "List of employees";
//    }

    @GetMapping("/{id:[\\d]+}")
    public MyResponse getEmployeeById(@PathVariable("id") Integer id) {
        try {
            logger.info("## Get employees by id : " + id);
            if(id == null) throw new ControllerException();
            Object dataBody = employeeService.findById(id);
            logger.info(dataBody.toString());
            return ResponseDataView.newCreator().setData(dataBody).create();
        } catch (Throwable e) {
            return ResponseErrorView.newCreator().setError(e.getMessage()).create();
        }
    }
}
