package med.vol.api.domain.validations.appointment;

import jakarta.validation.ValidationException;

import java.time.Duration;
import java.time.LocalDateTime;

import med.vol.api.domain.validations.protocols.IValidatorScheduleAppointment;
import med.vol.api.domain.validations.protocols.ValidatorAppointmentData;
import org.springframework.stereotype.Component;

@Component
public class AppointmentAdvanceValidator implements IValidatorScheduleAppointment {
    public void validate(ValidatorAppointmentData validatorAppointmentData) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appointmentDate = validatorAppointmentData.date();
        long differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (differenceInMinutes < 30) {
            throw new ValidationException(
                    "Appointments must be made at least 30 minutes before the appointment");
        }
    }
}
