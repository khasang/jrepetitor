package io.khasang.jrepetitor.entity;

import javax.persistence.*;

@Entity
public class CatWoman {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private Cat cat;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @ManyToOne
    @JoinColumn(name = "cat_id", foreignKey = @ForeignKey(name = "CAT_ID_FK"))

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