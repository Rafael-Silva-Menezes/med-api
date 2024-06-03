package med.vol.api.domain.validations.appointment;

import jakarta.validation.ValidationException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import med.vol.api.domain.validations.protocols.IValidatorScheduleAppointment;
import med.vol.api.domain.validations.protocols.ValidatorAppointmentData;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTimeValidator  implements IValidatorScheduleAppointment {
    public void validate(ValidatorAppointmentData validatorAppointmentData) {
        LocalDateTime appointmentDate = validatorAppointmentData.date();
        Boolean sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean startScheduleTime = appointmentDate.getHour() < 7;
        Boolean endScheduleTime = appointmentDate.getHour() > 18;

        if (sunday || startScheduleTime || endScheduleTime) {
            throw new ValidationException("Schedule outside opening hours");
        }
    }
}
