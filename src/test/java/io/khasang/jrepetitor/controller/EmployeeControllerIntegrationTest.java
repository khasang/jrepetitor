package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Car;
import io.khasang.jrepetitor.entity.Employee;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/employee";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addEmployeeAndCheck() {
        Employee employee = createEmployee();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Employee> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Employee.class,
                employee.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Employee receivedEmployee = responseEntity.getBody();
        assertNotNull(receivedEmployee);

//        deleteFromDB(employee);
    }

    @Test
    public void deleteEmployee() {
        Employee employee = createEmployee();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Employee.class,
                employee.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());

        Employee deletedEmployee = responseEntity.getBody();
        assertNotNull(deletedEmployee);

        ResponseEntity<Employee> responseForDeleteEmployee = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Employee.class,
                deletedEmployee.getId()
        );

        assertEquals(200, responseForDeleteEmployee.getStatusCodeValue());

        assertNull(responseForDeleteEmployee.getBody());
    }

    @Test
    public void getAllEmployees() {
        Employee firstEmployee = createEmployee();
        Employee secondEmployee = createEmployee();

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Employee>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {
                }
        );

        List<Employee> list = responseEntity.getBody();

        // логика хромает
        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstEmployee);
        deleteFromDB(secondEmployee);
    }

    public Employee deleteFromDB(Employee employee) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Employee.class,
                employee.getId()
        );

        return responseEntity.getBody();
    }

    private Employee createEmployee() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Employee employee = prefillEmployee("Jack");

        HttpEntity entity = new HttpEntity(employee, headers);

        RestTemplate template = new RestTemplate();

        Employee receivedEmployee = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Employee.class
        ).getBody();

        assertNotNull(receivedEmployee.getName());
        assertEquals(employee.getName(), receivedEmployee.getName());

        return receivedEmployee;
    }

    private Employee prefillEmployee(String name) {
        Employee employee = new Employee();
        employee.setName(name);

        Car car1 = new Car();
        car1.setYear(LocalDate.of(2017, 11, 12));
        car1.setModel("Vaz");

        Car car2 = new Car();
        car2.setYear(LocalDate.of(2018, 01, 22));
        car2.setModel("BMW");

        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        employee.setCarList(carList);

        return employee;
    }
}
