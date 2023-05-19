package com.example.projectHub.service;

import com.example.projectHub.data.entity.Project;
import com.example.projectHub.data.entity.User;
import com.example.projectHub.data.jpa.ProjectRepository;
import com.example.projectHub.dto.ProjectDTO;
import com.example.projectHub.dto.request.ProjectRegisterDTORequest;
import com.example.projectHub.dto.request.RequestToProjectDTO;
import com.example.projectHub.dto.response.UserRequesterDTOResponse;
import com.example.projectHub.util.DtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final UserService userService;

    private final DtoMapper dtoMapper;

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(dtoMapper::mapProjectToProjectDtoResponse)
                .collect(Collectors.toList());
    }

    public ProjectDTO register(ProjectRegisterDTORequest dto) {
        User user = userService.getUserById(dto.getUserId());
        Project project = dtoMapper.mapProjectRegisterDTORequestToProject(dto);
        project.setAuthor(user);
        return dtoMapper.mapProjectToProjectDtoResponse(projectRepository.save(project));
    }

    public List<UserRequesterDTOResponse> getRequesterById(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        List<UserRequesterDTOResponse> userRequesterDTOResponses;
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            userRequesterDTOResponses = project.getQueueRequests().stream()
                    .map(dtoMapper::mapUserToUserRequesterDTOResponse)
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Project is not presented");
        }
        return userRequesterDTOResponses;
    }

    public List<UserRequesterDTOResponse> getProjectParticipants(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        List<UserRequesterDTOResponse> userRequesterDTOResponses;
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            userRequesterDTOResponses = project.getParticipants().stream()
                    .map(dtoMapper::mapUserToUserRequesterDTOResponse)
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Project is not presented");
        }
        return userRequesterDTOResponses;
    }

    @Transactional
    public ProjectDTO update(ProjectDTO dto) {
        Project project = projectById(dto.getId())
                .setBudget(dto.getBudget())
                .setName(dto.getName())
                .setDescription(dto.getDescription());
        return dtoMapper.mapProjectToProjectDtoResponse(project);
    }

    @Transactional
    public ProjectDTO submitRequest(RequestToProjectDTO dto) {
        Project project = projectById(dto.getProjectId());
        Optional<User> optionalUser = project.getQueueRequests().stream()
                .filter(user -> user.getId().equals(dto.getRequesterId()))
                .findFirst();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            project.getQueueRequests().remove(user);
            project.getParticipants().add(user);
            projectRepository.save(project);
            return dtoMapper.mapProjectToProjectDtoResponse(project);
        } else {
            throw new RuntimeException("Project is not presented");
        }
    }

    @Transactional
    public ProjectDTO discardRequest(RequestToProjectDTO dto) {
        Project project = projectById(dto.getProjectId());
        Optional<User> optionalUser = project.getQueueRequests().stream()
                .filter(user -> user.getId().equals(dto.getRequesterId()))
                .findFirst();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            project.getQueueRequests().remove(user);
            projectRepository.save(project);
            return dtoMapper.mapProjectToProjectDtoResponse(project);
        } else {
            throw new RuntimeException("Project is not presented");
        }
    }

    @Transactional
    public void addRequest(RequestToProjectDTO dto) {
        Project project = projectById(dto.getProjectId());
        User user = userService.getUserById(dto.getRequesterId());
        project.getQueueRequests().add(user);
    }

    private Project projectById(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            throw new RuntimeException("Project is not presented");
        }
    }

    public ProjectDTO getById(Long id) {
        Project project = projectById(id);
        return dtoMapper.mapProjectToProjectDtoResponse(project);
    }

    public List<ProjectDTO> search(String projectName) {
        return projectRepository.findAllByNameContainingIgnoreCase(projectName).stream()
                .map(dtoMapper::mapProjectToProjectDtoResponse)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getByAuthor(Long author) {
        User user = userService.getUserById(author);
        return projectRepository.findAllByAuthor(user).stream()
                .map(dtoMapper::mapProjectToProjectDtoResponse)
                .collect(Collectors.toList());
    }
}
