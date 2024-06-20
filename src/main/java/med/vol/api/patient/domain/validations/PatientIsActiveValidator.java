package med.vol.api.patient.domain.validations;

import jakarta.validation.ValidationException;
import med.vol.api.appointment.domain.validations.schedule.ValidatorScheduleAppointment;
import med.vol.api.appointment.presentation.dto.CreateAppointmentDto;
import med.vol.api.patient.domain.entities.Patient;
import med.vol.api.patient.infra.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientIsActiveValidator implements ValidatorScheduleAppointment {
    @Autowired
    private PatientRepository patientRepository;

    public void validate(CreateAppointmentDto appointment) {
        Patient patient = patientRepository.getReferenceById(appointment.patientId());
        if (!patient.getActive()) throw new ValidationException("Patient is not active");
    }
}
