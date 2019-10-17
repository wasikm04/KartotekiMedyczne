package pl.medical.service.files.api;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Model przebiegu leczenia pacjenta")
public class TreatmentDto {
    private String _id;
    @NotNull
    private String userMail;
    @NotNull
    private String numberICD;
    private List<InformationDto> symptomsAndDiagnosis;
    private List<InformationDto> pharmacotherapy;
    private List<InformationDto> medicalAnalysisAndRecommendations;
}
