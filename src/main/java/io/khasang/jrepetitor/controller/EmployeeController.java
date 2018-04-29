package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.dto.EmployeeDTO;
import io.khasang.jrepetitor.entity.Employee;
import io.khasang.jrepetitor.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee employee) {

        return employeeService.addEmployee(employee);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<EmployeeDTO> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Employee getEmployeeById(@PathVariable(value = "id") String id) {
        return employeeService.getEmployeeById(Long.parseLong(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Employee deleteEmployee(@RequestParam(value = "id") String id) {
        return employeeService.deleteEmployee(Long.parseLong(id));
    }
}
