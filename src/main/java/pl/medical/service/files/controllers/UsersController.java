package pl.medical.service.files.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.models.PatientCard;
import pl.medical.service.files.models.User;
import pl.medical.service.files.services.patientcard.PatientCardService;
import pl.medical.service.files.services.user.UserService;

import javax.validation.Valid;

@RestController()
public class UsersController {

    private UserService userService;

    private PatientCardService cardService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/user/{user_mail}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> getUser(@PathVariable String user_mail, Authentication authentication) {
        if (authentication.getName().equals(user_mail) || authentication.getAuthorities().contains("ROLE_ADMIN")) {
            User user = userService.findUserByEmail(user_mail);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with that mail was not found");
            }
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to see others cards");
    }


    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        boolean registered = userService.createUserAccount(user);
        if (!registered) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong user data or user with that mail already exists");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
