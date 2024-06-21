package med.vol.api.appointment.presentation.dto;

import jakarta.validation.constraints.NotNull;
import med.vol.api.doctor.domain.entity.Specialization;

import java.time.LocalDateTime;

public record CreateAppointmentDto(
        Long doctorId ,
        @NotNull
        Long patientId ,
        @NotNull
        LocalDateTime date ,
        Specialization specialization
) {
}
