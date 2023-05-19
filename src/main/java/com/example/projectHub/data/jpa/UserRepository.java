package com.example.projectHub.data.jpa;

import com.example.projectHub.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM users WHERE fio LIKE '%{name}%'", nativeQuery = true)
    List<User> findAllByFullNameContainingIgnoreCase(String fullName);
}
