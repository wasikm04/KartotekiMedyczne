package pl.medical.service.files.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.services.patientcard.PatientCardService;
import pl.medical.service.files.services.user.UserService;

import javax.validation.Valid;

@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class PatientCardsController {

    private PatientCardService patientcards;
    private UserService userService;

    public PatientCardsController(PatientCardService patientcards,
                                  UserService userService) {
        this.patientcards = patientcards;
        this.userService = userService;
    }

    @GetMapping(value = "/card/{user_mail}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> getCardByEmail(@PathVariable String user_mail, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(user_mail) || authentication.getAuthorities().contains("ROLE_ADMIN")) {
            PatientCard card = patientcards.getPatientCardByMail(user_mail);
            if (card != null) {
                return ResponseEntity.ok(card);
            }
            return ResponseEntity.notFound().build();
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to see others cards");
    }

    @PostMapping(value = "/card", produces = "application/json")
    public ResponseEntity<?> updateCard(@Valid @RequestBody PatientCard card, Authentication authentication) {
        boolean idCheck = userService.findUserByEmail(authentication.getName()).get_id().equals(card.getUserId());
        if (authentication.getPrincipal().toString().equals(card.getUserMail()) || idCheck) {
            if (card.getUserId() != null) {
                patientcards.updateCardInformation(card);
                return ResponseEntity.ok("Dodano/zaktualizowano kartę o id:" + card.getUserId().toString());
            }
            return ResponseEntity.badRequest().body("Wrong data, card lacks of id or something else");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to update this card");
    }
}
