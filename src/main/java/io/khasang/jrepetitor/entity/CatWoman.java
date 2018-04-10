package io.khasang.jrepetitor.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CatWoman {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*@Column(name = "idcatwoman")*/
    private long id;


    private String name;

    /*@OneToOne(optional = false, mappedBy = "catWoman")
    private Cat cat;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }*/
    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Cat> catList = new ArrayList<>();

    public List<Cat> getCatList() {

        return catList;
    }

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
    }*/

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}