package med.vol.api.doctor.infra;

import med.vol.api.doctor.domain.entity.Doctor;
import med.vol.api.doctor.domain.entity.Specialization;
import med.vol.api.doctor.presentation.dto.CreateDoctorDto;
import med.vol.api.shared.domain.AddressStub;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class DoctorRepositoryStub {

    private final TestEntityManager entityManager;
    private final Faker faker;
    private final AddressStub addressStub;
    
    public DoctorRepositoryStub(TestEntityManager entityManager) {
        this.entityManager = entityManager;
        this.faker = new Faker();
        this.addressStub = new AddressStub();
    }

    public void insertDoctor(Specialization specialization , int count , int inactiveCount) {
        List<Doctor> doctors = new ArrayList<>();
        IntStream.range(0 , count).forEach(i -> {
            CreateDoctorDto doctorDto = createDoctorDto(specialization);
            doctors.add(new Doctor(doctorDto));
        });

        Collections.shuffle(doctors);
        doctors.stream().limit(inactiveCount).forEach(Doctor::toBeInactive);
        doctors.forEach(entityManager::persist);
    }

    public Doctor insertDoctor(Specialization specialization) {
        Doctor doctor = new Doctor(createDoctorDto(specialization));
        entityManager.persist(doctor);
        return doctor;
    }


    private CreateDoctorDto createDoctorDto(Specialization specialization) {
        return Instancio.of(CreateDoctorDto.class)
                .set(Select.field(CreateDoctorDto.class , "specialization") , specialization)
                .set(Select.field(CreateDoctorDto.class , "crm") , faker.number().digits(6))
                .set(Select.field(CreateDoctorDto.class , "address") , addressStub.createAddressDto())
                .set(Select.field(CreateDoctorDto.class , "name") , faker.name().fullName())
                .set(Select.field(CreateDoctorDto.class , "email") , faker.internet().emailAddress())
                .set(Select.field(CreateDoctorDto.class , "phone") , faker.phoneNumber().phoneNumber())
                .create();
    }
}
