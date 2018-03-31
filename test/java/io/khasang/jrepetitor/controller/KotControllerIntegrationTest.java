package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.Kot;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

public class KotControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/kot";
    private static final String ADD = "/add";
    private static final String UPDATE = "/update";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private static final String DELETE = "/delete";

//    @Test
    public void add(){
        //выполнить POST и чтобы в результате добавился кот в таблицу
        Kot resultKot=CreateKot();

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Kot> entity= restTemplate.exchange(
                ROOT + GET+"/{id}",
                HttpMethod.GET,
                null,
                Kot.class,
                resultKot.getId()
        );

        assertEquals(entity.getBody().getId(),resultKot.getId());
//        assertNull(resultKot);
    }
    @Test
    public void update(){
        //выполнить POST и чтобы в результате добавился кот в таблицу
        Kot resultKot=CreateKot();
        resultKot.setName("Murzik");

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity httpEntity = new HttpEntity(resultKot,headers);

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Kot> entity= restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                httpEntity,
                Kot.class
        );

        assertEquals(200,entity.getStatusCodeValue());
        assertEquals(entity.getBody().getName(),"Murzik");
//        assertNull(resultKot);
    }

//    @Test
    public void delete(){

        Kot kot=CreateKot(); //создали кота

        //удаляем кота
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Kot> entity= restTemplate.exchange(
                ROOT + DELETE+"/?id="+"{id}",
                HttpMethod.DELETE,
                null,
                Kot.class,
                kot.getId()
        );

        assertEquals(200, entity.getStatusCodeValue());

        Kot deletedKot=entity.getBody();

        assertNotNull(deletedKot);

        //проверяем что этогог кота нет в базе
        ResponseEntity<Kot> responseEntity= restTemplate.exchange(
                ROOT + GET+"/{id}",
                HttpMethod.GET,
                null,
                Kot.class,
                deletedKot.getId()
        );

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());
    }

//    @Test
    public void getAllKots(){

        Kot kot1 = CreateKot();
        Kot kot2 = CreateKot();

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<List<Kot>> entity = restTemplate.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Kot>>() {
                }
        );

        assertEquals(200,entity.getStatusCodeValue());

        List<Kot> list=entity.getBody();
        assertNotNull(list.get(0));
        assertNotNull(list.get(1));
    }


    private Kot CreateKot() {

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Kot kot=PrefiIlKot();

        HttpEntity httpEntity = new HttpEntity(kot,headers);

        RestTemplate restTemplate = new RestTemplate();

        Kot resultKot=restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                httpEntity,
                Kot.class
        ).getBody();

        assertNotNull(resultKot.getName());
        assertEquals(resultKot.getName(),kot.getName());

        return resultKot;
    }

    private Kot PrefiIlKot() {
        Kot kot=new Kot();
        kot.setName("barsik");

        return kot;
    }

}
