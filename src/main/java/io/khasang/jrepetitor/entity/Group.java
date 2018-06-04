package io.khasang.jrepetitor.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Topics^
 * Java Code
 * Srping
 * Hibernate
 * Rest Api
 * Front end
 * .....
 */
@Entity
@Table(name = "JR_GROUP")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_generator")
    @SequenceGenerator(name = "group_generator", sequenceName = "group_seq", allocationSize = 50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz> quizes = new ArrayList<>();

    /**
     * topic name
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quiz> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<Quiz> quizes) {
        this.quizes = quizes;
    }

    public void addQuiz(Quiz quiz) {
        quizes.add(quiz);
    }
}
