package med.vol.api.patient.domain.validations;

import med.vol.api.appointment.domain.validations.schedule.ValidatorScheduleAppointment;
import med.vol.api.appointment.presentation.dto.CreateAppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import med.vol.api.patient.infra.PatientRepository;

@Component
public class PatientIsActiveValidator implements ValidatorScheduleAppointment {
    @Autowired
    private PatientRepository patientRepository;

    public void validate(CreateAppointmentDto createAppointmentDto) {
        Long patientId = createAppointmentDto.patientId();

        Boolean patientIsActive = patientRepository.findActiveById(patientId);
        if (!patientIsActive) {
            throw new ValidationException("Patient is not active");
        }
    }
}
