package med.vol.api.presentation.dto.appointment;

import med.vol.api.domain.entities.Appointment;

import java.time.LocalDateTime;

public record DetailsAppointmentDto(Long id, Long doctorId, Long patientId, LocalDateTime date) {
    public DetailsAppointmentDto(Appointment schedule) {
        this(schedule.getId(), schedule.getDoctor().getId(), schedule.getPatient().getId(), schedule.getDate());
    }
}
