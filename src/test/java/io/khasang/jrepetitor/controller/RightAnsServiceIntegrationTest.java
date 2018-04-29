package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.ItemRightAns;
import io.khasang.jrepetitor.entity.RightAns;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RightAnsServiceIntegrationTest {
    private static final String ROOT = "http://localhost:8080/rightans";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addEmployeeAndCheck() {
        RightAns rightAns = createRightAns();

        RestTemplate template = new RestTemplate();
        ResponseEntity<RightAns> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                RightAns.class,
                rightAns.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        RightAns receivedRightAns = responseEntity.getBody();
        assertNotNull(receivedRightAns);

//        deleteFromDB(employee);
    }

    private RightAns createRightAns() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        RightAns rightAns = prefillRightAns(1);

        HttpEntity entity = new HttpEntity(rightAns, headers);

        RestTemplate template = new RestTemplate();

        RightAns receivedRightAns = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                RightAns.class
        ).getBody();

        assertNotNull(receivedRightAns.getId());
        return receivedRightAns;
    }

    private RightAns prefillRightAns(int i) {
        RightAns rightAns = new RightAns();

        ItemRightAns itemRightAns1 = new ItemRightAns();
        itemRightAns1.setAttemptId(i);
        itemRightAns1.setRightAnsId(20);

        ItemRightAns itemRightAns2 = new ItemRightAns();
        itemRightAns2.setAttemptId(i);
        itemRightAns2.setRightAnsId(21);

        List<ItemRightAns> rightAnsList = new ArrayList<>();
        rightAnsList.add(itemRightAns1);
        rightAnsList.add(itemRightAns2);
        rightAns.setRightAnsList(rightAnsList);

        return rightAns;
    }
}
