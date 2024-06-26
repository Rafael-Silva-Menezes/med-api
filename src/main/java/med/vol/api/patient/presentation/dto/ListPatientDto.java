package med.vol.api.patient.presentation.dto;

import med.vol.api.patient.domain.entities.Patient;

public record ListPatientDto(
        Long id,
         String name,
         String email,
         String phone,
         String document
) {
    public ListPatientDto(Patient patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getDocument()
        );
    }
}
