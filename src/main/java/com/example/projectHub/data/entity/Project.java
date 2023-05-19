package com.example.projectHub.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Entity
@Data
@Table(name = "projects")
@Accessors(chain = true)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "budget")
    private Float budget;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "queueRequests")
    private Set<User> queueRequests;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "participants")
//    @JoinTable(name = "users",
//            joinColumns = @JoinColumn(name = "requests"),
//            inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<User> participants;

    @OneToOne
//    @Column(name = "author")
    private User author;

}
