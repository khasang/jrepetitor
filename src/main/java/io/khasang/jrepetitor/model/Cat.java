package io.khasang.jrepetitor.model;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Cat {
    private String name;

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

//    @PostConstruct
//    public void init() {}
//
//    @PreDestroy
//    public void clean() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

