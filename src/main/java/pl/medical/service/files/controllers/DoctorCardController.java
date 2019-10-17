package pl.medical.service.files.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.api.DoctorCardDto;
import pl.medical.service.files.api.mappers.DoctorCardMapper;
import pl.medical.service.files.models.DoctorCard;
import pl.medical.service.files.services.doctorcard.DoctorCardService;

import javax.validation.Valid;

@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class DoctorCardController {

    private DoctorCardService doctorCardService;
    private DoctorCardMapper doctorCardMapper;

    public DoctorCardController(DoctorCardService doctorCardService,
                                DoctorCardMapper doctorCardMapper) {
        this.doctorCardService = doctorCardService;
        this.doctorCardMapper = doctorCardMapper;
    }

    @GetMapping(value = "/doctor-card/{doctorMail}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> getCardByEmail(@PathVariable String doctorMail) {
        DoctorCard card = doctorCardService.getCardByMail(doctorMail);
        if (card != null) {
            return ResponseEntity.ok(doctorCardMapper.mapToDoctorCardDto(card));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/doctor-card", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> updateAppointment(@Valid @RequestBody DoctorCardDto dto, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(dto.getUserMail())) {
            boolean updated = doctorCardService.addOrUpdateDoctorCard(doctorCardMapper.mapToDoctorCard(dto));
            if (updated) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().body("Niewłaściwe dane");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nie jesteś uprawniony do zmiany danych innych użytkowników");
    }
}
