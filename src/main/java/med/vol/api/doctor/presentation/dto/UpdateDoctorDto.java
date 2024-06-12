package med.vol.api.doctor.presentation.dto;

import med.vol.api.shared.presentation.AddressDto;

public record UpdateDoctorDto(
        String name,
        String phone,
        AddressDto address
) {
}
