package med.vol.api.patient.infra;

import med.vol.api.patient.domain.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByActiveTrue(Pageable pageable);
}
