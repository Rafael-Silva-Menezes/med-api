package med.vol.api.domain.validations.protocols;

import med.vol.api.presentation.dto.appointment.CreateAppointmentDto;

public interface IValidatorScheduleAppointment {
    void validate(ValidatorAppointmentData validatorAppointmentData);
}
