package med.vol.api.appointment.domain.validations.schedule;

import jakarta.validation.ValidationException;

import java.time.Duration;
import java.time.LocalDateTime;

import med.vol.api.appointment.presentation.dto.CreateAppointmentDto;
import org.springframework.stereotype.Component;

@Component
public class AppointmentAdvanceValidator implements ValidatorScheduleAppointment {
    public void validate(CreateAppointmentDto createAppointmentDto) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appointmentDate = createAppointmentDto.date();
        long differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (differenceInMinutes < 30) {
            throw new ValidationException(
                    "Appointments must be made at least 30 minutes before the appointment");
        }
    }
}
