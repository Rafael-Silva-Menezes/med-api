package med.vol.api.appointment.infra;

import med.vol.api.appointment.domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByDoctorIdAndDateAndReasonIsNull(Long doctorId , LocalDateTime date);

    Boolean existsByPatientIdAndDateBetween(Long patientId , LocalDateTime firstTimeDay , LocalDateTime lastTimeDay);
}
