package med.vol.api.doctor.presentation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.shared.presentation.AddressDto;
import med.vol.api.doctor.domain.entity.Specialization;

public record CreateDoctorDto(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Phone cannot be blank")
        String phone,
        @NotBlank(message = "CRM cannot be blank")
        @Pattern(regexp = "\\d{4,6}", message = "CRM must be a number with 4 to 6 digits")
        String crm,
        @NotNull(message = "Specialization cannot be null")
        Specialization specialization,
        @NotNull(message = "Address cannot be null")
        @Valid
        AddressDto address
) {
    @JsonCreator
    public static CreateDoctorDto fromJson(String name, String email, String phone, String crm, String specialization, AddressDto address) {
        return new CreateDoctorDto(name, email, phone, crm, Specialization.fromString(specialization), address);
    }
}


