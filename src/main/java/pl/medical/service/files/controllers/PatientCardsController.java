package pl.medical.service.files.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.medical.service.files.models.Error;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.repositories.PatientCardRepository;

import java.util.List;

@RestController
public class PatientCardsController {

    @Autowired
    private PatientCardRepository patientcards;

    @RequestMapping(value ="/cards", produces = "application/json", method= RequestMethod.GET)
    public @ResponseBody
    List<PatientCard> getCardByParam(@RequestParam(value = "key", required = false) String key) { //,  opcja do zawężania poszukiwań i wrzucić do zapytania i rozpatrywać czy puste czy brać pod uwagę itp
        if(key != null)
            return patientcards.findPatientCardsByKey(key);
        return patientcards.findAll();
    }


    @RequestMapping(value ="/card/{user_mail}", produces = "application/json", method= RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> getCardByEmail(@PathVariable String user_mail, Authentication authentication) { //,  opcja do zawężania poszukiwań i wrzucić do zapytania i rozpatrywać czy puste czy brać pod uwagę itp
        if(authentication.getName().equals(user_mail) || authentication.getAuthorities().contains("ROLE_ADMIN")) {
            return new ResponseEntity<PatientCard>(patientcards.findBy_user_mail(user_mail), HttpStatus.OK);
        }
        Error error = new Error(403, "Read access forbidden");
        return new ResponseEntity<Error>(error, HttpStatus.FORBIDDEN);
    }


    //@RequestMapping(value ="/card/{username}", produces = "application/json", method= RequestMethod.GET)
    // public @ResponseBody ResponseEntity<?> getCardByUsername(@PathVariable String username, Authentication authentication) { //,  opcja do zawężania poszukiwań i wrzucić do zapytania i rozpatrywać czy puste czy brać pod uwagę itp
    //    if(authentication.getName().equals(username) || authentication.getAuthorities().contains("ROLE_ADMIN")) {
    //        return new ResponseEntity<PatientCard>(patientcards.findBy_username(username),HttpStatus.OK);
    //    }
    //     Error error = new Error(403, "Read access forbidden");
    //      return new ResponseEntity<Error>(error, HttpStatus.FORBIDDEN);
    //  }
}
