package com.example.projectHub.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequesterDTOResponse {
    @NotNull
    private Long id;

    @NotNull
    private String fullName;
}
