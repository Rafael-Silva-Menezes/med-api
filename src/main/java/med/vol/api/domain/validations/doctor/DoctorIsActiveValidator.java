package med.vol.api.domain.validations.doctor;

import jakarta.validation.ValidationException;
import med.vol.api.domain.validations.protocols.IValidatorScheduleAppointment;
import med.vol.api.domain.validations.protocols.ValidatorAppointmentData;
import med.vol.api.infra.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorIsActiveValidator implements IValidatorScheduleAppointment {
    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(ValidatorAppointmentData validatorAppointmentData) {
        Long doctorId = validatorAppointmentData.doctorId();

        if (doctorId == null) {
            return;
        }
        Boolean doctorIsActive = doctorRepository.findActiveById(doctorId);
        if (!doctorIsActive) {
            throw new ValidationException("Doctor is not active");
        }

    }
}
