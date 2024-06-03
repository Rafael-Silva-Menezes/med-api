package med.vol.api.presentation.dto.patient;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.presentation.dto.address.AddressDto;

public record CreatePatientDto(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Phone cannot be blank")
        String phone,
        @NotBlank(message = "Document cannot be blank")
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}", message = "Invalid document format")
        String document,
        @NotNull(message = "Address cannot be null")
        @Valid
        AddressDto address
) {
}
