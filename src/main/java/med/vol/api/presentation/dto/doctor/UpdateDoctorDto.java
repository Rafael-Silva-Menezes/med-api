package med.vol.api.presentation.dto.doctor;

import med.vol.api.presentation.dto.address.AddressDto;

public record UpdateDoctorDto(
        String name,
        String phone,
        AddressDto address
) {
}
