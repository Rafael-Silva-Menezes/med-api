package med.vol.api.presentation.dto.doctor;

import med.vol.api.domain.entities.Address;
import med.vol.api.domain.entities.Doctor;
import med.vol.api.domain.entities.Specialization;

public record DetailsDoctorDto(Long id, String name, String phone, String email, String crm, Specialization specialization, Address address) {
    public DetailsDoctorDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(),doctor.getPhone(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialization(), doctor.getAddress());
    }
}
