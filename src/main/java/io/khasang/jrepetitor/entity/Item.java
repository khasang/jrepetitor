package io.khasang.jrepetitor.entity;

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
    /**
     *      Answer content
     */
    private String content;

    /**
     * 1 - Correct, 0 - incorrect
     */
    private byte correct;

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

    public byte getCorrect() {
        return correct;
    }

    public void setCorrect(byte correct) {
        this.correct = correct;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}