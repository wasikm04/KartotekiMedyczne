package pl.medical.service.files.api;

import lombok.*;
import pl.medical.service.files.models.Information;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TreatmentDto {
    private String _id;
    private String userMail;
    private String numberICD;
    private List<Information> symptomsAndDiagnosis;
    private List<Information> pharmacotherapy;
    private List<Information> medicalAnalysisAndRecommendations;
}
