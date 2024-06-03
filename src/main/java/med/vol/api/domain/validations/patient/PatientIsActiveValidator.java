package med.vol.api.domain.validations.patient;

import med.vol.api.domain.validations.protocols.IValidatorScheduleAppointment;
import med.vol.api.domain.validations.protocols.ValidatorAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import med.vol.api.infra.repositories.PatientRepository;

@Component
public class PatientIsActiveValidator implements IValidatorScheduleAppointment {
    @Autowired
    private PatientRepository patientRepository;

    public void validate(ValidatorAppointmentData validatorAppointmentData) {
        Long patientId = validatorAppointmentData.patientId();

        Boolean patientIsActive = patientRepository.findActiveById(patientId);
        if (!patientIsActive) {
            throw new ValidationException("Patient is not active");
        }
    }
}
