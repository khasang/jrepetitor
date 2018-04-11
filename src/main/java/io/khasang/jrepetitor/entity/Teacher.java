package io.khasang.jrepetitor.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Teacher {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public List<Apprentice> getApprenticeList() {
        return apprenticeList;
    }

    public void setApprenticeList(List<Apprentice> apprenticeList) {
        this.apprenticeList = apprenticeList;
    }

    @ManyToMany(fetch = javax.persistence.FetchType.EAGER, cascade = CascadeType.ALL)

    private List<Apprentice> apprenticeList = new ArrayList<Apprentice>();

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
