package med.vol.api.patient.infra;

import med.vol.api.patient.domain.entities.Patient;
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

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private TestEntityManager entityManager;
    private PatientRepositoryStub patientRepositoryStub;

    @BeforeEach
    void setUp() {
        patientRepositoryStub = new PatientRepositoryStub(entityManager);
    }

    @Test
    @DisplayName("Should return patients in database with active true")
    void findAllPatientsWithActiveTrue() {
        patientRepositoryStub.insertPatient(10 , 2);

        Page<Patient> patients = patientRepository.findByActiveTrue(PageRequest.of(0 , 10));

        assertThat(patients.getTotalElements()).isEqualTo(8);
        assertThat(patients.getContent()).allMatch(Patient::getActive);

    }

}
