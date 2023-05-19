package com.example.projectHub.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProjectDTO {
    @NotNull
    private Long id;

    @NotNull
    private Float budget;

    @NotNull
    private String name;

    @NotNull
    private String description;
}