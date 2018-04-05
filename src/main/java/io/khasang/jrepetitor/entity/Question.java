package io.khasang.jrepetitor.entity;


import javax.persistence.*;
import java.util.List;

/**
 *  Question
 *  question has a type: RadioGroup (only one correct answer) or Checkbox (a set of correct answers)
 */

@Entity
@Table(name = "JR_QUESTION")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="question_id")
    private Long id;

    private String content; //текст вопроса
    private String type; //тип вопроса: "RadioGroup/CheckBoz"

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Item> items; //ответ

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
}
