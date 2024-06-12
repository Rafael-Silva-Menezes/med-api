package med.vol.api.doctor.presentation.dto;

import med.vol.api.doctor.domain.entity.Doctor;
import med.vol.api.doctor.domain.entity.Specialization;

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
