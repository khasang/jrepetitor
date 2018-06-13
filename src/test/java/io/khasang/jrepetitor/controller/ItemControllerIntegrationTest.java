package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Item;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class ItemControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/item";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";

    @Test
    public void addItemAndCheck() {
        Item item = createItem();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Item> responseEntity = template.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Item.class,
                item.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());

        Item receivedItem = responseEntity.getBody();
        assertNotNull(receivedItem);

        deleteFromDB(item);
    }

    @Test
    public void deleteItem() {
        Item item = createItem();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Item> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Item.class,
                item.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());

        Item deletedItem = responseEntity.getBody();
        assertNotNull(deletedItem);

        ResponseEntity<Item> responseForDeleteItem = null;
        try {
            responseForDeleteItem = restTemplate.exchange(
                    ROOT + GET_BY_ID + "/{id}",
                    HttpMethod.GET,
                    null,
                    Item.class,
                    deletedItem.getId()
            );
        } catch (HttpClientErrorException e) {
            assertEquals(e.getMessage(), "404 null");
        }
    }

    @Test
    public void getAllItems() {
        Item firstItem = createItem();
        Item secondItem = createItem();

        RestTemplate template = new RestTemplate();

        ResponseEntity<List<Item>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Item>>() {
                }
        );

        List<Item> list = responseEntity.getBody();

        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        deleteFromDB(firstItem);
        deleteFromDB(secondItem);
    }

    public Item deleteFromDB(Item item) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Item> responseEntity = restTemplate.exchange(
                ROOT + DELETE + "?id=" + "{id}",
                HttpMethod.DELETE,
                null,
                Item.class,
                item.getId()
        );

        return responseEntity.getBody();
    }

    private Item createItem() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Item item = prefillItem();

        HttpEntity entity = new HttpEntity(item, headers);

        RestTemplate template = new RestTemplate();

        Item receivedItem = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Item.class
        ).getBody();

        assertNotNull(receivedItem.getContent());
        assertEquals(item.getContent(), receivedItem.getContent());

        return receivedItem;
    }

    private Item prefillItem() {
        Item item = new Item();
        item.setContent("Yes");
        return item;
    }
}
