package dev.tuchanski.ms.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @Size(min = 2, max = 40) @NotBlank String name,
        @Email @NotBlank String email
) {
}
