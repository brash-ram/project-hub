package com.example.projectHub.data.jpa;

import com.example.projectHub.data.entity.Project;
import com.example.projectHub.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

//    @Query(value = "SELECT * FROM projects WHERE name LIKE '%{name}%'", nativeQuery = true)
    List<Project> findAllByNameContainingIgnoreCase(String name);

    List<Project> findAllByAuthor(User author);
}