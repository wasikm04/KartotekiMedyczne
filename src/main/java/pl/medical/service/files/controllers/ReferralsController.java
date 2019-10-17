package pl.medical.service.files.controllers;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.api.ReferralDto;
import pl.medical.service.files.api.mappers.ReferralMapper;
import pl.medical.service.files.services.referral.ReferralService;

import javax.validation.Valid;

@Api(value = "Skierowanie", description = "Operacje dodawania i aktualizowania skierowań pacjenta")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class ReferralsController {

    private ReferralService referralService;
    private ReferralMapper referralMapper;

    public ReferralsController(ReferralService referralService,
                               ReferralMapper referralMapper) {
        this.referralService = referralService;
        this.referralMapper = referralMapper;
    }

    @PostMapping(value = "/referral", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> updateReferral(@Valid @RequestBody ReferralDto dto, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(dto.getUserMail())) {
            boolean created = referralService.addReferralToPatientCard(dto.getUserMail(), referralMapper.mapToReferral(dto));
            if (created) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().body("Niewłaściwe dane");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nie jesteś uprawniony do zmiany danych innych użytkowników");
    }

    @PutMapping(value = "/referral", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> addReferral(@Valid @RequestBody ReferralDto dto, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(dto.getUserMail())) {
            boolean updated = referralService.updateReferralToPatientCard(dto.getUserMail(), referralMapper.mapToReferral(dto));
            if (updated) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().body("Niewłaściwe dane");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nie jesteś uprawniony do zmiany danych innych użytkowników");
    }
}
