package io.khasang.jrepetitor.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "JR_QUIZ_TRY")
public class QuizTry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiz_try_item_generator")
    @SequenceGenerator(name = "quiz_try_item_generator", sequenceName = "quiz_try_item_seq", allocationSize = 50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USER_ID_FK"))
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "quiz_id", foreignKey = @ForeignKey(name = "QUIZ_ID_FK"))
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Quiz quiz;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<QuizTryItem> tryItems;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public QuizTry() {
        tryItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<QuizTryItem> getTryItems() {
        return tryItems;
    }

    public void setTryItems(List<QuizTryItem> tryItems) {
        this.tryItems = tryItems;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void addQuizTryItem(QuizTryItem quizTryItem) {
        tryItems.add(quizTryItem);
    }
}
