package io.khasang.jrepetitor.entity;

import javax.persistence.*;
import java.util.List;

/**
 *Topics^
 *  Java Code
 *  Srping
 *  Hibernate
 *  Rest Api
 *  Front end
 *  .....
 */
@Entity
@Table(name = "JR_GROUP")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private Long id;

//    List<Quiz> quizes;

    private String name; //наименование темы

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
