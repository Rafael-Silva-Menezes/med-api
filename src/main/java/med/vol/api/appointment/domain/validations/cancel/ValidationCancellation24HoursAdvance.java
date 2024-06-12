package med.vol.api.appointment.domain.validations.cancel;

import med.vol.api.appointment.domain.entity.Appointment;
import med.vol.api.appointment.infra.AppointmentRepository;
import med.vol.api.appointment.presentation.dto.CancelAppointmentDto;
import med.vol.api.shared.exceptions.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidationCancellation24HoursAdvance implements ValidatorCancelAppointment {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(CancelAppointmentDto appointmentDto) {
        Appointment appointment = appointmentRepository.getReferenceById(appointmentDto.id());

        if (LocalDateTime.now().plusHours(24).isAfter(appointment.getDate())) {
            throw new ValidateException("Appointments can only be canceled at least 24 hours in advance");
        }
    }
}
