package med.vol.api.domain.validations.patient;

import jakarta.validation.ValidationException;
import med.vol.api.domain.validations.protocols.IValidatorScheduleAppointment;
import med.vol.api.domain.validations.protocols.ValidatorAppointmentData;
import med.vol.api.infra.repositories.AppointmentRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientOverlapAppointmentValidator implements IValidatorScheduleAppointment {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(ValidatorAppointmentData validatorAppointmentData) {
        LocalDateTime appointmentDate = validatorAppointmentData.date();
        Long patientId = validatorAppointmentData.patientId();

        LocalDateTime firstTimeDay = appointmentDate.withHour(7);
        LocalDateTime lastTimeDay = appointmentDate.withHour(18);

        boolean patientOverlapAppointment = appointmentRepository
                .existsByPatientIdAndDateBetween(patientId, firstTimeDay, lastTimeDay);

        if (patientOverlapAppointment) {
            throw new ValidationException("Patient Overlap Appointment");
        }

    }
}
