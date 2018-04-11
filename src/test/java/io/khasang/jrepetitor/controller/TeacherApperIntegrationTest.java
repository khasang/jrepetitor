package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Apprentice;
import io.khasang.jrepetitor.entity.Teacher;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TeacherApperIntegrationTest {

    private static final String ROOT = "http://localhost:8080/teacher";
    private static final String ADD = "/add";

    private static final String GET_ALL = "/all";




    @Test
    public void getAllTeacherFromDB()
    {
        Teacher t1 = createTeacher();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Teacher>> entity = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Teacher>>() {

                }
        );

        List<Teacher> list = entity.getBody();

       // assertNotNull(list.get(1));
        //assertNotNull(list.get(2));


    }






    private Teacher createTeacher() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Teacher teacher = prefillTeacher("Karry");

        HttpEntity entity = new HttpEntity(teacher, headers);

        RestTemplate template = new RestTemplate();

        Teacher receivedTeacher = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Teacher.class
        ).getBody();

        assertNotNull(receivedTeacher.getName());
        assertEquals(teacher.getName(),receivedTeacher.getName());

        return receivedTeacher;
    }

    private Teacher prefillTeacher(String name) {
        Teacher teacher = new Teacher();
        teacher.setName(name);

        Apprentice a1 = new Apprentice();
        a1.setName("Kom");

        Apprentice a2 = new Apprentice();
       a2.setName("Kin");

        List<Apprentice> apprentices = new ArrayList<>();
        apprentices.add(a1);
        apprentices.add(a2);
        teacher.setApprenticeList(apprentices);

        return teacher;
    }
}
