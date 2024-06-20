package med.vol.api.appointment.infra;

import med.vol.api.appointment.domain.entity.Appointment;
import med.vol.api.appointment.domain.entity.AppointmentStatus;
import med.vol.api.appointment.domain.entity.ReasonCancellationAppointment;
import med.vol.api.doctor.domain.entity.Doctor;
import med.vol.api.patient.domain.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

public class AppointmentRepositoryStub {
    private static final Logger log = LoggerFactory.getLogger(AppointmentRepositoryStub.class);
    private final TestEntityManager entityManager;

    public AppointmentRepositoryStub(TestEntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void insertAppointment(Doctor doctor , Patient patient , LocalDateTime date , AppointmentStatus status) {
        Appointment appointment = new Appointment(null , doctor , patient , date , status , null);
        entityManager.persist(appointment);
    }

    public void insertAppointment(Doctor doctor , Patient patient , LocalDateTime date , AppointmentStatus status , ReasonCancellationAppointment reason) {
        Appointment appointment = new Appointment(null , doctor , patient , date , status , reason);
        entityManager.persist(appointment);
    }
}
