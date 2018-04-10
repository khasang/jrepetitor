package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Pupil;
import io.khasang.jrepetitor.entity.Teacher;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeacherControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/teacher";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addTeacherAndCheck() {
        Teacher teacher = createTeacher();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Teacher> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Teacher.class,
                teacher.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Teacher receivedTeacher = responseEntity.getBody();
        assertNotNull(receivedTeacher);

        deleteFromDB(teacher);
    }

    @Test
    public void deleteTeacher() {
        Teacher teacher = createTeacher();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Teacher> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Teacher.class,
                teacher.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());

        Teacher deletedTeacher = responseEntity.getBody();
        assertNotNull(deletedTeacher);

        ResponseEntity<Teacher> responseForDeleteTeacher = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Teacher.class,
                deletedTeacher.getId()
        );

        assertEquals(200, responseForDeleteTeacher.getStatusCodeValue());

        assertNull(responseForDeleteTeacher.getBody());
    }

    @Test
    public void getAllTeachers() {
        Teacher firstTeacher = createTeacher();
        Teacher secondTeacher = createTeacher();

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Teacher>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Teacher>>() {
                }
        );

        List<Teacher> list = responseEntity.getBody();

        // логика хромает
        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstTeacher);
        deleteFromDB(secondTeacher);
    }

    public Teacher deleteFromDB(Teacher teacher) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Teacher> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Teacher.class,
                teacher.getId()
        );

        return responseEntity.getBody();
    }

    private Teacher createTeacher() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Teacher teacher = prefillTeacher("OlgaPavlovna");

        HttpEntity entity = new HttpEntity(teacher, headers);

        RestTemplate template = new RestTemplate();

        Teacher receivedTeacher = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Teacher.class
        ).getBody();

        assertNotNull(receivedTeacher.getName());
        assertEquals(teacher.getName(), receivedTeacher.getName());

        return receivedTeacher;
    }

    private Teacher prefillTeacher(String name) {
        Teacher teacher = new Teacher();
        teacher.setName(name);

        Pupil pupil1 = new Pupil();
        pupil1.setYear(LocalDate.of(1980, 10, 22));
        pupil1.setName("Vasia");

        Pupil pupil2 = new Pupil();
        pupil2.setYear(LocalDate.of(1981, 01, 15));
        pupil2.setName("Nastia");

        List<Pupil> pupilList = new ArrayList<>();
        pupilList.add(pupil1);
        pupilList.add(pupil2);
        teacher.setCarList(pupilList);
       /* TeacherWoman teacherWoman1 = new TeacherWoman();
        TeacherWoman1.setName("Riska");

        TeacherWoman teacherWoman2 = new TeacherWoman();
        TeacherWoman2.setName("Murka");

        List<Teacher> TeacherWomanList = new ArrayList<>();
        TeacherWomanList.add(TeacherWoman1);
        TeacherWomanList.add(TeacherWoman2);

        Teacher.setTeacherWomanList(TeacherWomanList);*/
        return teacher;
    }
}
