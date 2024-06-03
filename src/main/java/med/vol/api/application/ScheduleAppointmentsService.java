package med.vol.api.application;

import med.vol.api.domain.entities.Appointment;
import med.vol.api.domain.entities.Doctor;
import med.vol.api.domain.entities.Patient;
import med.vol.api.domain.validations.protocols.IValidatorScheduleAppointment;
import med.vol.api.domain.validations.protocols.ValidatorAppointmentData;
import med.vol.api.infra.exceptions.ValidateException;
import med.vol.api.infra.repositories.AppointmentRepository;
import med.vol.api.infra.repositories.DoctorRepository;
import med.vol.api.infra.repositories.PatientRepository;
import med.vol.api.presentation.dto.appointment.CreateAppointmentDto;
import med.vol.api.presentation.dto.appointment.DetailsAppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleAppointmentsService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private List<IValidatorScheduleAppointment> validatorScheduleAppointments;

    public DetailsAppointmentDto schedule(CreateAppointmentDto appointmentData){
        if(!patientRepository.existsById(appointmentData.patientId())){
            throw new ValidateException("Patient id not found!");
        }

        if(appointmentData.doctorId() != null && !doctorRepository.existsById(appointmentData.doctorId() )){
            throw new ValidateException("Doctor id not found!");
        }

        ValidatorAppointmentData validatorAppointmentData = ValidatorAppointmentData.from(appointmentData);

        validatorScheduleAppointments.forEach(validator -> {
            validator.validate(validatorAppointmentData);
        });

        Doctor doctor = choiceDoctor(appointmentData);
        Patient patient = patientRepository.getReferenceById(appointmentData.patientId());
        Appointment appointment = new Appointment(
                null,
                doctor,
                patient,
                appointmentData.date().withMinute(0)
        );
        appointmentRepository.save(appointment);

        return new DetailsAppointmentDto(appointment);
    }

    private Doctor choiceDoctor(CreateAppointmentDto appointmentData){
        if(appointmentData.doctorId() != null){
            return doctorRepository.getReferenceById(appointmentData.doctorId());
        }

        if(appointmentData.specialization() == null){
            throw new ValidateException("Specialization is required, when doctorId is null");
        }

        return doctorRepository.choiceDoctorAvailable(appointmentData.specialization(), appointmentData.date());
    }
}
