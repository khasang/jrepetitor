package io.khasang.jrepetitor.entity;

import javax.persistence.*;
import java.util.List;

/**
 *  Test
 *  list of questions on one specified topic
 */

@Entity
@Table(name="JR_QUIZ")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="quiz_id")
    private Long id;

    private String name;

//    private List<Question> questions;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;

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
