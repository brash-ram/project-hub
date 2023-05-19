package com.example.projectHub.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRegisterDTORequest {
    @NotNull
    private Long userId;

    @NotNull
    private Float budget;

    @NotNull
    private String name;

    @NotNull
    private String description;
}
