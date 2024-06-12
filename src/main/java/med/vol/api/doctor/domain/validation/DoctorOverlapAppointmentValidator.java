package med.vol.api.doctor.domain.validation;

import jakarta.validation.ValidationException;
import med.vol.api.appointment.domain.validations.schedule.ValidatorScheduleAppointment;
import med.vol.api.appointment.infra.AppointmentRepository;

import java.time.LocalDateTime;

import med.vol.api.appointment.presentation.dto.CreateAppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorOverlapAppointmentValidator implements ValidatorScheduleAppointment {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(CreateAppointmentDto createAppointmentDto) {
        Long doctorId = createAppointmentDto.doctorId();
        LocalDateTime appointmentDate = createAppointmentDto.date();

        var doctorAlreadyAppointment = appointmentRepository
                .existsByDoctorIdAndDateAndReasonIsNull(doctorId, appointmentDate);

        if (doctorAlreadyAppointment) {
            throw new ValidationException("Doctor already has an appointment");
        }
    }
}
