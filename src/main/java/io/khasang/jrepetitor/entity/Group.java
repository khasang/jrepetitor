package io.khasang.jrepetitor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *Topics^
 *  Java Code
 *  Srping
 *  Hibernate
 *  Rest Api
 *  Front end
 *  .....
 */
@Entity
@Table(name = "JR_GROUP")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Group implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "group_id")
    private Long id;

//    @JsonBackReference
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Quiz> quizes;

    private String name; //наименование темы

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
}
