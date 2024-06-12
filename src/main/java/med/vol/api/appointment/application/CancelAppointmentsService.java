package med.vol.api.appointment.application;

import jakarta.validation.ValidationException;
import med.vol.api.appointment.domain.entity.Appointment;
import med.vol.api.appointment.domain.validations.cancel.ValidatorCancelAppointment;
import med.vol.api.appointment.infra.AppointmentRepository;
import med.vol.api.appointment.presentation.dto.CancelAppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelAppointmentsService  {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private List<ValidatorCancelAppointment> validatorCancelAppointments;

    public void cancel(CancelAppointmentDto cancelAppointmentDto) {
            if (!appointmentRepository.existsById(cancelAppointmentDto.id())){
                throw new ValidationException("Appointment with id " + cancelAppointmentDto.id() + " does not exist");
            }

            validatorCancelAppointments.forEach(v -> v.validate(cancelAppointmentDto));

            Appointment appointment = appointmentRepository.getReferenceById(cancelAppointmentDto.id());

            appointment.cancel(cancelAppointmentDto.reason());
    }
}
