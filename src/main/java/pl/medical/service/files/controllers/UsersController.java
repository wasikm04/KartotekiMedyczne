package pl.medical.service.files.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.models.User;
import pl.medical.service.files.services.patientcard.PatientCardService;
import pl.medical.service.files.services.user.UserService;

import javax.validation.Valid;

@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class UsersController {

    private UserService userService;
    private PatientCardService cardService;

    public UsersController(UserService userService,
                           PatientCardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @GetMapping(value = "/user/role/{user_mail}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> getUser(@PathVariable String user_mail, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(user_mail) || authentication.getAuthorities().contains("ROLE_ADMIN")) {
            User user = userService.findUserByEmail(user_mail);
            if (user != null) {
                return ResponseEntity.ok(user.getRoles());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to see others cards");
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        boolean registered = userService.createUserAccount(user);
        if (!registered) {
            return ResponseEntity.badRequest().body("Błędne dane lub użytkownik o podanym mailu już istnieje");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/role/user")
    public ResponseEntity<?> updateRole(@RequestBody User user) {
        boolean updated = userService.makeUserAsDoctor(user.getEmail());
        if (!updated) {
            return ResponseEntity.badRequest().body("Nieprawidłowy mail");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
