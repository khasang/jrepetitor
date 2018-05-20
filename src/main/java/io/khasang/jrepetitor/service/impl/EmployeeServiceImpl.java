package io.khasang.jrepetitor.service.impl;

import io.khasang.jrepetitor.dao.EmployeeDao;
import io.khasang.jrepetitor.dto.EmployeeDTO;
import io.khasang.jrepetitor.entity.Employee;
import io.khasang.jrepetitor.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private EmployeeDTO employeeDTO;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.create(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeDTO.getEmployeeDTOList(employeeDao.getList());
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeDao.getById(id);
    }

    @Override
    public Employee deleteEmployee(long id) {
        return employeeDao.delete(getEmployeeById(id));
    }
}
