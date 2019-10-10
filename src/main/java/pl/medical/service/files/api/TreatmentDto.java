package pl.medical.service.files.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.medical.service.files.models.Information;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class TreatmentDto {
    private String _id;
    private String userMail;
    private String numberICD;
    private List<Information> symptomsAndDiagnosis;
    private List<Information> pharmacotherapy;
    private List<Information> medicalAnalysisAndRecommendations;
}
