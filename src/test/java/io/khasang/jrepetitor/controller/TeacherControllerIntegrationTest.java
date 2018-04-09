package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Student;
import io.khasang.jrepetitor.entity.Teacher;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TeacherControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/teacher";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";
    private static final String ALL = "/all";
    private static final String UPDATE = "/update";

    @Test
    public void getAllTeachers() {
        List<Teacher> teachers = createTeacher();

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Teacher>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Teacher>>() {
                }
        );

        List<Teacher> receivedTeachers = responseEntity.getBody();
        assertNotNull(receivedTeachers.get(0));
        assertNotNull(receivedTeachers.get(1));
    }

    private Teacher deleteFromDB(Teacher teacher) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Teacher> responseEntity = template.exchange(
                ROOT + DELETE + "?id={id}",
                HttpMethod.DELETE,
                null,
                Teacher.class,
                teacher.getId()
        );

        return responseEntity.getBody();
    }

    private List<Teacher> createTeacher() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        List<Teacher> teachers = prefillTeachers();
        List<Teacher> receivedTeachers = new ArrayList<>();

        teachers.forEach(teacher -> {
            HttpEntity<Teacher> entity = new HttpEntity<>(teacher, headers);

            RestTemplate template = new RestTemplate();

            Teacher receivedTeacher = template.exchange(
                    ROOT + ADD,
                    HttpMethod.POST,
                    entity,
                    Teacher.class
            ).getBody();

            assertNotNull(receivedTeacher.getName());
            assertEquals(teacher.getName(), receivedTeacher.getName());

            receivedTeachers.add(receivedTeacher);
        });

        return receivedTeachers;
    }

    private List<Teacher> prefillTeachers() {
        Teacher teacher1 = new Teacher();
        teacher1.setName("Dmitry");
        teacher1.setTemper("strict");
        List<Student> students1 =
                Arrays.asList(new Student("Vladimir", 120), new Student("Elena", 115));
        teacher1.setStudents(students1);

        Teacher teacher2 = new Teacher();
        teacher2.setName("Nadejda");
        teacher2.setTemper("soft");

        List<Student> students2 =
                Arrays.asList(new Student("Vladimir", 120),
                        new Student("Pete", 150), new Student("Maria", 135));
        teacher2.setStudents(students2);

        return Arrays.asList(teacher1, teacher2);
    }
}
