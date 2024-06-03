package med.vol.api.presentation.dto.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.entities.Specialization;

import java.time.LocalDateTime;

public record CreateAppointmentDto(
        Long doctorId,
        @NotNull
        Long patientId,
        @NotNull
        @Future
        LocalDateTime date,
        Specialization specialization
) {
}
