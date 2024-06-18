package med.vol.api.doctor.infra;

import med.vol.api.doctor.domain.entity.Specialization;
import med.vol.api.doctor.presentation.dto.ListDoctorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager entityManager;

    private DoctorRepositoryStub doctorRepositoryStub;

    @BeforeEach
    void setUp() {
        doctorRepositoryStub = new DoctorRepositoryStub(entityManager);
    }

    @Test
    @DisplayName("Should be return doctors in database with active true")
    void findAllByActiveTrue() {
        doctorRepositoryStub.insertDoctor(Specialization.CARDIOLOGY, 2,1);
        doctorRepositoryStub.insertDoctor(Specialization.ORTHOPEDICS, 3,1);

        Page<ListDoctorDto> doctors = doctorRepository
                .findAllByActiveTrue(null)
                .map(ListDoctorDto::new);
        assertThat(doctors.getTotalElements()).isEqualTo(3);
    }

    @Test
    void choiceDoctorAvailable() {

    }

    @Test
    void findActiveById() {
    }
}