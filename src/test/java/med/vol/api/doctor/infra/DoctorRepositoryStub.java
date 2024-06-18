package med.vol.api.doctor.infra;

import med.vol.api.doctor.domain.entity.Doctor;
import med.vol.api.doctor.domain.entity.Specialization;
import med.vol.api.doctor.presentation.dto.CreateDoctorDto;
import med.vol.api.shared.presentation.AddressDto;
import org.instancio.Instancio;
import org.instancio.Select;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import net.datafaker.Faker;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class DoctorRepositoryStub {

    private final TestEntityManager entityManager;
    private final Faker faker;

    public DoctorRepositoryStub(TestEntityManager entityManager) {
        this.entityManager = entityManager;
        this.faker = new Faker();
    }

    public void insertDoctor(Specialization specialization, int count, int inactiveCount) {
        List<Doctor> doctors = IntStream.range(0, count).mapToObj(i -> {
            CreateDoctorDto createDoctorDto = Instancio.of(CreateDoctorDto.class)
                    .set(Select.field(CreateDoctorDto.class, "specialization"), specialization)
                    .set(Select.field(CreateDoctorDto.class, "crm"), faker.number().digits(6))
                    .set(Select.field(CreateDoctorDto.class, "address"), createAddressDto())
                    .set(Select.field(CreateDoctorDto.class, "name"), faker.name().fullName())
                    .set(Select.field(CreateDoctorDto.class, "email"), faker.internet().emailAddress())
                    .set(Select.field(CreateDoctorDto.class, "phone"), faker.phoneNumber().phoneNumber())
                    .create();
            return new Doctor(createDoctorDto);
        }).toList();

        // Randomly select inactiveCount doctors to be inactive
        Collections.shuffle(doctors);
        doctors.stream().limit(inactiveCount).forEach(doctor -> doctor.toBeInactive());

        // Persist all doctors
        doctors.forEach(entityManager::persist);
    }

    private AddressDto createAddressDto() {
        return new AddressDto(
                faker.address().streetAddress(),
                faker.address().streetName(),
                faker.address().zipCode().replaceAll("-", ""),
                faker.address().city(),
                faker.address().stateAbbr(),
                faker.address().buildingNumber(),
                faker.address().secondaryAddress()
        );
    }


    public void insertPatient() {
        // Implement the method to insert a patient
    }

    public void insertConsultation(Long doctorId, Long patientId, String consultationDate) {
        // Implement the method to insert a consultation
    }
}
