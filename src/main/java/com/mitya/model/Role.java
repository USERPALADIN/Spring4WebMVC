package com.mitya.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role() {
    }
}