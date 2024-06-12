package med.vol.api.shared.presentation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDto(
        @NotBlank(message = "Street cannot be blank")
        String street,
        @NotBlank(message = "Neighborhood cannot be blank")
        String neighborhood,
        @NotBlank(message = "Zip code cannot be blank")
        @Pattern(regexp = "\\d{8}", message = "Zip code must be a number with 8 digits")
        String zipCode,
        @NotBlank(message = "City cannot be blank")
        String city,
        @NotBlank(message = "State cannot be blank")
        String state,
        String number,
        String complement
) {
}
