package med.vol.api.appointment.presentation.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.appointment.application.CancelAppointmentsService;
import med.vol.api.appointment.application.ScheduleAppointmentsService;
import med.vol.api.appointment.domain.entity.Appointment;
import med.vol.api.appointment.domain.entity.ReasonCancellationAppointment;
import med.vol.api.appointment.infra.AppointmentRepository;
import med.vol.api.appointment.presentation.dto.CancelAppointmentDto;
import med.vol.api.appointment.presentation.dto.CreateAppointmentDto;
import med.vol.api.appointment.presentation.dto.DetailsAppointmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ScheduleAppointmentsService scheduleAppointmentsService;

    @Autowired
    private CancelAppointmentsService cancelAppointmentsService;

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

    @DeleteMapping("/cancel")
    @Transactional
    public ResponseEntity deleteSchedule(@RequestBody @Valid CancelAppointmentDto data){
        cancelAppointmentsService.cancel(data);

        return ResponseEntity.noContent().build();
    }
}
