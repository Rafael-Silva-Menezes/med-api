package med.vol.api.appointment.domain.validations.schedule;

import med.vol.api.appointment.presentation.dto.CreateAppointmentDto;

public interface ValidatorScheduleAppointment {
    void validate(CreateAppointmentDto createAppointmentDto);
}
