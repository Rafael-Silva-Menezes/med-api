package med.vol.api.appointment.presentation.dto;

import jakarta.validation.constraints.NotNull;
import med.vol.api.appointment.domain.entity.ReasonCancellationAppointment;

public record CancelAppointmentDto(
        @NotNull
        Long id,
        @NotNull
        ReasonCancellationAppointment reason
) {
}
