package med.vol.api.presentation.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.presentation.dto.patient.UpdatePatientDto;
import med.vol.api.presentation.dto.patient.CreatePatientDto;
import med.vol.api.presentation.dto.patient.DetailsPatientDto;
import med.vol.api.presentation.dto.patient.ListPatientDto;
import med.vol.api.domain.entities.Patient;
import med.vol.api.infra.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/create")
    public ResponseEntity createPatient(@RequestBody @Valid CreatePatientDto patientData, UriComponentsBuilder uriBuilder){
        Patient patient = new Patient(patientData);
        patientRepository.save(patient);

        var uriCreatePatient = uriBuilder.path("/patient/details/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uriCreatePatient).body(patient);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ListPatientDto>> getAllPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<ListPatientDto> patients =  patientRepository.findAll(pageable).map(ListPatientDto::new);
        return ResponseEntity.ok(patients);

    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity updatePatient(@PathVariable Long id, @RequestBody @Valid UpdatePatientDto patientData){
        var patient = patientRepository.getReferenceById(id);
        patient.updatePatientInfo(patientData);

        return ResponseEntity.ok(new DetailsPatientDto(patient));

    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id){
        Patient patient = patientRepository.getReferenceById(id);
        patient.toBeInactive();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity detailsPatient(@PathVariable Long id){
        Patient patient = patientRepository.getReferenceById(id);

        return ResponseEntity.ok(new DetailsPatientDto(patient));
    }
}
