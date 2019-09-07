package pl.medical.service.files.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.medical.service.files.models.User;
import pl.medical.service.files.models.Error;
import pl.medical.service.files.services.user.UserService;

@RestController
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value ="/users/{user_mail}", produces = "application/json", method= RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable String user_mail, Authentication authentication) {
        if(authentication.getName().equals(user_mail) || authentication.getAuthorities().contains("ROLE_ADMIN"))
            return userService.findUserByEmail(user_mail);
        return null; //custom Error
    }


   // @RequestMapping(value ="/users/registration", consumes = "application/json", method= RequestMethod.POST)
    @PostMapping("/users/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        boolean registered = userService.createUserAccount(user);
        if (!registered) {
           // throw new UserAlreadyExistException(); //custom error?
            Error error = new Error(400, "User with that login(email) already exists");
            return new ResponseEntity<Error>(error, HttpStatus.FORBIDDEN);
        }
        Error error = new Error(204, "No Content");
        return new ResponseEntity<Error>(error, HttpStatus.CREATED); //custom success 204
    }
}
