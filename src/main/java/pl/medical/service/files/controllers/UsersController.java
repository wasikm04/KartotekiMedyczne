package pl.medical.service.files.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.models.User;
import pl.medical.service.files.models.Error;
import pl.medical.service.files.repositories.PatientCardRepository;
import pl.medical.service.files.repositories.UserRepository;

import java.security.Principal;
import java.util.List;

@RestController
public class UsersController
{
    @Autowired
    private PatientCardRepository patientcards;

    @Autowired
    private UserRepository users;


    @RequestMapping(value ="/cards", produces = "application/json", method= RequestMethod.GET)
    public @ResponseBody List<PatientCard> getCardByParam(@RequestParam(value = "key", required = false) String key) { //,  opcja do zawężania poszukiwań i wrzucić do zapytania i rozpatrywać czy puste czy brać pod uwagę itp
        if(key != null)
            return patientcards.findPatientCardsByKey(key);
        return patientcards.findAll();
    }




    @RequestMapping(value ="/card/{username}", produces = "application/json", method= RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> getCardByUsername(@PathVariable String username, Authentication authentication) { //,  opcja do zawężania poszukiwań i wrzucić do zapytania i rozpatrywać czy puste czy brać pod uwagę itp
        if(authentication.getName().equals(username) || authentication.getAuthorities().contains("ROLE_ADMIN")) {
            return new ResponseEntity<PatientCard>(patientcards.findBy_username(username),HttpStatus.OK);
        }
        Error error = new Error(403, "Read access forbidden");
        return new ResponseEntity<Error>(error, HttpStatus.FORBIDDEN);
    }


    @RequestMapping(value ="/users/{username}", produces = "application/json", method= RequestMethod.GET)
    public @ResponseBody User getuser(@PathVariable String username, Authentication authentication) {
        if(authentication.getName().equals(username) || authentication.getAuthorities().contains("ROLE_ADMIN"))
            return users.findUserByUsername(username);
        return null;
    }


    @RequestMapping(value ="/users", consumes = "application/json", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String saveuser(@RequestBody String arg) {

        return "Users Here saved " + arg;
    }
}
