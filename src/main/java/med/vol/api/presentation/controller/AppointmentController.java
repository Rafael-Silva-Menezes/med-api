package med.vol.api.presentation.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.application.ScheduleAppointmentsService;
import med.vol.api.domain.entities.Appointment;
import med.vol.api.infra.repositories.AppointmentRepository;
import med.vol.api.presentation.dto.appointment.CreateAppointmentDto;
import med.vol.api.presentation.dto.appointment.DetailsAppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ScheduleAppointmentsService scheduleAppointmentsService;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity createSchedule(@RequestBody @Valid CreateAppointmentDto data){
        DetailsAppointmentDto detailsAppointment = scheduleAppointmentsService.schedule(data);
        return ResponseEntity.ok(detailsAppointment);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity detailsSchedule(@PathVariable Long id){
        Appointment appointment = appointmentRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetailsAppointmentDto(appointment));
    }
}
