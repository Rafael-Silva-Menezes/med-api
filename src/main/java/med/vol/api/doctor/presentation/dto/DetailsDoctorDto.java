package med.vol.api.doctor.presentation.dto;

import med.vol.api.shared.domain.Address;
import med.vol.api.doctor.domain.entity.Doctor;
import med.vol.api.doctor.domain.entity.Specialization;

public record DetailsDoctorDto(Long id, String name, String phone, String email, String crm, Specialization specialization, Address address) {
    public DetailsDoctorDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(),doctor.getPhone(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialization(), doctor.getAddress());
    }
}
