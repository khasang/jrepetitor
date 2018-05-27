package io.khasang.jrepetitor.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Try
 * When user takes a test he choose a group and a quiz
 * Each Try is saved in UserTry
 * User answers are saved in UserAnswer object
 */

@Entity
@Table(name = "JR_USER_TRY")
public class UserTry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USER_ID_FK"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "GROUP_ID_FK"))
    private Group group;

    @ManyToOne
    @JoinColumn(name = "quiz_id", foreignKey = @ForeignKey(name = "QUIZ_ID_FK"))
    private Quiz quiz;

    private Timestamp timestamp;

    private int score;

    @OneToMany(mappedBy = "userTry", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserAnswer> userAnswers = new ArrayList<UserAnswer>();

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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
