package io.khasang.jrepetitor.entity;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *  Question
 *  question has a type: RadioGroup (only one correct answer) or Checkbox (a set of correct answers)
 */

@Entity
@Table(name = "JR_QUESTION")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Question implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="question_id")
    private Long id;

    @Column(length = 1000)
    private String content; //текст вопроса
    private String type; //тип вопроса: "radio/CheckBoz"

//    @JsonBackReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Item> items; //ответ

//    @JsonManagedReference
    @ManyToOne
    private Quiz quiz;

    @Column(length = 1000)
    private String explanation; //объясенение правильного ответа

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
