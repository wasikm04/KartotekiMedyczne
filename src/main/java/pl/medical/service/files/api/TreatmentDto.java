package pl.medical.service.files.api;

import lombok.*;

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
    private List<InformationDto> symptomsAndDiagnosis;
    private List<InformationDto> pharmacotherapy;
    private List<InformationDto> medicalAnalysisAndRecommendations;
}
