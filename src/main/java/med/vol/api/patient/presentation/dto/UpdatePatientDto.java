package med.vol.api.patient.presentation.dto;


import med.vol.api.shared.presentation.AddressDto;

public record UpdatePatientDto(
        String name,
        String phone,
        AddressDto address
) {
}
