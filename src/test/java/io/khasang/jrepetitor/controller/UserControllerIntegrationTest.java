package io.khasang.jrepetitor.controller;

import io.khasang.jrepetitor.entity.User;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/user";
    private static final String ADD = "/add";
    private static final String ALL = "/all";
    private static final String GET_BY_ID = "/get";
    private static final String DELETE = "/delete";
}
