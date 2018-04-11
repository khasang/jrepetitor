package io.khasang.jrepetitor.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Transient
    private static final String ROLE = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    private String role_name;

    public User() {
        role_name = ROLE;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_name() {
        return role_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
