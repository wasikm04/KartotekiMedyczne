package pl.medical.service.files.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.services.patientcard.PatientCardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

@RestController(value = "card/")
public class PatientCardsController {

    private PatientCardService patientcards;

    public PatientCardsController(PatientCardService patientcards){
        this.patientcards = patientcards;
    }

    @GetMapping(value = "/{user_mail}", produces = "application/json")
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

 //   @PostMapping
 //   public ResponseEntity<?> saveCard(@Valid @RequestBody PatientCard card){

 //   }


    //@RequestMapping(value ="/{username}", produces = "application/json", method= RequestMethod.GET)
    // public @ResponseBody ResponseEntity<?> getCardByUsername(@PathVariable String username, Authentication authentication) { //,  opcja do zawężania poszukiwań i wrzucić do zapytania i rozpatrywać czy puste czy brać pod uwagę itp
    //    if(authentication.getName().equals(username) || authentication.getAuthorities().contains("ROLE_ADMIN")) {
    //        return new ResponseEntity<PatientCard>(patientcards.findBy_username(username),HttpStatus.OK);
    //    }
    //
    //  }
}
