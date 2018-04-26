package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Student;
import io.khasang.jrepetitor.entity.Tutor;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TutorControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/tutor";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String UPDATE = "/update";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";


    @Test
    public void addTutorAndCheck() {
        Tutor tutor = createTutor();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Tutor> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Tutor.class,
                tutor.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Tutor receivedTutor = responseEntity.getBody();
        assertNotNull(receivedTutor);

        //   deleteFromDB(tutor);
    }

    @Test
    public void deleteTutor() {
        Tutor tutor = createTutor();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Tutor> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Tutor.class,
                tutor.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());

        Tutor deletedTutor = responseEntity.getBody();
        assertNotNull(deletedTutor);

        ResponseEntity<Tutor> responseForDeleteTutor = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Tutor.class,
                deletedTutor.getId()
        );

        assertEquals(200, responseForDeleteTutor.getStatusCodeValue());

        assertNull(responseForDeleteTutor.getBody());
    }

    @Test
    public void getAllTutors() {
        Tutor firstTutor = createTutor();
        Tutor secondTutor = createTutor();

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Tutor>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Tutor>>() {
                }
        );

        List<Tutor> list = responseEntity.getBody();

        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstTutor);
        deleteFromDB(secondTutor);
    }

    @Test
    public void updateTutor() {
        Tutor tutor = createTutor();
        String name = "Pushok";
        tutor.setName(name);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity entity = new HttpEntity(tutor, headers);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Tutor> responseEntity = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                Tutor.class
        );

        assertEquals(name, responseEntity.getBody().getName());

        deleteFromDB(tutor);
    }

    public Tutor deleteFromDB(Tutor tutor) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Tutor> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Tutor.class,
                tutor.getId()
        );

        return responseEntity.getBody();
    }

    private Tutor createTutor() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Tutor tutor = prefillTutor("Jack");

        HttpEntity entity = new HttpEntity(tutor, headers);

        RestTemplate template = new RestTemplate();
        Tutor receivedTutor = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Tutor.class
        ).getBody();

        assertNotNull(receivedTutor.getName());
        assertEquals(tutor.getName(), receivedTutor.getName());

        return receivedTutor;
    }

    private Tutor prefillTutor(String name) {
        Tutor tutor = new Tutor();
        tutor.setName(name);

        Student student1 = new Student();
        student1.setName("John");
        Student student2 = new Student();
        student2.setName("Jeff");

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        tutor.setStudentList(studentList);

        return tutor;
    }
}
