package med.vol.api.presentation.dto.patient;


import med.vol.api.presentation.dto.address.AddressDto;

public record UpdatePatientDto(
        String name,
        String phone,
        AddressDto address
) {
}
