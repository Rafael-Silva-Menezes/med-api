package med.vol.api.presentation.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.presentation.dto.doctor.CreateDoctorDto;
import med.vol.api.presentation.dto.doctor.DetailsDoctorDto;
import med.vol.api.presentation.dto.doctor.ListDoctorDto;
import med.vol.api.presentation.dto.doctor.UpdateDoctorDto;
import med.vol.api.domain.entities.Doctor;
import med.vol.api.infra.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity createDoctors(@RequestBody @Valid CreateDoctorDto data, UriComponentsBuilder uriBuilder){
        Doctor doctor = new Doctor(data);
        doctorRepository.save(doctor);

        var uriCreateDoctor = uriBuilder.path("/doctor/details/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uriCreateDoctor).body(doctor);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ListDoctorDto>> getAllDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<ListDoctorDto> doctors = doctorRepository.findAllByActiveTrue(pageable).map(ListDoctorDto::new);
        return ResponseEntity.ok(doctors);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity updateDoctor(@PathVariable Long id, @RequestBody @Valid UpdateDoctorDto data){
            Doctor doctor = doctorRepository.getReferenceById(id);
            doctor.updateDoctorInfo(data);

            return ResponseEntity.ok(new DetailsDoctorDto(doctor));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.toBeInactive();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity detailsDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);

        return ResponseEntity.ok(new DetailsDoctorDto(doctor));
    }
}
