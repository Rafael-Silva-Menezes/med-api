package med.vol.api.patient.infra;

import med.vol.api.patient.domain.entities.Patient;
import med.vol.api.patient.presentation.dto.CreatePatientDto;
import med.vol.api.shared.domain.AddressStub;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class PatientRepositoryStub {

    private final TestEntityManager entityManager;
    private final Faker faker;
    private final AddressStub addressStub;

    public PatientRepositoryStub(TestEntityManager entityManager) {
        this.entityManager = entityManager;
        this.faker = new Faker();
        this.addressStub = new AddressStub();
    }

    public void insertPatient(int count , int inactiveCount) {
        List<Patient> patients = new ArrayList<>();
        IntStream.range(0 , count).forEach(i -> {
            CreatePatientDto patientDto = createPatientDto();
            patients.add(new Patient(patientDto));
        });

        Collections.shuffle(patients);
        patients.stream().limit(inactiveCount).forEach(Patient::toBeInactive);
        patients.forEach(entityManager::persist);
    }

    public Patient insertPatient() {
        Patient patient = new Patient(createPatientDto());
        entityManager.persist(patient);
        return patient;
    }

    private CreatePatientDto createPatientDto() {
        return Instancio.of(CreatePatientDto.class)
                .set(Select.field(CreatePatientDto.class , "name") , faker.name().fullName())
                .set(Select.field(CreatePatientDto.class , "email") , faker.internet().emailAddress())
                .set(Select.field(CreatePatientDto.class , "phone") , faker.phoneNumber().phoneNumber())
                .set(Select.field(CreatePatientDto.class , "document") , faker.regexify("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}"))
                .set(Select.field(CreatePatientDto.class , "address") , addressStub.createAddressDto())
                .create();
    }
}
