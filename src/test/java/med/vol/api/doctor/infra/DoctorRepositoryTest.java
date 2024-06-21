package med.vol.api.doctor.infra;

import med.vol.api.appointment.domain.entity.AppointmentStatus;
import med.vol.api.appointment.domain.entity.ReasonCancellationAppointment;
import med.vol.api.appointment.infra.AppointmentRepositoryStub;
import med.vol.api.doctor.domain.entity.Doctor;
import med.vol.api.doctor.domain.entity.Specialization;
import med.vol.api.patient.domain.entities.Patient;
import med.vol.api.patient.infra.PatientRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    private final LocalDateTime scheduleDateOnMonday10Am = LocalDate.now()
            .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
            .atTime(10 , 0);
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private TestEntityManager entityManager;
    private DoctorRepositoryStub doctorRepositoryStub;
    private PatientRepositoryStub patientRepositoryStub;
    private AppointmentRepositoryStub appointmentRepositoryStub;

    @BeforeEach
    void setUp() {
        doctorRepositoryStub = new DoctorRepositoryStub(entityManager);
        patientRepositoryStub = new PatientRepositoryStub(entityManager);
        appointmentRepositoryStub = new AppointmentRepositoryStub(entityManager);

    }

    @Test
    @DisplayName("Should return all active doctors from the database")
    void shouldReturnAllActiveDoctors() {
        doctorRepositoryStub.insertDoctor(Specialization.CARDIOLOGY , 5 , 1);

        Page<Doctor> doctors = doctorRepository.findByActiveTrue(PageRequest.of(0 , 10));
        assertThat(doctors.getTotalElements()).isEqualTo(4);
        assertThat(doctors.getContent()).allMatch(Doctor::getActive);
    }

    @Test
    @DisplayName("Should return null if no doctor is available at the chosen date and time")
    void shouldReturnNullIfNoDoctorAvailableAtChosenDateTime() {
        Doctor doctor = doctorRepositoryStub.insertDoctor(Specialization.CARDIOLOGY);
        Patient patient = patientRepositoryStub.insertPatient();
        appointmentRepositoryStub
                .insertAppointment(doctor , patient , scheduleDateOnMonday10Am , AppointmentStatus.SCHEDULED);

        Doctor chosenDoctor = doctorRepository.choiceDoctorAvailable(Specialization.CARDIOLOGY , scheduleDateOnMonday10Am);
        assertThat(chosenDoctor).isNull();
    }

    @Test
    @DisplayName("Should return an available doctor if one exists at the chosen date and time")
    void shouldReturnAvailableDoctorAtChosenDateTime() {
        Doctor doctor = doctorRepositoryStub.insertDoctor(Specialization.CARDIOLOGY);
        Doctor chosenDoctor = doctorRepository.choiceDoctorAvailable(Specialization.CARDIOLOGY , scheduleDateOnMonday10Am);
        assertThat(chosenDoctor).isEqualTo(doctor);
    }

    @Test
    @DisplayName("Should return doctor 2 when available at the chosen date and time")
    void shouldReturnDoctor2WhenAvailableAtChosenDateTime() {
        Doctor doctor1 = doctorRepositoryStub.insertDoctor(Specialization.CARDIOLOGY);
        Doctor doctor2 = doctorRepositoryStub.insertDoctor(Specialization.CARDIOLOGY);

        Patient patient = patientRepositoryStub.insertPatient();
        appointmentRepositoryStub
                .insertAppointment(doctor1 , patient , scheduleDateOnMonday10Am , AppointmentStatus.SCHEDULED);

        Doctor chosenDoctor = doctorRepository.choiceDoctorAvailable(Specialization.CARDIOLOGY , scheduleDateOnMonday10Am);
        assertThat(chosenDoctor).isEqualTo(doctor2);
    }

    @Test
    @DisplayName("Should return the doctor if the appointment at the time is canceled")
    void shouldReturnDoctorIfAppointmentCanceledAtTime() {
        Doctor doctor = doctorRepositoryStub.insertDoctor(Specialization.CARDIOLOGY);

        Patient patient = patientRepositoryStub.insertPatient();
        appointmentRepositoryStub
                .insertAppointment(doctor , patient , scheduleDateOnMonday10Am ,
                        AppointmentStatus.CANCELED , ReasonCancellationAppointment.PATIENT_GIVE_UP);

        Doctor chosenDoctor = doctorRepository.choiceDoctorAvailable(Specialization.CARDIOLOGY , scheduleDateOnMonday10Am);
        assertThat(chosenDoctor).isEqualTo(doctor);
    }

}