package med.vol.api.presentation.dto.doctor;

import med.vol.api.domain.entities.Doctor;
import med.vol.api.domain.entities.Specialization;

public record ListDoctorDto(
        Long id,
        String name,
        String email,
        String crm,
        Specialization specialization
) {

   public ListDoctorDto(Doctor doctor){
       this(
               doctor.getId(),
               doctor.getName(),
               doctor.getEmail(),
               doctor.getCrm(),
               doctor.getSpecialization()
       );
   }
}
