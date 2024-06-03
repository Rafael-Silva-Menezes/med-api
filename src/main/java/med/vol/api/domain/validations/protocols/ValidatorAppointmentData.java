package med.vol.api.domain.validations.protocols;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.entities.Specialization;
import med.vol.api.presentation.dto.appointment.CreateAppointmentDto;

import java.time.LocalDateTime;

public record ValidatorAppointmentData(
        Long doctorId,
        @NotNull
        Long patientId,
        @NotNull
        @Future
        LocalDateTime date,
        Specialization specialization
) {
        public static ValidatorAppointmentData from(CreateAppointmentDto dto) {
                return new ValidatorAppointmentData(
                        dto.doctorId(),
                        dto.patientId(),
                        dto.date(),
                        dto.specialization()
                );
        }
}
