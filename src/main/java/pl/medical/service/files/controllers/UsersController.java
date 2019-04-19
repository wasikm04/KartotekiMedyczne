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
import pl.medical.service.files.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
public class UsersController
{

    @Autowired
    private UserService userService;



    @RequestMapping(value ="/users/{user_mail}", produces = "application/json", method= RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable String user_mail, Authentication authentication) {
        if(authentication.getName().equals(user_mail) || authentication.getAuthorities().contains("ROLE_ADMIN"))
            return userService.findUserByEmail(user_mail);
        return null; //custom Error
    }


    @RequestMapping(value ="/users/registration", consumes = "application/json", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveUser(@RequestBody User user) {

        User registered = userService.createUserAccount(user);
        if (registered == null) {
           // throw new UserAlreadyExistException(); //custom error
        }
        Error error = new Error(403, "Read access forbidden");
        return new ResponseEntity<Error>(error, HttpStatus.FORBIDDEN); //custom success 204
    }
}
