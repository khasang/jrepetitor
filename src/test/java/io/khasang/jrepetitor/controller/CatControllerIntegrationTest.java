package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.model.Cat;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CatControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/cat";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get";

    @Test
    public void addCat() {
        Cat cat = createCat();
    }

    private Cat createCat() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat();

        return null;
    }

    private Cat prefillCat() {
        Cat cat = new Cat();
        return null;
    }

}
