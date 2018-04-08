//package io.khasang.jrepetitor.entity;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "colors")
//public class Color {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//
//    private String name;
//
//    @ManyToMany(mappedBy = "colors")
//    private List<Cat> cats = new ArrayList<>();
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Cat> getCats() {
//        return cats;
//    }
//
//    public void setCats(List<Cat> cats) {
//        this.cats = cats;
//    }
//}
