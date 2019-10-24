package pl.medical.service.files.controllers;

import io.swagger.annotations.Api;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.api.DoctorCardDto;
import pl.medical.service.files.api.mappers.DoctorCardMapper;
import pl.medical.service.files.models.DoctorCard;
import pl.medical.service.files.repositories.doctorcard.DoctorCardRepository;
import pl.medical.service.files.services.doctorcard.DoctorCardService;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Karta lekarza", description = "Operacje pobierania i aktualizacji karty lekarza")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class DoctorCardController {

    DoctorCardRepository repository;
    private DoctorCardService doctorCardService;
    private DoctorCardMapper doctorCardMapper;

    public DoctorCardController(DoctorCardService doctorCardService,
                                DoctorCardMapper doctorCardMapper,
                                DoctorCardRepository repository) {
        this.doctorCardService = doctorCardService;
        this.doctorCardMapper = doctorCardMapper;
        this.repository = repository;
    }

    @GetMapping(value = "/doctor-card/{doctorMail}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> getCardByEmail(@PathVariable String doctorMail) {
        DoctorCard card = doctorCardService.getCardByMail(doctorMail);
        if (card != null) {
            return ResponseEntity.ok(doctorCardMapper.mapToDoctorCardDto(card));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/doctor-card/list/{text}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> getCardsByText(@PathVariable String text) {

        TextCriteria criteria1 = TextCriteria.forDefaultLanguage().caseSensitive(false).matching(text);
        TextCriteria criteria2 = TextCriteria.forDefaultLanguage().caseSensitive(false).matchingPhrase(text);
        TextCriteria criteria3 = TextCriteria.forDefaultLanguage().caseSensitive(false).matchingAny(text);
        List<DoctorCard> l3 = doctorCardService.getByFirstOrLastName(text, PageRequest.of(0, 5));
        List<DoctorCard> l1 = repository.findByFirstNameOrLastNameOrUserMail(text, text, text);
        List<DoctorCard> l4s = repository.findByFirstNameLikeAndLastNameLike(text, text);

        List<DoctorCard> l24 = repository.findBy(criteria1, PageRequest.of(0, 5)).getContent();

        List<DoctorCard> l222 = repository.findBy(criteria2, PageRequest.of(0, 5)).getContent();

        List<DoctorCard> l211 = repository.findBy(criteria2, PageRequest.of(0, 5)).getContent();

        return null;
    }

    @PutMapping(value = "/doctor-card", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> updateDoctorCard(@Valid @RequestBody DoctorCardDto dto, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(dto.getUserMail())) {
            boolean updated = doctorCardService.addOrUpdateDoctorCard(doctorCardMapper.mapToDoctorCard(dto));
            if (updated) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().body("Niewłaściwe dane");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nie jesteś uprawniony do zmiany danych innych użytkowników");
    }
}
