package med.vol.api.user.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthUserDto(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
