package pl.medical.service.files.controllers;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.models.*;
import pl.medical.service.files.services.patientcard.PatientCardService;
import pl.medical.service.files.services.user.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(value = "/user/testData")
    public @ResponseBody
    ResponseEntity<?> getData() {
        List<String> paremeter = new ArrayList<>();
        paremeter.add("Tętno: 80");
        MedicalTest test = MedicalTest.builder().parametersWithReference(paremeter).authormail("lekarz@pw.pl").testDate(LocalDate.of(2000, 4, 29)).testName("EKG").build();
        ArrayList list = new ArrayList<MedicalTest>();
        //list.add(test);
        ArrayList list2 = new ArrayList<Referral>();
        ArrayList list3 = new ArrayList<Prescription>();
        ArrayList list4 = new ArrayList<Treatment>();

        PatientCard card = PatientCard.
                builder()
                ._id(new ObjectId("5d407961d235193ae8ea97af"))
                .userId(new ObjectId("5d6c14f4d5b400474cea4b09"))
                .userMail("admin@pw.pl")
                .address("Warszawa 20-400 ul. Pierwsza 12")
                .dateBirth(LocalDate.of(2003, 4, 29))
                .firstName("Jan")
                .lastName("Kowalski")
                .insuranceType("1241221")
                .PESEL("92045906152")
                .sex('m')
                .phoneNumber("512512512")
                .medicalTests(list)
                .referrals(list2)
                .prescriptions(list3)
                .treatments(list4)
                .build();
        return ResponseEntity.ok(card);
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        boolean registered = userService.createUserAccount(user);
        if (!registered) {
            return ResponseEntity.badRequest().body("Wrong user data or user with that mail already exists");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
