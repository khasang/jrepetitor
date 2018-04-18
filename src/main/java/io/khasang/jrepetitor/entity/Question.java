package io.khasang.jrepetitor.entity;


import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Question
 * question has a type: RadioGroup (only one correct answer) or Checkbox (a set of correct answers)
 */

@Entity
@Table(name = "JR_QUESTION")
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 1000)
    private String content; //текст вопроса
    private String type; //тип вопроса: "radio/CheckBoz"

    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    private Collection<Item> items = new ArrayList<Item>(); //ответ

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

    @org.hibernate.annotations.LazyCollection(LazyCollectionOption.EXTRA)
    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        // update association on Author entity
        if (quiz != null) {
            quiz.getQuestions().add(this);
        } else if (this.quiz != null) {
            this.quiz.getQuestions().remove(this);
        }
        this.quiz = quiz;
    }
}
