package io.khasang.jrepetitor.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*@OneToOne
    @JoinColumn(name = "idcat", referencedColumnName = "idcatwoman")*/
    private long id;


    private String name;

   /* @OneToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "idcat")
    CatWoman catWoman;

    public CatWoman getCatWoman() {
        return catWoman;
    }

    public void setCatWoman(CatWoman catWoman) {
        this.catWoman = catWoman;
    }*/

    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CatWoman> catWomanList = new ArrayList<>();

    public List<CatWoman> getCatWomanList() {
        return catWomanList;

    }

    public void setCatWomanList(List<CatWoman> catWomanList) {
        this.catWomanList = catWomanList;
    }*/
    /*@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cat> catList = new ArrayList<>();

    public List<Cat> getCatList() {
        return catList;
    }

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}