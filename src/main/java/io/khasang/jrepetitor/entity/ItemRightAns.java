package io.khasang.jrepetitor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemRightAns {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int attemptId;

    private int rightAnsId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(int attemptId) {
        this.attemptId = attemptId;
    }

    public int getRightAnsId() {
        return rightAnsId;
    }

    public void setRightAnsId(int rightAnsId) {
        this.rightAnsId = rightAnsId;
    }
}
