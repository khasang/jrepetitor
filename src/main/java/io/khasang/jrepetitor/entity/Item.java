package io.khasang.jrepetitor.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  One of Answer variants
 */
@Entity
@Table(name = "JR_ITEM")
public class Item implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 1000)
    private String content; //текст варианта ответа
    private byte correct; //правильно/не правильно

    @ManyToOne
    private Question question;

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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        // update association on Author entity
        if (question != null) {
            question.getItems().add(this);
        } else if (this.question != null) {
            this.question.getItems().remove(this);
        }
        this.question = question;
    }

    public byte getCorrect() {
        return correct;
    }

    public void setCorrect(byte correct) {
        this.correct = correct;
    }

}
