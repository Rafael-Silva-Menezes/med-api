package med.vol.api.patient.presentation.dto;

import med.vol.api.shared.domain.Address;
import med.vol.api.patient.domain.entities.Patient;

public record DetailsPatientDto(Long id, String name, String phone, String email, String document, Address address) {

    public DetailsPatientDto(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getPhone(), patient.getEmail(), patient.getDocument(), patient.getAddress());
    }
}
