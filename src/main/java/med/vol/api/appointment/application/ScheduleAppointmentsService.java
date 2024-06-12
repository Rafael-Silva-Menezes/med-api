package med.vol.api.appointment.application;

import med.vol.api.appointment.domain.entity.Appointment;
import med.vol.api.appointment.domain.entity.AppointmentStatus;
import med.vol.api.appointment.domain.validations.schedule.ValidatorScheduleAppointment;
import med.vol.api.doctor.domain.entity.Doctor;
import med.vol.api.patient.domain.entities.Patient;
import med.vol.api.shared.exceptions.ValidateException;
import med.vol.api.appointment.infra.AppointmentRepository;
import med.vol.api.doctor.infra.DoctorRepository;
import med.vol.api.patient.infra.PatientRepository;
import med.vol.api.appointment.presentation.dto.CreateAppointmentDto;
import med.vol.api.appointment.presentation.dto.DetailsAppointmentDto;
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
    private List<ValidatorScheduleAppointment> validatorScheduleAppointments;

    public DetailsAppointmentDto schedule(CreateAppointmentDto createAppointmentDto){
        if(!patientRepository.existsById(createAppointmentDto.patientId())){
            throw new ValidateException("Patient id not found!");
        }

        if(createAppointmentDto.doctorId() != null && !doctorRepository.existsById(createAppointmentDto.doctorId() )){
            throw new ValidateException("Doctor id not found!");
        }


        validatorScheduleAppointments.forEach(validator -> {
            validator.validate(createAppointmentDto);
        });

        Doctor doctor = choiceDoctor(createAppointmentDto);
        Patient patient = patientRepository.getReferenceById(createAppointmentDto.patientId());
        Appointment appointment = new Appointment(
                null,
                doctor,
                patient,
                createAppointmentDto.date().withMinute(0),
                AppointmentStatus.SCHEDULED,
                null
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
