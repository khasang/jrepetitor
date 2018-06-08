package io.khasang.jrepetitor.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "JR_QUIZ_TRY_ITEM")
public class QuizTryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiz_try_generator")
    @SequenceGenerator(name = "quiz_try_generator", sequenceName = "quiz_try_seq", allocationSize = 50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @JoinColumn(name = "question_id", foreignKey = @ForeignKey(name = "QUESTION_ID_FK"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Question question;

    @JoinColumn(name = "quiz_try_id", foreignKey = @ForeignKey(name = "QUIZ_TRY_ID_ID_FK"))
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private QuizTry quizTry;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Item> selectedItems;


    public QuizTryItem() {
        selectedItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Item> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<Item> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public void addSelectedItem(Item item) {
        selectedItems.add(item);
    }

    public QuizTry getQuizTry() {
        return quizTry;
    }

    public void setQuizTry(QuizTry quizTry) {
        this.quizTry = quizTry;
    }
}
