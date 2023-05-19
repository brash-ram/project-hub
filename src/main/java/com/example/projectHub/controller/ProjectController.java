package com.example.projectHub.controller;

import com.example.projectHub.dto.ProjectDTO;
import com.example.projectHub.dto.request.ProjectRegisterDTORequest;
import com.example.projectHub.dto.request.RequestToProjectDTO;
import com.example.projectHub.dto.response.UserRequesterDTOResponse;
import com.example.projectHub.service.ProjectService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/all")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ofNullable(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getUserById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ofNullable(projectService.getById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<ProjectDTO> registerProject(@RequestBody @NotNull ProjectRegisterDTORequest dto) {
        return ResponseEntity.ofNullable(projectService.register(dto));
    }

    @GetMapping("/requests")
    public ResponseEntity<List<UserRequesterDTOResponse>> getRequestsToProject(
            @RequestParam @NotNull Long projectId
    ) {
        return ResponseEntity.ofNullable(projectService.getRequesterById(projectId));
    }

    @GetMapping("/participants")
    public ResponseEntity<List<UserRequesterDTOResponse>> getProjectParticipants(
            @RequestParam @NotNull Long projectId
    ) {
        return ResponseEntity.ofNullable(projectService.getProjectParticipants(projectId));
    }

    @PostMapping("/requests/submit")
    public ResponseEntity<ProjectDTO> submitRequest(
            @RequestBody RequestToProjectDTO dto
    ) {
        return ResponseEntity.ofNullable(projectService.submitRequest(dto));
    }

    @PostMapping("/requests/discard")
    public ResponseEntity<ProjectDTO> discardRequest(
            @RequestBody RequestToProjectDTO dto
    ) {
        return ResponseEntity.ofNullable(projectService.discardRequest(dto));
    }

    @PostMapping("/requests/add")
    public ResponseEntity<Void> addRequestToProject(
            @RequestBody RequestToProjectDTO dto
            ) {
        projectService.addRequest(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProjectDTO>> sea(
            @RequestParam String projectName
    ) {
        List<ProjectDTO> dtos = projectService.search(projectName);
        return ResponseEntity.ok().body(dtos);
    }

    @PutMapping("/update")
    public ResponseEntity<ProjectDTO> updateProject(
            @RequestBody @NotNull ProjectDTO dto
    ) {
        return ResponseEntity.ofNullable(projectService.update(dto));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ProjectDTO>> byAuthor(
            @RequestParam @NotNull Long userId
    ) {
        return ResponseEntity.ofNullable(projectService.getByAuthor(userId));
    }
}
