package io.khasang.jrepetitor.service;

import io.khasang.jrepetitor.dto.EmployeeDTO;
import io.khasang.jrepetitor.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    List<EmployeeDTO> getAllEmployees();

    Employee getEmployeeById(long l);

    Employee deleteEmployee(long l);
}
