package pl.medical.service.files.controllers;

import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import java.util.Optional;

@Api(value = "Karta lekarza", description = "Operacje pobierania i aktualizacji karty lekarza")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class DoctorCardController {

    private DoctorCardService doctorCardService;
    private DoctorCardMapper doctorCardMapper;

    public DoctorCardController(DoctorCardService doctorCardService,
                                DoctorCardMapper doctorCardMapper,
                                DoctorCardRepository repository) {
        this.doctorCardService = doctorCardService;
        this.doctorCardMapper = doctorCardMapper;
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

    @GetMapping(value = "/doctor-card/list/{page}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> getCardsByText(@PathVariable int page, @RequestParam(value = "text", required = false) String text) {
        Page<DoctorCard> coll = null;
        if (text == null || text.isEmpty()) {
            coll = doctorCardService.getPage(PageRequest.of(page, 5, Sort.by("lastName").and(Sort.by("firstName"))));
        } else {
            coll = doctorCardService.getByFirstOrLastName(text, PageRequest.of(page, 5, Sort.by("lastName").and(Sort.by("firstName"))));
        }
        if (Optional.ofNullable(coll).isPresent()) {
            Page<DoctorCardDto> dto = coll.map(card -> doctorCardMapper.mapToDoctorCardDto(card));
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
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
