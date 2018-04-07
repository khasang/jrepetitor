package io.khasang.jrepetitor.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *  Test
 *  list of questions on one specified topic
 */

@Entity
@Table(name="JR_QUIZ")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Quiz implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="quiz_id")
    private Long id;

    private String name;

//    @JsonBackReference
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Question> questions;

//    @JsonManagedReference
//    @JsonIgnore
    @ManyToOne
    private Group group;

    private byte level; // question level

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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public byte getLevel() { return level;    }

    public void setLevel(byte level) { this.level = level; }
}
