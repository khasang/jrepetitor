package io.khasang.jrepetitor.entity;

import javax.persistence.*;

/**
 * Try
 *  When user takes a test he choose a group and a quiz
 *  Each Try is saved in UserTry
 *  User answers are saved in UserAnswer object
 */

@Entity
@Table(name = "JR_USER_ANSWER")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id",
            foreignKey = @ForeignKey(name = "QUESTION_ID_FK"))
    private Question question;

    @ManyToOne
    @JoinColumn(name = "item_id",
            foreignKey = @ForeignKey(name = "ITEN_ID_FK"))
    private Item item;

    @ManyToOne
    private UserTry userTry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public UserTry getUserTry() {
        return userTry;
    }

    public void setUserTry(UserTry userTry) {
        this.userTry = userTry;
    }
}
