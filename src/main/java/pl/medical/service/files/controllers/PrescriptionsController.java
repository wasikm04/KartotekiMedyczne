package pl.medical.service.files.controllers;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.api.PrescriptionDto;
import pl.medical.service.files.api.mappers.PrescriptionMapper;
import pl.medical.service.files.services.prescription.PrescriptionService;

import javax.validation.Valid;

@Api(value = "Recepta", description = "Operacje dodawania i aktualizowania recept pacjenta")
@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class PrescriptionsController {

    private PrescriptionService prescriptionService;
    private PrescriptionMapper prescriptionMapper;

    public PrescriptionsController(PrescriptionService prescriptionService,
                                   PrescriptionMapper prescriptionMapper) {
        this.prescriptionService = prescriptionService;
        this.prescriptionMapper = prescriptionMapper;
    }

    @PostMapping(value = "/prescription", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> updatePrescription(@Valid @RequestBody PrescriptionDto dto, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(dto.getUserMail())) {
            boolean created = prescriptionService.addPrescriptionToPatientCard(dto.getUserMail(), prescriptionMapper.mapToPrescription(dto));
            if (created) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().body("Niewłaściwe dane");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nie jesteś uprawniony do zmiany danych innych użytkowników");
    }

    @PutMapping(value = "/prescription", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> addPrescription(@Valid @RequestBody PrescriptionDto dto, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(dto.getUserMail())) {
            boolean updated = prescriptionService.updatePrescriptionToPatientCard(dto.getUserMail(), prescriptionMapper.mapToPrescription(dto));
            if (updated) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().body("Niewłaściwe dane");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nie jesteś uprawniony do zmiany danych innych użytkowników");
    }
}
