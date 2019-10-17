package pl.medical.service.files.controllers;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.api.PatientCardDto;
import pl.medical.service.files.api.mappers.AppointmentMapper;
import pl.medical.service.files.api.mappers.DoctorCardMapper;
import pl.medical.service.files.api.mappers.PatientCardMapper;
import pl.medical.service.files.models.*;
import pl.medical.service.files.services.patientcard.PatientCardService;
import pl.medical.service.files.services.user.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class PatientCardsController {

    private PatientCardService patientcards;
    private UserService userService;
    private PatientCardMapper mapper;

    private AppointmentMapper appointmentMapper;
    private DoctorCardMapper doctorCardMapper;

    public PatientCardsController(PatientCardService patientcards,
                                  UserService userService,
                                  PatientCardMapper mapper,
                                  AppointmentMapper appointmentMapper, DoctorCardMapper doctorCardMapper) {
        this.patientcards = patientcards;
        this.userService = userService;
        this.mapper = mapper;
        this.appointmentMapper = appointmentMapper;
        this.doctorCardMapper = doctorCardMapper;
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

        ArrayList list = new ArrayList<MedicalTest>();
        List<String> paremeter = new ArrayList<>();
        paremeter.add("Tętno: 80");
        MedicalTest test = MedicalTest.builder().parametersWithReference(paremeter).authorMail("lekarz@pw.pl").testDate(LocalDate.of(2000, 4, 29)).testName("EKG").build();
        list.add(test);

        ArrayList list2 = new ArrayList<Referral>();
        Referral referral = Referral.builder().date(LocalDate.of(2010, 4, 29)).doctorMail("lekarz@pw.pl").numberPWZ(13123).purpose("Badanie EKG").recognition("Ból klatki piersiowej").build();
        list2.add(referral);

        ArrayList list3 = new ArrayList<Prescription>();
        List<String> meds = new ArrayList<>();
        meds.add("Apap 10");
        Prescription prescription = Prescription.builder().dateTo(LocalDate.of(2000, 4, 29)).departmentNFZ("212412").doctorMail("lekarz@pw.pl").permissions("Dodatkowe uprawnienie").medicines(meds).numberPWZ(2131232).build();
        list3.add(prescription);

        ArrayList list4 = new ArrayList<Treatment>();
        List<Information> l1 = new ArrayList<>();
        List<Information> l2 = new ArrayList<>();
        List<Information> l3 = new ArrayList<>();
        l1.add(Information.builder().doctorMail("lekarz@pw.pl").date(LocalDate.of(2000, 4, 29)).information("Apap10 3 razy dziennie po posiłku").build());
        l2.add(Information.builder().doctorMail("lekarz@pw.pl").date(LocalDate.of(2000, 4, 29)).information("Zalecany odpoczynek, obserwacja").build());
        l3.add(Information.builder().doctorMail("lekarz@pw.pl").date(LocalDate.of(2000, 4, 29)).information("Ból klatki piersiowej wskazujący na wieńcowe zapalenie").build());
        Treatment treatment = Treatment.builder().numberICD("I20").pharmacotherapy(l1).medicalAnalysisAndRecommendations(l2).symptomsAndDiagnosis(l3).build();
        list4.add(treatment);

        PatientCard card = PatientCard.
                builder()
                ._id(new ObjectId("5d407961d235193ae8ea97af"))
                .userId(new ObjectId("5d6c14f4d5b400474cea4b09"))
                .userMail("user@pw.pl")
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

    @GetMapping(value = "/card/testAppointment")
    public @ResponseBody
    ResponseEntity<?> getApp() {
        Appointment appointment = Appointment.builder()
                .comment("Wolna")
                .date(LocalDateTime.of(2019, 11, 22, 17, 45))
                .doctorFullName("Jacek Komuda")
                .doctorMail("lekarz@pw.pl")
                .build();
        return ResponseEntity.ok(appointmentMapper.mapToAppointmentDto(appointment));
    }

    @GetMapping(value = "/card/testDoctor")
    public @ResponseBody
    ResponseEntity<?> getDoctor() {
        DoctorCard card = DoctorCard.builder()
                .firstName("Jacek")
                .lastName("Komuda")
                .numberPWZ(12352324)
                .phoneNumber(923913221)
                .prescriptionRefundNumber("23dada213")
                .specializations(Arrays.asList("Kardiologia", "Kardiochirurgia"))
                .title("dr n. med.")
                .userMail("lekarz@pw.pl")
                .build();
        return ResponseEntity.ok(doctorCardMapper.mapToDoctorCardDto(card));
    }
}
