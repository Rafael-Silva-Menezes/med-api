package med.vol.api.appointment.domain.validations.schedule;

import jakarta.validation.ValidationException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import med.vol.api.appointment.presentation.dto.CreateAppointmentDto;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTimeValidator  implements ValidatorScheduleAppointment {
    public void validate(CreateAppointmentDto createAppointmentDto) {
        LocalDateTime appointmentDate = createAppointmentDto.date();
        Boolean sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean startScheduleTime = appointmentDate.getHour() < 7;
        Boolean endScheduleTime = appointmentDate.getHour() > 18;

        if (sunday || startScheduleTime || endScheduleTime) {
            throw new ValidationException("Schedule outside opening hours");
        }
    }
}
