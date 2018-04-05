package io.khasang.jrepetitor.entity;

import javax.persistence.*;

/**
 *  One of Answer variants
 */
@Entity
@Table(name = "JR_ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content; //текст варианта ответа
    private boolean correct; //правильно/не правильно

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

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

}
