package com.mitya.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "password")
    private String password;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User() {

    }

    public User(String login) {
        this.login = login;
    }

    public User(String name, String login, String password, Collection<Role> role) {

        this.name = name;
        this.login = login;
        this.password = password;
        this.roles = role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }
}