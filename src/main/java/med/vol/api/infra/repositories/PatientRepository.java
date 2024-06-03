package med.vol.api.infra.repositories;

import med.vol.api.domain.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("""
    select p.active from Patient p
    where p.id = :id
""")
    Boolean findActiveById(Long id);
}
