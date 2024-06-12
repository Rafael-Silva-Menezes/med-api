package med.vol.api.appointment.domain.validations.cancel;

import med.vol.api.appointment.presentation.dto.CancelAppointmentDto;

public interface ValidatorCancelAppointment {
    void validate(CancelAppointmentDto cancelAppointmentDto);
}
