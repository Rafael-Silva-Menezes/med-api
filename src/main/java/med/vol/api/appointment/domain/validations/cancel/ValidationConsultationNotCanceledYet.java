package med.vol.api.appointment.domain.validations.cancel;

import med.vol.api.appointment.domain.entity.Appointment;
import med.vol.api.appointment.domain.entity.AppointmentStatus;
import med.vol.api.appointment.infra.AppointmentRepository;
import med.vol.api.appointment.presentation.dto.CancelAppointmentDto;
import med.vol.api.shared.exceptions.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationConsultationNotCanceledYet implements ValidatorCancelAppointment {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(CancelAppointmentDto appointmentDto) {
        Appointment appointment = appointmentRepository.getReferenceById(appointmentDto.id());

        if (appointment.getStatus() == AppointmentStatus.CANCELED) {
            throw new ValidateException("The appointment is now canceled");
        }
    }
}
