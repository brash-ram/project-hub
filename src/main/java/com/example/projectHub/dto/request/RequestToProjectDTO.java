package com.example.projectHub.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestToProjectDTO {
    @NotNull
    private Long projectId;

    @NotNull
    private Long requesterId;
}
