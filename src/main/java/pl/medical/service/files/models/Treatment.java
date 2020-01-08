package pl.medical.service.files.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

//choroba i zalecenia do niej
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class Treatment {
    @Id
    private ObjectId _id;
    @NotNull
    private String numberICD;
    private List<Information> symptomsAndDiagnosis;
    private List<Information> pharmacotherapy;
    private List<Information> medicalAnalysisAndRecommendations;
}
