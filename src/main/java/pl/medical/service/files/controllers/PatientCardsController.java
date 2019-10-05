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

@RestController()
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
    ResponseEntity<?> getCardByEmail(@PathVariable String user_mail, Authentication authentication) { //,  opcja do zawężania poszukiwań i wrzucić do zapytania i rozpatrywać czy puste czy brać pod uwagę itp
        if(authentication.getName().equals(user_mail) || authentication.getAuthorities().contains("ROLE_ADMIN")) {
            PatientCard card = patientcards.getPatientCardByMail(user_mail);
            if (card != null) {
                return ResponseEntity.ok(card);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such card");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to see others cards");
    }

    @PostMapping(value = "/card", produces = "application/json")
    public ResponseEntity<?> updateCard(@Valid @RequestBody PatientCard card, Authentication authentication) {
        boolean idCheck = userService.findUserByEmail(authentication.getName()).get_id().equals(card.get_user_id());
        if (authentication.getName().equals(card.get_user_mail()) || idCheck) {
            if (card.get_user_id() != null) {
                patientcards.updateCardInformation(card);
                return ResponseEntity.ok("Dodano/zaktualizowano kartę o id:" + card.get_user_id().toString());
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad data");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to update this card");
    }


    //@RequestMapping(value ="/{username}", produces = "application/json", method= RequestMethod.GET)
    // public @ResponseBody ResponseEntity<?> getCardByUsername(@PathVariable String username, Authentication authentication) { //,  opcja do zawężania poszukiwań i wrzucić do zapytania i rozpatrywać czy puste czy brać pod uwagę itp
    //    if(authentication.getName().equals(username) || authentication.getAuthorities().contains("ROLE_ADMIN")) {
    //        return new ResponseEntity<PatientCard>(patientcards.findBy_username(username),HttpStatus.OK);
    //    }
    //
    //  }
}
