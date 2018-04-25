package io.khasang.jrepetitor.entity;

import javax.persistence.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String middlename;
    private String surname;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    //todo next commit
    //@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //private User user;

    /*public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
