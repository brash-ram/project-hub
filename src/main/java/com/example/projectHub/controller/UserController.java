package com.example.projectHub.controller;

import com.example.projectHub.data.entity.User;
import com.example.projectHub.dto.UserDTO;
import com.example.projectHub.dto.request.AuthDTORequest;
import com.example.projectHub.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ofNullable(userService.getUserDtoById(id));
    }

    @PostMapping("/signUp")
    public ResponseEntity<Long> signUp(
            @RequestBody AuthDTORequest request
    ) {
        User user = userService.register(request.getUsername(), request.getPassword());
        return ResponseEntity.ok().body(user.getId());
    }

    @PostMapping("/signIn")
    public ResponseEntity<Long> signIn(
            @RequestBody AuthDTORequest request
    ) {
        UserDTO dto = userService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok().body(dto.getId());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> search(
            @RequestParam String userFullName
    ) {
        List<UserDTO> search = userService.search(userFullName);
        return ResponseEntity.ok().body(search);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateProject(
            @RequestBody @NotNull UserDTO dto
    ) {
        return ResponseEntity.ofNullable(userService.update(dto));
    }
}
