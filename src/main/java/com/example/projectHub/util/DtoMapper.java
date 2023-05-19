package com.example.projectHub.util;

import com.example.projectHub.data.entity.Project;
import com.example.projectHub.data.entity.User;
import com.example.projectHub.dto.ProjectDTO;
import com.example.projectHub.dto.UserDTO;
import com.example.projectHub.dto.request.ProjectRegisterDTORequest;
import com.example.projectHub.dto.response.UserRequesterDTOResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoMapper {

    private final ModelMapper modelMapper;

    public ProjectDTO mapProjectToProjectDtoResponse(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    public Project mapProjectDtoResponseToProject(ProjectDTO dto) {
        return modelMapper.map(dto, Project.class);
    }

    public UserRequesterDTOResponse mapUserToUserRequesterDTOResponse(User user) {
        return modelMapper.map(user, UserRequesterDTOResponse.class);
    }

    public UserDTO mapUserToUserDTOResponse(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public Project mapProjectRegisterDTORequestToProject(ProjectRegisterDTORequest dto) {
        return modelMapper.map(dto, Project.class);
    }
}
