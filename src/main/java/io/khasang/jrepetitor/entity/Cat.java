package io.khasang.jrepetitor.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CatWoman> catWomanList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", foreignKey = @ForeignKey(name = "DISH_ID_FK"))
    private Dish dish;

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

    public List<CatWoman> getCatWomanList() {
        return catWomanList;
    }

    public void setCatWomanList(List<CatWoman> catWomanList) {
        this.catWomanList = catWomanList;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
