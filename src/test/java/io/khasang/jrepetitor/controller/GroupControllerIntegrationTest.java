package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Group;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class GroupControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/group";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addGroupAndCheck() {
        Group group = createGroup();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Group> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Group.class,
                group.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Group receivedGroup = responseEntity.getBody();
        assertNotNull(receivedGroup);

        deleteFromDB(group);
    }

    @Test
    public void deleteGroup() {
        Group group = createGroup();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Group> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Group.class,
                group.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());

        Group deletedGroup = responseEntity.getBody();
        assertNotNull(deletedGroup);

        ResponseEntity<Group> responseForDeleteGroup = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Group.class,
                deletedGroup.getId()
        );

        assertEquals(200, responseForDeleteGroup.getStatusCodeValue());

        assertNull(responseForDeleteGroup.getBody());
    }

    @Test
    public void getAllGroups() {
        Group firstGroup = createGroup();
        Group secondGroup = createGroup();

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Group>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Group>>() {
                }
        );

        List<Group> list = responseEntity.getBody();

        // логика хромает
        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstGroup);
        deleteFromDB(secondGroup);
    }

    public Group deleteFromDB(Group group) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Group> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Group.class,
                group.getId()
        );

        return responseEntity.getBody();
    }

    private Group createGroup() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Group group = prefillGroup();

        HttpEntity entity = new HttpEntity(group, headers);

        RestTemplate template = new RestTemplate();

        Group receivedGroup = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Group.class
        ).getBody();

        assertNotNull(receivedGroup.getName());
        assertEquals(group.getName(), receivedGroup.getName());

        return receivedGroup;
    }

    private Group prefillGroup() {
        Group group = new Group();
        group.setName("Java Core");
        return group;
    }
}
