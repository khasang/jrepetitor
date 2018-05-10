package io.khasang.jrepetitor.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RightAns {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemRightAns> rightAnsList = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ItemRightAns> getRightAnsList() {
        return rightAnsList;
    }

    public void setRightAnsList(List<ItemRightAns> rightAnsList) {
        this.rightAnsList = rightAnsList;
    }
}
