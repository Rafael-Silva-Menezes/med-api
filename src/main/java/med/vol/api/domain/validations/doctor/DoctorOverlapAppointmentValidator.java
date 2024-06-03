package med.vol.api.domain.validations.doctor;

import jakarta.validation.ValidationException;
import med.vol.api.domain.validations.protocols.IValidatorScheduleAppointment;
import med.vol.api.domain.validations.protocols.ValidatorAppointmentData;
import med.vol.api.infra.repositories.AppointmentRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorOverlapAppointmentValidator implements IValidatorScheduleAppointment {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(ValidatorAppointmentData validatorAppointmentData) {
        Long doctorId = validatorAppointmentData.doctorId();
        LocalDateTime appointmentDate = validatorAppointmentData.date();

        var doctorAlreadyAppointment = appointmentRepository
                .existsByDoctorIdAndDate(doctorId, appointmentDate);

        if (doctorAlreadyAppointment) {
            throw new ValidationException("Doctor already has an appointment");
        }
    }
}
