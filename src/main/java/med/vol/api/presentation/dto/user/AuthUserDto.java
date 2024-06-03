package med.vol.api.presentation.dto.user;

import jakarta.validation.constraints.NotBlank;

public record AuthUserDto(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
