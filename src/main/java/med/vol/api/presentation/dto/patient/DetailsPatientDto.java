package med.vol.api.presentation.dto.patient;

import med.vol.api.domain.entities.Address;
import med.vol.api.domain.entities.Patient;

public record DetailsPatientDto(Long id, String name, String phone, String email, String document, Address address) {

    public DetailsPatientDto(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getPhone(), patient.getEmail(), patient.getDocument(), patient.getAddress());
    }
}
