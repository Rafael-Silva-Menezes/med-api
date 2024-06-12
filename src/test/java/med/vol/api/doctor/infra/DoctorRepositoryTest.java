package med.vol.api.doctor.infra;

import med.vol.api.doctor.domain.entity.Doctor;
import med.vol.api.doctor.domain.entity.Specialization;
import med.vol.api.doctor.presentation.dto.CreateDoctorDto;
import med.vol.api.doctor.presentation.dto.ListDoctorDto;
import med.vol.api.shared.presentation.AddressDto;
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


    @Test
    @DisplayName("Should be return all actives doctors in database")
    void findAllByActiveTrue() {
        insertDoctor("Dr. João Silva" , "joao_cardiologia@gmail.com" , "4564545464" , "989820" , Specialization.CARDIOLOGY);
        insertDoctor("Dr. Augusto Lima" , "augusto_orotpedia@gmail.com" , "787878787" , "544554" , Specialization.ORTHOPEDICS);

        Page<ListDoctorDto> doctors = doctorRepository.findAllByActiveTrue(null).map(ListDoctorDto::new);

        assertThat(doctors.getTotalElements()).isEqualTo(2);
    }

    @Test
    void choiceDoctorAvailable() {
    }

    @Test
    void findActiveById() {
    }

    private void insertDoctor(String name , String email , String phone , String crm , Specialization specialization) {
        CreateDoctorDto createDoctorDto = new CreateDoctorDto(name , email  , phone, crm , specialization , createAddressDto());
        Doctor doctor = new Doctor(createDoctorDto);
        System.out.println(doctor);
        entityManager.persist(doctor);
    }


    private AddressDto createAddressDto() {
        return new AddressDto("Rua A" , "Jardim das Flores" , "34600-000" , "Campinas" , "Sao Paulo" , "34521" , "No Centro da cidade");
    }
}