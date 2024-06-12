package med.vol.api.doctor.domain.validation;

import jakarta.validation.ValidationException;
import med.vol.api.appointment.domain.validations.schedule.ValidatorScheduleAppointment;
import med.vol.api.doctor.infra.DoctorRepository;
import med.vol.api.appointment.presentation.dto.CreateAppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorIsActiveValidator implements ValidatorScheduleAppointment {
    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(CreateAppointmentDto createAppointmentDto) {
        Long doctorId = createAppointmentDto.doctorId();

        if (doctorId == null) {
            return;
        }
        Boolean doctorIsActive = doctorRepository.findActiveById(doctorId);
        if (!doctorIsActive) {
            throw new ValidationException("Doctor is not active");
        }

    }
}
