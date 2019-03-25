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

    @ResponseBody
    @RequestMapping(value ="/cards", produces = "application/json", method= RequestMethod.GET)
    public List<PatientCard> getCardByParam(@RequestParam(value = "user_id", required = false) ObjectId user_id, @RequestParam(value = "key", required = false) String key) { //,  opcja do zawężania poszukiwań i wrzucić do zapytania i rozpatrywać czy puste czy brać pod uwagę itp
        if(user_id != null)
            return patientcards.findAllBy_userid(user_id);
        if(key != null)
            return patientcards.findPatientCardsByKey(key);
        return patientcards.findAll();
    }



    @ResponseBody
    @RequestMapping(value ="/card/{username}", produces = "application/json", method= RequestMethod.GET)
    public PatientCard getCardByUsername(@PathVariable String username, Authentication authentication) { //,  opcja do zawężania poszukiwań i wrzucić do zapytania i rozpatrywać czy puste czy brać pod uwagę itp
        if(authentication.getName().equals(username) || authentication.getAuthorities().contains("ROLE_ADMIN")) {
            return patientcards.findPatientCardByUsername(username); //zamienić schemat patient card: _userid na pole username
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value ="/users/{username}", produces = "application/json", method= RequestMethod.GET)
    public User getuser(@PathVariable String username, Authentication authentication) {
        if(authentication.getName().equals(username) || authentication.getAuthorities().contains("ROLE_ADMIN"))
            return users.findUserByUsername(username);
        return null;
    }


    @RequestMapping(value ="/users", consumes = "application/json", method= RequestMethod.POST)
    public String saveuser(@RequestBody String arg) {

        return "Users Here saved " + arg;
    }
}
