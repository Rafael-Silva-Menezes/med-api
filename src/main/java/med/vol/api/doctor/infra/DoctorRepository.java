package med.vol.api.doctor.infra;

import med.vol.api.doctor.domain.entity.Doctor;
import med.vol.api.doctor.domain.entity.Specialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query("""
                    select d from Doctor d
                    where d.active = true
                    and d.specialization = :specialization
                    and d.id not in (
                    select a.doctor.id from Appointment a
                    where a.date = :date
                    and a.reason is null
            )
            order by rand()
            limit 1
            """)
    Doctor choiceDoctorAvailable(Specialization specialization, LocalDateTime date);

    @Query("""
    select d.active from Doctor d
    where d.id = :id
""")
    Boolean findActiveById(Long id);
}
