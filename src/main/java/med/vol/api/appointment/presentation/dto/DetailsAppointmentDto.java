package med.vol.api.appointment.presentation.dto;

import med.vol.api.appointment.domain.entity.Appointment;

import java.time.LocalDateTime;

public record DetailsAppointmentDto(Long id, Long doctorId, Long patientId, LocalDateTime date) {
    public DetailsAppointmentDto(Appointment schedule) {
        this(schedule.getId(), schedule.getDoctor().getId(), schedule.getPatient().getId(), schedule.getDate());
    }
}
