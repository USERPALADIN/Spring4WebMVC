package com.mitya.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    public Role() {
    }
    public Role(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}