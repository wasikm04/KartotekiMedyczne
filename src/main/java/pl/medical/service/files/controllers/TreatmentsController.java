package pl.medical.service.files.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.medical.service.files.api.TreatmentDto;
import pl.medical.service.files.api.mappers.TreatmentMapper;
import pl.medical.service.files.services.treatment.TreatmentService;

import javax.validation.Valid;

@CrossOrigin(value = "*", maxAge = 3600, allowCredentials = "true")
@RestController
public class TreatmentsController {

    private TreatmentService treatmentService;
    private TreatmentMapper treatmentMapper;


    public TreatmentsController(TreatmentService treatmentService,
                                TreatmentMapper treatmentMapper) {
        this.treatmentService = treatmentService;
        this.treatmentMapper = treatmentMapper;
    }

    @PostMapping(value = "/treatment", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> updateTreatment(@Valid @RequestBody TreatmentDto dto, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(dto.getUserMail())) {
            boolean created = treatmentService.addTreatmentToPatientCard(dto.getUserMail(), treatmentMapper.mapToTreatment(dto));
            if (created) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().body("Niewłaściwe dane");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nie jesteś uprawniony do zmiany danych innych użytkowników");
    }

    @PutMapping(value = "/treatment", produces = "application/json")
    public @ResponseBody
    ResponseEntity<?> addTreatment(@Valid @RequestBody TreatmentDto dto, Authentication authentication) {
        if (authentication.getPrincipal().toString().equals(dto.getUserMail())) {
            boolean updated = treatmentService.updateTreatmentToPatientCard(dto.getUserMail(), treatmentMapper.mapToTreatment(dto));
            if (updated) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.badRequest().body("Niewłaściwe dane");
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nie jesteś uprawniony do zmiany danych innych użytkowników");
    }
}
