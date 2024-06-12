package med.vol.api.appointment.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.doctor.domain.entity.Doctor;
import med.vol.api.patient.domain.entities.Patient;

import java.time.LocalDateTime;

@Table(name="appointments")
@Entity(name="Appointment")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    @Enumerated(EnumType.STRING)
    private ReasonCancellationAppointment reason;

    public void cancel(ReasonCancellationAppointment reason){
        status = AppointmentStatus.CANCELED;
        this.reason =  reason;
    }
}
