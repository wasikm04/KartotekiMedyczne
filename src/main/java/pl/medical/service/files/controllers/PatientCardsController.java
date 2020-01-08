package pl.medical.service.files.controllers;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.api.PatientCardDto;
import pl.medical.service.files.api.mappers.AppointmentMapper;
import pl.medical.service.files.api.mappers.DoctorCardMapper;
import pl.medical.service.files.api.mappers.PatientCardMapper;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.models.User;
import pl.medical.service.files.services.patientcard.PatientCardService;
import pl.medical.service.files.services.user.UserService;

import javax.validation.Valid;

@Api(value = "Kartoteka pacjenta", description = "Operacje pobierania i zapisywania kartoteki pacjenta")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class PatientCardsController {

    private PatientCardService patientcards;
    private UserService userService;
    private PatientCardMapper mapper;

    private AppointmentMapper appointmentMapper;
    private DoctorCardMapper doctorCardMapper;

    public PatientCardsController(PatientCardService patientcards,
                                  UserService userService,
                                  PatientCardMapper mapper,
                                  AppointmentMapper appointmentMapper, DoctorCardMapper doctorCardMapper) {
        this.patientcards = patientcards;
        this.userService = userService;
        this.mapper = mapper;
        this.appointmentMapper = appointmentMapper;
        this.doctorCardMapper = doctorCardMapper;
    }

    @GetMapping(value = "/card/{user_mail}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> getCardByEmail(@PathVariable String user_mail, Authentication authentication) {
        boolean isDoctor = authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_DOCTOR"));
        if (isDoctor || authentication.getPrincipal().toString().equals(user_mail)) {
            PatientCard card = patientcards.getPatientCardByMail(user_mail);
            if (card != null) {
                return ResponseEntity.ok(mapper.mapToPatientCardDto(card));
            }
            return ResponseEntity.notFound().build();
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to see others cards");
    }

    @PostMapping(value = "/card", produces = "application/json")
    public ResponseEntity<?> updateCard(@Valid @RequestBody PatientCardDto cardDto, Authentication authentication) {
        PatientCard card = mapper.mapToPatientCard(cardDto);
        User check = userService.findUserByEmail(authentication.getName());
        boolean idCheck = card.getUserId().toString().equals(check.get_id().toString());
        if (idCheck) {
            if (card.getUserId() != null) {
                boolean updated = patientcards.updateCardInformation(card);
                return updated ? ResponseEntity.ok("Dodano/zaktualizowano kartę") :
                        ResponseEntity.badRequest().body("Karta nie została zaktualizowana");
            }
            return ResponseEntity.badRequest().body("Brakuje numeru id aby zaktualizować kartę");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to update this card");
    }
}
