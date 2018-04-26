package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.EmployeeDTO;
import io.khasang.jrepetitor.entity.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * method for add employee
     *
     * @param employee = new employee for creating in DB
     * @return created employee
     */
    Employee addEmployee(Employee employee);

    /**
     * method for update
     *
     * @param employee = employee for update
     * @return updated employee
     */
    Employee updateEmployee(Employee employee);

    /**
     * method for receiving all employees
     *
     * @return all employees
     */
    List<EmployeeDTO> getAllEmployees();

    /**
     * method for specify employee by id
     *
     * @param id = uniq employee id
     * @return specify employee by id
     */
    Employee getEmployeeById(long id);

    /**
     * method for employee delete
     *
     * @param id = employee's id for delete
     * @return removed employee
     */
    Employee deleteEmployee(long id);
}
