package med.vol.api.patient.domain.validations;

import jakarta.validation.ValidationException;
import med.vol.api.appointment.domain.validations.schedule.ValidatorScheduleAppointment;
import med.vol.api.appointment.infra.AppointmentRepository;

import java.time.LocalDateTime;

import med.vol.api.appointment.presentation.dto.CreateAppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientOverlapAppointmentValidator implements ValidatorScheduleAppointment {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(CreateAppointmentDto createAppointmentDto) {
        LocalDateTime appointmentDate = createAppointmentDto.date();
        Long patientId = createAppointmentDto.patientId();

        LocalDateTime firstTimeDay = appointmentDate.withHour(7);
        LocalDateTime lastTimeDay = appointmentDate.withHour(18);

        boolean patientOverlapAppointment = appointmentRepository
                .existsByPatientIdAndDateBetween(patientId, firstTimeDay, lastTimeDay);

        if (patientOverlapAppointment) {
            throw new ValidationException("Patient Overlap Appointment");
        }

    }
}
