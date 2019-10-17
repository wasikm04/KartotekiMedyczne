package pl.medical.service.files.controllers;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.api.AppointmentDto;
import pl.medical.service.files.api.mappers.AppointmentMapper;
import pl.medical.service.files.models.Appointment;
import pl.medical.service.files.services.appointment.AppointmentService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value = "Wizyty", description = "Operacje związane z pobieraniem i tworzeniem wizyt u lekarzy")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class AppointmentsController {

    private AppointmentService appointmentService;
    private AppointmentMapper appointmentMapper;

    public AppointmentsController(AppointmentService appointmentService,
                                  AppointmentMapper appointmentMapper) {
        this.appointmentService = appointmentService;
        this.appointmentMapper = appointmentMapper;
    }

    @GetMapping(value = "/appointment/doctor/{doctorMail}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> getDoctorAppointment(@PathVariable String doctorMail) {
        List<Appointment> list = appointmentService.getAppointmentsForDoctorMail(doctorMail);
        if (Optional.ofNullable(list).isPresent()) {
            return ResponseEntity.ok(appointmentMapper.mapToAppointmentDtoList(list));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/appointment/patient/{patientMail}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> getPatientAppointment(@PathVariable String patientMail, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(patientMail)) {
            List<Appointment> list = appointmentService.getAppointmentsForPatientMail(patientMail);
            if (Optional.ofNullable(list).isPresent()) {
                return ResponseEntity.ok(appointmentMapper.mapToAppointmentDtoList(list));
            }
            return ResponseEntity.notFound().build();
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nie jesteś uprawniony do pobierania danych innych użytkowników");
    }

    @GetMapping(value = "/appointment/{doctorMail}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> getFreeAppointments(@PathVariable String doctorMail) {
        List<Appointment> list = appointmentService.getListOfFreeAppointmentsOfDoctorMail(doctorMail);
        if (Optional.ofNullable(list).isPresent()) {
            return ResponseEntity.ok(appointmentMapper.mapToAppointmentDtoList(list));
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping(value = "/appointment", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> createAppointment(@Valid @RequestBody AppointmentDto dto, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(dto.getDoctorMail())) {
            boolean created = appointmentService.createNewAppointment(appointmentMapper.mapToAppointment(dto));
            if (created) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().body("Niewłaściwe dane");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nie jesteś uprawniony do zmiany danych innych użytkowników");
    }


    @PutMapping(value = "/appointment", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> updateAppointment(@Valid @RequestBody AppointmentDto dto, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(dto.getPatientMail())) {
            boolean updated = appointmentService.updateAppointmentWithPatientData(appointmentMapper.mapToAppointment(dto));
            if (updated) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().body("Niewłaściwe dane");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nie jesteś uprawniony do zmiany danych innych użytkowników");
    }

}
