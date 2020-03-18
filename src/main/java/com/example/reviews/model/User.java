package com.example.reviews.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @ManyToMany(fetch= FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public void setRole(Role role) {
        roles.add(role);
    }
}
