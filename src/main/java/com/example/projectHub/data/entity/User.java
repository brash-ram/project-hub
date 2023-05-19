package com.example.projectHub.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "fio")
    private String fullName;

    @Column(name = "bio")
    private String bio;

    @Column(name = "chatId")
    private Long chatId;

    @Column(name = "dateRegistration")
    private Timestamp dateRegistration;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "requests")
    private Set<Project> requests;
}
