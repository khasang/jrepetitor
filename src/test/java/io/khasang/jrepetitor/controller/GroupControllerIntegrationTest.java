package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Group;
import io.khasang.jrepetitor.model.wrappers.GroupWrapper;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;
import static io.khasang.jrepetitor.util.TestUtils.*;

public class GroupControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/group";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addGroupAndCheck() {
        GroupWrapper groupWrapper = prefillGroup("group_name");
        Group group = createGroup(groupWrapper);

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

        deleteGroupFromDB(group);
    }

    @Test
    public void deleteGroup() {
        GroupWrapper groupWrapper = prefillGroup("group_name");
        Group group = createGroup(groupWrapper);

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
        GroupWrapper firstGroupWrapper = prefillGroup("first_group_name");
        GroupWrapper secondGroupWrapper = prefillGroup("second_group_name");

        Group firstGroup = createGroup(firstGroupWrapper);
        Group secondGroup = createGroup(secondGroupWrapper);

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Group>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Group>>() {
                }
        );

        List<Group> list = responseEntity.getBody();

        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteGroupFromDB(firstGroup);
        deleteGroupFromDB(secondGroup);
    }

    @Test
    public void removeGroup() {
        GroupWrapper groupWrapper = prefillGroup("group_name");
        Group group = createGroup(groupWrapper);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Group> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Group.class,
                group.getId()
        );
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(group.getId(), responseEntity.getBody().getId());
    }
}
