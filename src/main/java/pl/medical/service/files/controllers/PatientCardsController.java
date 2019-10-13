package pl.medical.service.files.controllers;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.api.PatientCardDto;
import pl.medical.service.files.api.mappers.PatientCardMapper;
import pl.medical.service.files.models.*;
import pl.medical.service.files.services.patientcard.PatientCardService;
import pl.medical.service.files.services.user.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class PatientCardsController {

    private PatientCardService patientcards;
    private UserService userService;
    private PatientCardMapper mapper;

    public PatientCardsController(PatientCardService patientcards,
                                  UserService userService,
                                  PatientCardMapper mapper) {
        this.patientcards = patientcards;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/card/{user_mail}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> getCardByEmail(@PathVariable String user_mail, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(user_mail) || authentication.getAuthorities().contains("ROLE_ADMIN")) {
            PatientCard card = patientcards.getPatientCardByMail(user_mail);
            if (card != null) {
                return ResponseEntity.ok(mapper.mapToPatientCardDto(card));
            }
            return ResponseEntity.notFound().build();
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to see others cards");
    }

    @PostMapping(value = "/card", produces = "application/json")
    public ResponseEntity<?> updateCard(@Valid @RequestBody PatientCardDto cardDto, Authentication authentication) {
        PatientCard card = mapper.mapToPatientCard(cardDto);
        boolean idCheck = userService.findUserByEmail(authentication.getName()).get_id().equals(card.getUserId());
        if (authentication.getPrincipal().toString().equals(card.getUserMail()) || idCheck) {
            if (card.getUserId() != null) {
                boolean updated = patientcards.updateCardInformation(card);
                return updated ? ResponseEntity.ok("Dodano/zaktualizowano kartę o id:" + card.getUserId().toString()) :
                ResponseEntity.badRequest().body("Karta nie została zaktualizowana");
            }
            return ResponseEntity.badRequest().body("Brakuje numeru id aby zaktualizować kartę");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to update this card");
    }

    @GetMapping(value = "/card/testData")
    public @ResponseBody
    ResponseEntity<?> getData() {
        List<String> paremeter = new ArrayList<>();
        paremeter.add("Tętno: 80");
        MedicalTest test = MedicalTest.builder().parametersWithReference(paremeter).authorMail("lekarz@pw.pl").testDate(LocalDate.of(2000, 4, 29)).testName("EKG").build();
        ArrayList list = new ArrayList<MedicalTest>();
        list.add(test);
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
        return ResponseEntity.ok(mapper.mapToPatientCardDto(card));
    }
}
